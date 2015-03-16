package com.bizconf.vcaasz.action.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.Contacts;
import com.bizconf.vcaasz.entity.EmpowerConfig;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.SiteContacts;
import com.bizconf.vcaasz.entity.SiteOrg;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteAdminInterceptor;
import com.bizconf.vcaasz.logic.ConfUserLogic;
import com.bizconf.vcaasz.logic.ContactLogic;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.ConfUserService;
import com.bizconf.vcaasz.service.ContactService;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.EmpowerConfigService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.OrgService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.BeanUtil;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.ExcelUtil;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.JsonUtil;
import com.bizconf.vcaasz.util.ObjectUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberCFile;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
import com.libernate.liberc.annotation.httpmethod.Post;
import com.libernate.liberc.utils.LiberContainer;

/**
 * 企业用户联系人controller
 * 
 * @author 2013/2/28
 * 
 */
@Interceptors({ SiteAdminInterceptor.class })
@ReqPath("contact")
public class SiteContactController extends BaseController {
	private final Logger logger = Logger.getLogger(SiteContactController.class);

	@Autowired
	UserService userService;
	@Autowired
	SiteService siteService;
	@Autowired
	ContactService contactService;
	@Autowired
	ContactLogic contactLogic;
	@Autowired
	ConfService confService;
	@Autowired
	EmailService emailService;
	@Autowired
	ConfUserService confUserService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	ConfUserLogic confUserLogic;
	@Autowired
	EmpowerConfigService empowerConfigService;
	@Autowired
	OrgService orgService;

	@AsController(path = "list")
	public Object inviteContactslist(@CParam("keyword") String keyword,
			@CParam("pageNo") Integer pageNo, HttpServletRequest request) {
		UserBase currUser = userService.getCurrentSiteAdmin(request);
		PageBean<Contacts> page = contactService.getSiteContactsPage(keyword,
				currUser.getSiteId(), pageNo, currUser.getPageSize()); // 2013.6.24
																		// 因客户需求新加常量，部分每页展示偏好设置显示条数
		request.setAttribute("pageModel", page);
		request.setAttribute("keyword", keyword);
		// return new
		// ActionForward.Forward("/jsp/user/invite_contacts_list.jsp");
		return new ActionForward.Forward("/jsp2.0/admin/contact_list.jsp");
	}

	/**
	 * 进入企业联系人的信息编辑页 2013-2-28
	 */
	@AsController(path = "editContact")
	public Object toEditContact(@CParam("id") Integer contactId,
			HttpServletRequest request) {
		SiteContacts contact = contactService.getSiteContactById(contactId);
		contact = (SiteContacts) ObjectUtil.parseHtml(contact, "contactName",
				"contactDesc");
		request.setAttribute("contact", contact);
		// return new ActionForward.Forward("/jsp/user/add_contacts.jsp");
		return new ActionForward.Forward("/jsp2.0/admin/contact_edit.jsp");
	}

	/**
	 * 新建或者修改企业联系人 2013-2-28
	 */
	@AsController(path = "saveSingle")
	public Object saveContactSingle(SiteContacts contact,
			HttpServletRequest request) {
		int createFlag = ConstantUtil.CREATE_CONF_FAIL;
		String retInfo = "";
		SiteContacts contactInfo = null;
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		// contact = (Contacts) ObjectUtil.parseHtml(contact,
		// "contactName","contactDesc");
		contact.setSiteId(currentUser.getId());

		SiteBase siteBase = siteService
				.getSiteBaseById(currentUser.getSiteId());

		int moudleID = EventLogConstants.SITE_ADMIN_UPDATE_CONTACT;
		if (contact != null) {
			if (!contactLogic.contactEmailAvailableSite(contact)) {
				String errorInfo = ResourceHolder.getInstance().getResource(
						"bizconf.jsp.inviteFirst.res13");
				return StringUtil.returnJsonStr(createFlag, errorInfo);
			}
			if (contact.getId() != null && contact.getId() > 0) {
				contactInfo = contactService.updateSiteContactInfo(contact,
						currentUser);
				retInfo += ResourceHolder.getInstance().getResource(
						"bizconf.jsp.user.contact.update");

			} else {
				contactInfo = contactService.createSiteContactSingle(contact,
						currentUser);
				retInfo += ResourceHolder.getInstance().getResource(
						"bizconf.jsp.user.contact.create");
				moudleID = EventLogConstants.SITE_ADMIN_CREATE_CONTACT;
			}
			if (contactInfo != null && contactInfo.getId() != null
					&& contactInfo.getId() > 0) {
				createFlag = ConstantUtil.CREATE_CONF_SUCCEED;
				retInfo += ResourceHolder.getInstance().getResource(
						"bizconf.jsp.user.contact.success");

				eventLogService.saveAdminEventLog(
						currentUser, moudleID,
						retInfo, EventLogConstants.EVENTLOG_SECCEED, contactInfo,
						request);
			} else {
				retInfo += ResourceHolder.getInstance().getResource(
						"bizconf.jsp.user.contact.failed");
				// 创建成功后写EventLog
				eventLogService.saveAdminEventLog(
						currentUser, moudleID,
						retInfo, EventLogConstants.EVENTLOG_FAIL, contactInfo,
						request);
			}
		}
		// return new ActionForward.Forward("/jsp/user/contactsList.jsp");
		return StringUtil.returnJsonStr(createFlag, retInfo);
	}

	/**
	 * 新建（修改）联系人时验证邮箱是否已存在 wangyong 2013-3-11
	 */
	@Post
	@AsController(path = "contactEmailValidate")
	public Object contactEmailValidate(@CParam("email") String email,
			@CParam("userId") int userId, HttpServletRequest request) {
		String flag = "true";
		UserBase currentUser = userService.getCurrentUser(request);
		if (StringUtil.isNotBlank(email)) {
			Contacts contact = contactService.getContactByEmail(email,
					currentUser);
			if (contact != null && userId == 0) { // 新建联系人
				logger.info("该email已存在!");
				flag = "false";
			} else if (contact != null && userId != 0
					&& contact.getId().intValue() != userId) { // 修改联系人
				logger.info("该email已存在!");
				flag = "false";
			}
		}
		return flag;
	}

	// 显示反馈信息窗口
	@Get
	@AsController(path = "showImportInfoDialog")
	public Object showImportInfoDialog(Contacts contact,
			@CParam("successflag") String successFlag,
			@CParam("total") int total, @CParam("succnum") int succnum,
			@CParam("excelimp") boolean excelimp, HttpServletRequest request)
			throws Exception {
		String[] repts = request.getParameterValues("repeated");
		if (repts != null) {
			for (int i = 0; i < repts.length; i++) {
				repts[i] = URLDecoder.decode(repts[i], "utf-8");
			}
		}
		String[] unsaves = request.getParameterValues("unsave");
		if (unsaves != null) {
			for (int i = 0; i < unsaves.length; i++) {
				unsaves[i] = URLDecoder.decode(unsaves[i], "utf-8");
			}
		}
		request.setAttribute("repts", repts);
		request.setAttribute("unsaves", unsaves);
		request.setAttribute("successFlag", successFlag);
		request.setAttribute("total", total);
		request.setAttribute("succnum", succnum);
		request.setAttribute("excelimp", excelimp);
		return new ActionForward.Forward("/jsp2.0/user/contact_add_success.jsp");
	}

	/**
	 * 
	 * 导出联系人到Excel alanliu 2013-08-15
	 */
//	@AsController(path = "exportContacts")
//	public void exportContacts(@CParam("keyword") String keyword,
//			HttpServletRequest request, HttpServletResponse response) {
//		UserBase currentUser = userService.getCurrentUser(request);
//		// String[] contIds=request.getParameterValues("id");
//		// if(contIds==null || contIds.length <= 0){
//		// return;
//		// }
//		// System.out.println("----------->>>cIds==="+Arrays.toString(contIds));
//		List<Contacts> contacts = contactService.exportContacts(keyword, null,
//				currentUser.getSiteId(), currentUser.getId());
//		List<Object[]> exportList = new ArrayList<Object[]>();
//
//		// 表格头部
//		Object[] headers = new Object[5];
//		headers[0] = ResourceHolder.getInstance().getResource(
//				"bizconf.jsp.enContacts_list.res6");// "姓名";
//		headers[1] = ResourceHolder.getInstance().getResource(
//				"system.sysUser.list.enName");// "英文名";
//		headers[2] = ResourceHolder.getInstance().getResource(
//				"system.sysUser.list.email");// "邮箱";
//		headers[3] = ResourceHolder.getInstance().getResource(
//				"system.sysUser.list.telephone");// "电话";
//		headers[4] = ResourceHolder.getInstance().getResource(
//				"system.sysUser.list.mobile");// "手机";
//		exportList.add(headers);
//
//		// 表格内容
//		if (contacts != null && contacts.size() > 0) {
//			for (Iterator<Contacts> contact = contacts.iterator(); contact
//					.hasNext();) {
//				Contacts user = contact.next();
//				Object[] userObjs = new Object[5];
//				userObjs[0] = user.getContactName() == null ? "" : user
//						.getContactName();
//				userObjs[1] = user.getContactNameEn() == null ? "" : user
//						.getContactNameEn();
//				userObjs[2] = user.getContactEmail() == null ? "" : user
//						.getContactEmail();
//				userObjs[3] = user.getContactPhone() == null ? "" : user
//						.getContactPhone();
//				userObjs[4] = user.getContactMobile() == null ? "" : user
//						.getContactMobile();
//				exportList.add(userObjs);
//			}
//		}
//		String exportPath = request.getSession().getServletContext()
//				.getRealPath("/");
//		String exportFileName = "export/export_contacts" + Math.random()
//				+ ".xls";
//		String filePathName = exportPath + "" + exportFileName;
//		System.out
//				.println("request.getSession().getServletContext().getRealPath(\"/\")=="
//						+ request.getSession().getServletContext()
//								.getRealPath("/"));
//		try {
//			ExcelUtil.createExcel(filePathName, exportList);
//			DownLoadUtil.downloadFile(response, filePathName, true);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		//
//		// HSSFWorkbook wb = ExcelUtil.createExcelWorkbook("users", exportList);
//		// response.reset();
//		// response.setContentType("octets/stream");
//		// response.setHeader("Content-Disposition",
//		// "attachment;filename=export_contacts.xls");
//		// try {
//		// wb.write(response.getOutputStream());
//		// response.getOutputStream().flush();
//		// response.getOutputStream().close();
//		// } catch (IOException e) {
//		// e.printStackTrace();
//		// }finally{
//		// contacts = null;
//		// exportList = null;
//		// wb = null;
//		// }
//	}

	/**
	 * 从Excel文件批量导入联系人(跳转页面) wangyong 2013-3-1
	 */
	@AsController(path = "importContactsByExcel")
	public Object importContactsByExcel() {
		// return new ActionForward.Forward("/jsp/user/import.jsp");
		return new ActionForward.Forward("/jsp2.0/admin/contact_upload_list.jsp");
	}

	/**
	 * 转到导入页面
	 * 
	 * @return
	 */
	@AsController(path = "importPop")
	public Object importPop() {
		// return new
		// ActionForward.Forward("/jsp/user/contacts_import_main.jsp");
		return new ActionForward.Forward("/jsp2.0/admin/contact_import.jsp");
	}

	/**
	 * 下载联系人模板 wangyong 2013-3-1
	 */
	@AsController(path = "downloadContactsTemplate")
	public void downloadContactsTemplate(HttpServletRequest request,
			HttpServletResponse response) {
		String lang = getLang(request);
		String dirPath = LiberContainer.getContainer().getServletContext()
				.getRealPath("excel_template")
				+ File.separator;
		String tempPath = "";
		if (lang.startsWith(ConstantUtil.LANG_CN)) {
			tempPath = dirPath + "template_contact_zh.xlsx";
		} else {
			tempPath = dirPath + "template_contact_en.xlsx";
		}
		File file = new File(tempPath);
		response.setContentType("octets/stream");
		response.setHeader("Content-Disposition",
				"attachment;filename=template_contact.xlsx");
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(response.getOutputStream());
			byte[] bts = new byte[1024 * 20];
			int temp = 0;
			while ((temp = in.read(bts)) != -1) {
				out.write(bts, 0, temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.flush();
				out.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
				in.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 删除联系人 wangyong 2013-3-1
	 */
	@AsController(path = "deleteContact")
	public Object deleteContact(@CParam("id") String id,
			HttpServletRequest request) {
		boolean delFlag = false;
		// int delStatus = ConstantUtil.DELETE_CONF_FAIL;
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		
		SiteBase siteBase = siteService.getSiteBaseById(currentUser.getSiteId());
		if (StringUtil.isNotBlank(id)) {
			delFlag = contactService.deleteSiteContactInfo(
					Integer.parseInt(id), currentUser);
		}
		
		SiteContacts contacts = contactService.getSiteContactById(Integer.valueOf(id));
		
		if (delFlag) {
			// delStatus = ConstantUtil.DELETE_CONF_SUCCEED;
			setInfoMessage(
					request,
					ResourceHolder.getInstance().getResource(
							"bizconf.jsp.user.conf.invite.success"));
			
			eventLogService.saveAdminEventLog(
					userService.getCurrentSiteAdmin(request), EventLogConstants.SITE_ADMIN_DELETE_CONTACT,
					ResourceHolder.getInstance().getResource(
							"bizconf.jsp.user.conf.invite.success"), EventLogConstants.EVENTLOG_SECCEED, contacts,
					request);
		} else {
			setErrMessage(
					request,
					ResourceHolder.getInstance().getResource(
							"bizconf.jsp.user.conf.invite.failed"));
			eventLogService.saveAdminEventLog(
					userService.getCurrentSiteAdmin(request), EventLogConstants.SITE_ADMIN_DELETE_CONTACT,
					ResourceHolder.getInstance().getResource(
							"bizconf.jsp.user.conf.invite.failed"), EventLogConstants.EVENTLOG_FAIL, contacts,
					request);
		}
		return new ActionForward.Forward("/admin/contact/list");
	}

	/**
	 * 批量删除联系人 wangyong 2013-5-2
	 */
	@AsController(path = "deleteContactBatch")
	public Object deleteContactBatch(HttpServletRequest request) {
		boolean delFlag = false;
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		String[] ids = request.getParameterValues("id[]");
		if (ids != null && ids.length > 0) {
			delFlag = contactService.deleteContactInfoBatch(ids, currentUser);
		}
		if (delFlag) {
			setInfoMessage(request, "批量删除联系人成功");
			eventLogService.saveUserEventLog(currentUser,
					EventLogConstants.SITEUSER_CONF_CONTACT_DELETE,
					"批量删除联系人成功", EventLogConstants.EVENTLOG_SECCEED, null,
					request);
		} else {
			setErrMessage(request, "批量删除联系人失败");
			eventLogService
					.saveUserEventLog(currentUser,
							EventLogConstants.SITEUSER_CONF_CONTACT_DELETE,
							"批量删除联系人失败", EventLogConstants.EVENTLOG_FAIL, null,
							request);
		}
		return new ActionForward.Forward("/admin/contact/list");
	}

	/**
	 * 查询企业联系人
	 * 
	 * @param request
	 * @return
	 */
	@AsController(path = "showEnterpriseContacts")
	public Object showEnterpriseContacts(@CParam("keyword") String keyword,
			@CParam("showAll") String showAll,
			@CParam("pageNo") Integer pageNo, HttpServletRequest request) {
		UserBase currentUser = userService.getCurrentUser(request);
		// UserBase creator =
		// userService.getUserBaseById(currentUser.getCreateUser());
		// boolean showAllFlag = true;
		// boolean isSupper = true;// 默认全部显示
		// //如果找不到登录用户的创建者。认为该用户为管理员
		// if(currentUser.isSiteAdmin()||creator==null ||
		// creator.isSuperSiteAdmin()){
		// isSupper = true;
		// }
		// if(showAll != null && showAll.trim().equals("1")){
		// showAllFlag =false;
		// }
		// PageBean<UserBase> page =
		// contactService.getEnterpriseContacts(keyword, pageNo, isSupper,
		// showAllFlag,currentUser, "");
		// request.setAttribute("pageModel", page);
		request.setAttribute("showAll", showAll);
		request.setAttribute("keyword", keyword);
		List<SiteOrg> orgList = orgService.getSiteOrgList(
				currentUser.getSiteId()).getDatas();
		// if(orgList != null && orgList.size() > 0){
		// for(SiteOrg siteOrg : orgList){
		// request.setAttribute("rootOrg", siteOrg);
		// break;
		// }
		// }
		if (orgList != null && orgList.size() > 0) {
			request.setAttribute("orgList", orgList);
		}
		// return new ActionForward.Forward("/jsp/user/enContacts_list.jsp");
		return new ActionForward.Forward(
				"/jsp/user/contact_enterprise_list.jsp");
	}

	/**
	 * 查询企业联系人
	 * 
	 * @param request
	 * @return
	 */
	@AsController(path = "showEnterpriseOrgContacts")
	public Object showEnterpriseOrgContacts(@CParam("keyword") String keyword,
			@CParam("showAll") String showAll,
			@CParam("pageNo") Integer pageNo, HttpServletRequest request)
			throws Exception {
		UserBase currentUser = userService.getCurrentUser(request);
		String keywordUtf8 = "";
		if (!StringUtil.isEmpty(keyword)) {
			try {
				keywordUtf8 = URLDecoder.decode(
						request.getParameter("keyword"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("我的会议搜索转码错误" + e);
			}
			// keywordUtf8=new String(keyword.getBytes("ISO8859_1"),
			// "UTF-8");//getBytes("ISO8859_1"),"
		}
		UserBase creator = userService.getUserBaseById(currentUser
				.getCreateUser());
		SiteOrg org = orgService.getSiteOrgById(IntegerUtil
				.parseIntegerWithDefaultZero(request.getParameter("orgId")));
		String orgCode = "";
		if (org != null && org.getId() > 0) {
			orgCode = org.getOrgCode();
		}
		boolean showAllFlag = true;
		boolean isSupper = true;// 默认全部显示
		// 如果找不到登录用户的创建者。认为该用户为管理员
		if (currentUser.isSiteAdmin() || creator == null
				|| creator.isSuperSiteAdmin()) {
			isSupper = true;
		}
		if (showAll != null && showAll.trim().equals("1")) {
			showAllFlag = false;
		}

		PageBean<UserBase> page = contactService.getEnterpriseContacts(
				keywordUtf8, pageNo, isSupper, showAllFlag, currentUser,
				orgCode);
		request.setAttribute("pageModel", page);
		request.setAttribute(
				"orgId",
				(org == null || org.getId() == null || org.getId().intValue() <= 0) ? 0
						: org.getId());
		request.setAttribute("showAll", showAll);
		request.setAttribute("keyword", keywordUtf8);
		// return new
		// ActionForward.Forward("/jsp/user/invite_enContacts_org_list.jsp");
		return new ActionForward.Forward("/jsp2.0/user/enterprise_org_list.jsp");
	}

	/**
	 * 进入邀请界面
	 * 
	 * @param confId
	 * @param request
	 * @return
	 */
	@AsController(path = "goInviteContacts")
	public Object goInviteContacts(@CParam("confId") String confId,
			@CParam("isTelCall") boolean isTelCall, HttpServletRequest request) {
		request.setAttribute("confId", confId);

		UserBase user = userService.getCurrentUser(request);
		SiteBase site = siteService.getSiteBaseById(user.getSiteId());
		EmpowerConfig siteConfig = empowerConfigService
				.getSiteApplyEmpowerBySiteId(site.getId());
		EmpowerConfig config = empowerConfigService
				.getUserEmpowerConfigByUserId(user.getId());
		request.setAttribute("config", config);
		request.setAttribute("siteConfig", siteConfig);
		if (isTelCall) {
			// return new ActionForward.Forward("/jsp/user/inviteFirstTel.jsp");
			return new ActionForward.Forward("/jsp2.0/user/tel_user_edit.jsp");
		} else {
			// return new ActionForward.Forward("/jsp/user/inviteFirst.jsp");
			return new ActionForward.Forward(
					"/jsp2.0/user/invite_user_edit.jsp");
		}
	}

	/**
	 * 邀请选择主页面
	 * 
	 * @param request
	 * @return
	 */
	@AsController(path = "goContactsSelect")
	public Object goContactsSelect(HttpServletRequest request) {
		// return new
		// ActionForward.Forward("/jsp/user/inviteContactsSelect.jsp");
		return new ActionForward.Forward("/jsp/user/contact_choose.jsp");
	}

	/**
	 * 进入站点联系人管理页面
	 * 
	 * @param request
	 * @return
	 */
	@AsController(path = "goContacts")
	public Object goContacts(HttpServletRequest request) {
		return new ActionForward.Forward("/jsp2.0/admin/contacts_main.jsp");
	}

	/**
	 * 提醒未响应邀请的与会者 wangyong 2013-8-14
	 */
//	@AsController(path = "remindConfUser")
//	public Object remindConfUser(@CParam("confId") String confId,
//			HttpServletRequest request) {
//		Integer status = ConstantUtil.GLOBAL_SUCCEED_FLAG;
//		String msg = ResourceHolder.getInstance().getResource(
//				"bizconf.jsp.user.conf.invite.remind.success");
//		List<ConfUser> confUserList = confUserService
//				.getNoResponseUserList(IntegerUtil
//						.parseIntegerWithDefaultZero(confId));
//		if (confUserList != null && confUserList.size() > 0) {
//			ConfBase conf = confService.getConfBasebyConfId(IntegerUtil
//					.parseIntegerWithDefaultZero(confId));
//			if (!emailService.remindUserNotResponse(confUserList, conf)) {
//				status = ConstantUtil.GLOBAL_FAIL_FLAG;
//				msg = ResourceHolder.getInstance().getResource(
//						"bizconf.jsp.user.conf.invite.remind.failed");
//			}
//		}
//		return StringUtil.returnJsonStr(status, msg);
//	}

	/**
	 * 邀请联系人参会
	 * 
	 * @param data
	 * @return
	 */
	@AsController(path = "sendinvites")
	public Object inviteContacts(@CParam("data") String data,
			HttpServletRequest request) {
		logger.info("will invite user ! ");
		UserBase currUser = userService.getCurrentUser(request);
		SiteBase currSite = siteService.getSiteBaseById(currUser.getSiteId());
		Integer status = ConstantUtil.GLOBAL_SUCCEED_FLAG;
		String msg = ResourceHolder.getInstance().getResource(
				"bizconf.jsp.user.conf.invite.success");
		List<ConfUser> users = new ArrayList<ConfUser>();
		List<UserBase> ubs = new ArrayList<UserBase>();

		List<ConfUser> unStoredUsers = new ArrayList<ConfUser>();
		try {
			JSONObject jObject = JSONObject.fromObject(data);
			Integer confId = Integer.parseInt(jObject.get("confId").toString());
			Boolean isCalling = Boolean.parseBoolean(jObject.get("isCalling")
					.toString());
			ConfBase confInfo = confService.getConfBasebyConfId(confId);
			JSONArray jsonArray = jObject.getJSONArray("users");

			// List<ConfUser> invertedUsers =
			// confUserLogic.getConfUserList(confId);
			for (int i = 0; i < jsonArray.size(); i++) {
				ConfUser cu = (ConfUser) JsonUtil.parseObjectWithJsonString(
						jsonArray.get(i).toString(), ConfUser.class);
				users.add(cu);
				if (!StringUtil.isEmpty(cu.getUserEmail())
						&& !contactService.emailContactStored(
								cu.getUserEmail(), currUser)) {
					unStoredUsers.add(cu);
				} else if (!StringUtil.isEmpty(cu.getTelephone())
						&& !contactService.telContactStored(cu.getTelephone(),
								currUser)) {
					// unStoredUsers.add(cu);
				}
				UserBase ub = new UserBase();
				ub.setId(0);
				if (cu.getUserId() != null && cu.getUserId() > 0) {
					ub.setId(cu.getUserId());
				}
				ub.setTrueName(cu.getUserName());
				if (ub.getTrueName() == null || ub.getTrueName().equals("")) {
					ub.setTrueName(cu.getUserEmail());
				}
				ub.setUserEmail(cu.getUserEmail());
				ub.setPhone(cu.getTelephone());
				if (ub.getTrueName() == null || ub.getTrueName().equals("")) {
					ub.setTrueName(cu.getTelephone());
				}
				ubs.add(ub);
			}
			// invertedUsers.addAll(users);
			// String retInfo = confManagementService.updateConf(confInfo,
			// invertedUsers, currSite, currUser);
			// if(retInfo.equals(ConstantUtil.AS_MORE_THAN_MAXNUM)){
			// return StringUtil.returnJsonStr(ConstantUtil.GLOBAL_FAIL_FLAG,
			// "到达会议人数上限");
			// }else if(ConstantUtil.AS_BEGIN_TIME_INVALID.equals(retInfo)){
			// return StringUtil.returnJsonStr(ConstantUtil.GLOBAL_FAIL_FLAG,
			// "会议已开始,请从会议客户端邀请参会人");
			// }else if(!retInfo.equals(ConstantUtil.AS_SUCCESS_CODE)){
			// throw new
			// RuntimeException("invoking the soap invite interface error errorcode:"+retInfo);
			// }

			boolean flag = confUserService.fillConfUserForInvite(confInfo, ubs,1);
			// 群呼时使用
			logger.info("will bacth call attendee ");
			if (ConfConstant.CONF_STATUS_OPENING.equals(confInfo
					.getConfStatus())) {
				 
			}

			if (flag) {
				flag = emailService.sendInviteToConfUser(users, confInfo);
			}
			if (!flag) {
				status = ConstantUtil.GLOBAL_FAIL_FLAG;
				msg = ResourceHolder.getInstance().getResource(
						"bizconf.jsp.user.conf.invite.failed");
			}
			request.setAttribute("userList", unStoredUsers);
			request.setAttribute("isCalling", isCalling);
		} catch (Exception e) {
			e.printStackTrace();
			status = ConstantUtil.GLOBAL_FAIL_FLAG;
			msg = ResourceHolder.getInstance().getResource(
					"bizconf.jsp.user.conf.invite.failed");
			return new ActionForward.Forward("/jsp2.0/user/invite_failed.jsp");
		}
		// return StringUtil.returnJsonStr(status, msg);
		return new ActionForward.Forward("/jsp2.0/user/invite_success.jsp");
	}

	@AsController(path = "saveAsContacts")
	public Object saveAsContacts(@CParam("data") String data,
			HttpServletRequest request) {
		// logger.info("will invite user ! ");
		UserBase currUser = userService.getCurrentUser(request);
		int status = ConstantUtil.GLOBAL_SUCCEED_FLAG;
		String msg = "";
		if (data != null) {
			JSONObject jObject = JSONObject.fromObject(data);
			JSONArray jsonArray = jObject.getJSONArray("users");
			for (int i = 0; i < jsonArray.size(); i++) {
				Contacts cu = (Contacts) JsonUtil.parseObjectWithJsonString(
						jsonArray.get(i).toString(), Contacts.class);

				if (StringUtil.isEmpty(cu.getContactName())) {
					cu.setContactName(cu.getContactEmail());
				}
				if (StringUtil.isEmpty(cu.getContactName())) {
					cu.setContactName(cu.getContactPhone());
				}
				cu.setSiteId(currUser.getSiteId());
				cu.setUserId(currUser.getId());
				cu.setCreateUser(currUser.getId());
				cu.setCreateTime(new Date());
				contactService.saveContact(cu);
			}
		} else {
			status = ConstantUtil.GLOBAL_FAIL_FLAG;
		}
		return StringUtil.returnJsonStr(status, msg);
	}

	/**
	 * 查询站点下的组织机构 1.查出该组织机构的下一级组织机构(不包括自己) 2.组织机构共4层 wangyong 2013-5-6
	 */
	@AsController(path = "orgListForJson/{id:([0-9]+)}")
	@Get
	public Object orgListForJson(@CParam("id") String id,
			HttpServletRequest request) {
		JSONObject json = new JSONObject();
		UserBase userBase = userService.getCurrentUser(request);
		PageBean<SiteOrg> pageModel = orgService.getSubOrgOnlyList(
				userBase.getSiteId(), Integer.parseInt(id));
		Map<Integer, Integer> participantSizeList = getParticipants(pageModel
				.getDatas()); // 获取每个机构人数
		request.setAttribute("participantSizeList", participantSizeList);
		request.setAttribute("pageModel", pageModel);

		json.put("status", ConstantUtil.CREATE_CONF_SUCCEED);
		JSONArray jsonArrOrgUser = JSONArray.fromObject(pageModel.getDatas());
		json.put("orgUserList", jsonArrOrgUser);

		return json.toString();
	}

	/**
	 * 获取每个组织机构的人数 wangyong 2013-3-25
	 */
	private Map<Integer, Integer> getParticipants(List<SiteOrg> orgList) {
		Map<Integer, Integer> ParticipantsMap = new HashMap<Integer, Integer>();
		if (orgList != null && orgList.size() > 0) {
			for (SiteOrg org : orgList) {
				List<UserBase> Participants = orgService.getOrgUserList(org);
				ParticipantsMap.put(org.getId(), Participants == null ? 0
						: Participants.size());
			}
		}
		return ParticipantsMap;
	}
	
	/**
	 * 
	 * 批量导入联系人
	 * 
	 * @param file 导入的Excel模板
	 * @since 2014-08-20
	 * @author Darren
	 */
	@AsController(path = "importBatchByContacts")
	public Object importBatchByContacts(@CParam("excelfile") LiberCFile file,
			HttpServletRequest request) {
		int statu = ConstantUtil.GLOBAL_SUCCEED_FLAG;
		UserBase currentUser = userService.getCurrentSiteAdmin(request);
		if (file != null) {
			try {
				List<SiteContacts> importusers = new ArrayList<SiteContacts>(); // 可成功导入的用户
				List<SiteContacts> unSaveableusers = new ArrayList<SiteContacts>(); // 数据格式不对或者不全的
				List<Object[]> datas = ExcelUtil
						.getDataByInputStream(file.getInputStream(),
								file.getOriginalFilename(), 2, 0);
				BeanUtil.trimObjs(datas);
				// 用于显示总共多少数据
				request.setAttribute("itemnum", datas.size());
				for (Iterator<Object[]> it = datas.iterator(); it.hasNext();) {
					Object[] objs = it.next();
					SiteContacts contact = new SiteContacts();
					for (int i = 0; i < objs.length; i++) {
						if (i == 0) {
							contact.setContactName(objs[0] == null ? "": objs[0].toString().trim());
						}
						if (i == 1) {
							contact.setContactNameEn(objs[1] == null?"": objs[1].toString().trim());
						}
						if (i == 2) {
							contact.setContactEmail(objs[2] == null ? "": objs[2].toString().trim());
						}
						if (i == 3) {
							contact.setContactPhone(objs[3] == null ? "": objs[3].toString().trim());
						}
						if (i == 4) {
							contact.setContactMobile(objs[4] == null ? "": objs[4].toString().trim());
						}
						if (i == 5) {
							contact.setContactDesc(objs[5] == null ? "": objs[5].toString().trim());
						}
					}
					contact.setSiteId(currentUser.getSiteId());
					contact.setCreateUser(currentUser.getId());
					contact.setCreateTime(DateUtil.getGmtDate(null));
//					contact.setContactDesc("");

					//取出掉Excel中的格式不正确的记录
					if(!contactLogic.createSiteContactSingleValidate(contact,currentUser)){
						unSaveableusers.add(contact);
						eventLogService.saveAdminEventLog(currentUser,
								EventLogConstants.SITE_ADMIN_BATCHADD_CONTACT,"【"+contact.getContactName()+"】"+ResourceHolder.getInstance().getResource("admin.site.userimport.infotail"), EventLogConstants.EVENTLOG_FAIL,
								currentUser, request);
					}else {
						contact.initContact();
						importusers.add(contact);
					}
				}
				// 可以成功导入的
				if (importusers.size() > 0) {
					boolean flag = contactService.importSiteContactBatch(importusers);
					if (flag) {
						request.setAttribute("imported", importusers);
					} else {
						eventLogService.saveAdminEventLog(currentUser,
								EventLogConstants.SITE_ADMIN_BATCHADD_CONTACT,ResourceHolder.getInstance().getResource("bizconf.jsp.user.contact.import.failed"), EventLogConstants.EVENTLOG_FAIL,
								currentUser, request);
						throw new RuntimeException("import contacts failed!");
					}
				}
				request.setAttribute("iptitemnum", importusers.size());//导入的数
				//格式不正确的有
				if (unSaveableusers.size() > 0) {
					request.setAttribute("dissaveable", unSaveableusers);
				}else{
					eventLogService.saveAdminEventLog(currentUser,
							EventLogConstants.SITE_ADMIN_BATCHADD_CONTACT,ResourceHolder.getInstance().getResource("bizconf.jsp.user.contact.import.success"), EventLogConstants.EVENTLOG_SECCEED,
							currentUser, request);
				}
				request.setAttribute("excelimp", true);
				request.setAttribute("statu", statu);
				request.setAttribute("disitemnum", datas.size()-importusers.size());//导入失败的数
				
				
//				eventLogService.saveAdminEventLog(currentUser,
//						EventLogConstants.SITE_ADMIN_BATCHADD_CONTACT,ResourceHolder.getInstance().getResource("bizconf.jsp.user.contact.import.success"), EventLogConstants.EVENTLOG_SECCEED,
//						null, request);
			} catch (Exception e) {
				logger.error("从Excel文件批量导入联系人出错！" + e);
				request.setAttribute("statu", ConstantUtil.GLOBAL_FAIL_FLAG);
				request.setAttribute("info", ResourceHolder.getInstance()
						.getResource("bizconf.jsp.user.contact.import.failed"));
				
				eventLogService.saveAdminEventLog(currentUser,
						EventLogConstants.SITE_ADMIN_BATCHADD_CONTACT,ResourceHolder.getInstance().getResource("bizconf.jsp.user.contact.import.failed"), EventLogConstants.EVENTLOG_FAIL,
						null, request);
			}
			
		}
		return new ActionForward.Forward("/jsp2.0/admin/import_callback_new.jsp");
	}
}
