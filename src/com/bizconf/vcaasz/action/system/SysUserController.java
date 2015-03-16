package com.bizconf.vcaasz.action.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.language.ResourceHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.constant.EventLogConstants;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.EmailService;
import com.bizconf.vcaasz.service.EventLogService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.JsonUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.encrypt.MD5;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
import com.libernate.liberc.annotation.httpmethod.Post;

@Interceptors({SystemUserInterceptor.class})
@ReqPath("sysUser")
public class SysUserController extends BaseController {
	private final  Logger logger = Logger.getLogger(SiteController.class);
	
	@Autowired
	UserService userService;
	@Autowired
	EventLogService eventLogService;
	@Autowired
	EmailService emailService;
	
	/**
	 * 获取系统管理员信息列表
	 * wangyong
	 * 2013-2-5
	 * 
	 */
	@AsController(path = "list")
	public Object list(PageModel pageModel, HttpServletRequest request) throws Exception{
		List<SystemUser> systemUserList = null;
		Integer rows = 0;
		try {
			rows = userService.CountSystemUser(null);
			logger.info("rows=="+rows);
		} catch (Exception e) {
			logger.error("获取系统管理员信息列表页数出错!"+e);
		}
		pageModel.setRowsCount(rows);
		try {
			systemUserList = userService.getSystemUserList(null, pageModel);
			logger.info("systemUserList=="+systemUserList);
		} catch (Exception e) {
			logger.error("获取系统管理员信息列表出错!"+e);
		}
		request.setAttribute("systemUserList", systemUserList);
		request.setAttribute("pageModel", pageModel);
		return new ActionForward.Forward("/jsp/system/sysUser_list.jsp");
	}
	
	/**
	 * 新建系统管理员
	 * wangyong
	 * 2013-2-5
	 */
	@AsController(path = "new")
	public Object createSystemUser() throws Exception{
		return new ActionForward.Forward("/jsp/system/createSystemUser.jsp");
	}
	
	/**
	 * 创建系统管理员
	 * 说明：
	 * 1. 登录名不能重复
	 * 2. 邮箱不能重复
	 * 3. 创建系统管理员，要给系统管理员发封邮件
	 * 4. 校验数据放到service的logic中
	 * 5. 由martin改造新增客服管理员
	 * wangyong
	 * 2013-2-5
	 */
	@Post
	@AsController(path = "create")
	public Object saveSystemUser(@CParam("data") String data, HttpServletRequest request) throws Exception{
		Object[] user = JsonUtil.parseObjectArrWithJsonString(data);
		SystemUser systemUser = (SystemUser) JsonUtil.parseObjectWithJsonString(user[0].toString(), SystemUser.class);
		logger.info("systemUser-----"+systemUser);
		SystemUser currentSystemUser = userService.getCurrentSysAdmin(request);
		SystemUser sysUser = null;
		//获取密码，发邮件时候使用
		String loginpass = "";
		String createSuccess = ResourceHolder.getInstance().getResource("system.admin.create.succeed");
		String createFailed = ResourceHolder.getInstance().getResource("system.admin.create.failed");
		String exists = ResourceHolder.getInstance().getResource("system.admin.create.exists");
		if(ConstantUtil.USERTYPE_SYS_SERVER.equals(systemUser.getSysType())){
			createSuccess = ResourceHolder.getInstance().getResource("system.service.create.succeed");
			createFailed = ResourceHolder.getInstance().getResource("system.service.create.failed");
			exists = ResourceHolder.getInstance().getResource("system.service.create.exists");
		}
		
//		String roleStr = "管理员";
//		roleStr = ResourceHolder.getInstance().getResource("system.sysUser.delete.");
//		if(ConstantUtil.USERTYPE_SYS_SERVER.equals(systemUser.getSysType())){
//			roleStr = "客服";
//		}
		if(systemUser != null){
			if(StringUtil.isNotBlank(systemUser.getLoginName())){
				if("false".equals(loginNameValidate(systemUser.getLoginName(), 0))){
					return returnJsonStr(ConstantUtil.CREATESYSTEMUSER_FAIL, exists);
				}
			}
			if(StringUtil.isNotBlank(systemUser.getEmail())){
				if("false".equals(emailValidate(systemUser.getEmail(), 0))){
					return returnJsonStr(ConstantUtil.CREATESITE_FAIL, ResourceHolder.getInstance().getResource("system.service.email.exists"));
				}
			}
			systemUser = initSystemUser(systemUser, currentSystemUser.getId());	//初始化systemUser对象
			
			if(StringUtil.isNotBlank(systemUser.getLoginPass())){
				loginpass = systemUser.getLoginPass();
				systemUser.setLoginPass(MD5.encodePassword(systemUser.getLoginPass(), "MD5"));
			}
			try{
				sysUser = userService.createSystemUser(systemUser);
			}catch(Exception e){
				return returnJsonStr(ConstantUtil.CREATESYSTEMUSER_FAIL, createFailed);
			}
			try{
				if(sysUser != null){
					sysUser.setLoginPass(loginpass);//设置获取的密码 用于邮件发送
					emailService.createSystemUserEmail(sysUser);                //创建成功后发送邮件
					eventLogService.saveSystemEventLog(currentSystemUser, 
							EventLogConstants.SYSTEM_SYSTEMUSER_CREATE, createSuccess, 
							EventLogConstants.EVENTLOG_SECCEED, sysUser, request);   //创建成功后写EventLog
					logger.info("创建系统管理员成功");
				}else{
					eventLogService.saveSystemEventLog(currentSystemUser, 
							EventLogConstants.SYSTEM_SYSTEMUSER_CREATE, createFailed, 
							EventLogConstants.EVENTLOG_FAIL, sysUser, request);   //创建失败后写EventLog
					return returnJsonStr(ConstantUtil.CREATESYSTEMUSER_FAIL, createFailed);
				}
			}catch(Exception e){
				eventLogService.saveSystemEventLog(currentSystemUser, 
						EventLogConstants.SYSTEM_SYSTEMUSER_CREATE, createFailed, 
						EventLogConstants.EVENTLOG_FAIL, sysUser, request);   //创建失败后写EventLog
			}
		}
		return returnJsonStr(ConstantUtil.CREATESYSTEMUSER_SUCCEED, createSuccess);
	}
	
	/**
	 * 初始化系统管理员信息
	 * wangyong
	 * 2013-2-5
	 */
	private SystemUser initSystemUser(SystemUser systemUser, int currentSysUserId){
		systemUser.setCreateTime(DateUtil.getGmtDate(null));   //创建时间初始化为GMT时间
		systemUser.setCreateUser(currentSysUserId);
		systemUser.setDelFlag(ConstantUtil.DELFLAG_UNDELETE);
		try {
			systemUser.setDelTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00")));
			systemUser.setLastErrorTime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00")));
		} catch (ParseException e) {
			logger.error("初始化系统管理员删除时间字段错误！");
		}
		systemUser.setDelUser(0);
		systemUser.setErrorCount(0);
		if(systemUser.getSysType()==null){
			systemUser.setSysType(ConstantUtil.USERTYPE_SYSTEM);
		}
		systemUser.setTelephone("");
		systemUser.setPassEditor(currentSysUserId);
		return systemUser;
	}
	
	/**
	 * 创建(修改)系统管理员时验证登录名是否已存在
	 * return true(不存在) false(已存在)
	 * @param id 系统管理员id
	 * wangyong
	 * 2013-2-5
	 */
	@Post
	@AsController(path = "loginNameValidate")
	public String loginNameValidate(@CParam("loginName") String loginName, @CParam("userId") int userId){
		String flag = "true";
		if(StringUtil.isNotBlank(loginName)){
			SystemUser systemUser = userService.getSystemUserByLoginName(loginName);
			if(systemUser != null && userId == 0){    //创建系统管理员
				logger.info("该系统管理员登录名"+loginName+"已存在!");
				flag = "false";
			}else if(systemUser != null && userId != 0 && systemUser.getId().intValue() != userId){    //修改系统管理员
				logger.info("该系统管理员登录名"+loginName+"已存在!");
				flag = "false";
			}
		}
		return flag;
	}
	
	/**
	 * 创建(修改)系统管理员时验证邮箱是否已存在
	 * return true(不存在) false(已存在)
	 * wangyong
	 * 2013-1-31
	 */
	@Post
	@AsController(path = "emailValidate")
	public String emailValidate(@CParam("userEmail") String userEmail, @CParam("userId") int userId){
		String flag = "true";
		if(StringUtil.isNotBlank(userEmail)){
			SystemUser systemUser = userService.getSystemUserByEmail(userEmail.trim());
			if(systemUser != null && userId == 0){    //创建站点
				logger.info("邮箱名"+userEmail+"已存在!");
				flag = "false";
			}else if(systemUser != null && userId != 0 && systemUser.getId().intValue() != userId){    //修改站点
				logger.info("邮箱名"+userEmail+"已存在!");
				flag = "false";
			}
		}
		return flag;
	}
	
	/**
	 * 修改系统管理员
	 * wangyong
	 * 2013-2-5
	 */ 
	@AsController(path = "update/{id:([0-9]+)}")
	@Get
	public Object updateSysUser(@CParam("id") Integer id,HttpServletRequest request) throws Exception{
		logger.info("id=="+id);
		SystemUser systemUser = userService.getSystemUserById(id);
		systemUser.setLoginPass("");     //传到前台的密码置空
		request.setAttribute("systemUser", systemUser);
		return new ActionForward.Forward("/jsp/system/createSystemUser.jsp");
	}
	
	/**
	 * 修改系统管理员
	 * 说明：
	 * 1. 修改系统管理员后发封邮件通知
	 * 2. 校验数据放到service的logic中
	 * 3. 校验loginname是否已存在
	 * 4. 由martin改造修改客服管理员
	 * wangyong
	 * 2013-2-5
	 */
	@AsController(path = "update")
	@Post
	public Object updateSystemUser(@CParam("data") String data, HttpServletRequest request){
		Object[] user = JsonUtil.parseObjectArrWithJsonString(data);
		SystemUser systemUser = (SystemUser) JsonUtil.parseObjectWithJsonString(user[0].toString(), SystemUser.class);
		logger.info("systemUser-----"+systemUser);
		SystemUser sysUser = null;
		String loginpass = "";//发送邮件用  如果有修改密码
		
		SystemUser orgUser = userService.getSystemUserById(systemUser.getId());
		
		String updateSuccess = ResourceHolder.getInstance().getResource("system.admin.update.succeed");
		String updateFailed = ResourceHolder.getInstance().getResource("system.admin.update.failed");
		String exists = ResourceHolder.getInstance().getResource("system.admin.create.exists");
		if(orgUser != null && ConstantUtil.USERTYPE_SYS_SERVER.equals(orgUser.getSysType())){
			updateSuccess = ResourceHolder.getInstance().getResource("system.service.update.succeed");
			updateFailed = ResourceHolder.getInstance().getResource("system.service.update.failed");
			exists = ResourceHolder.getInstance().getResource("system.service.create.exists");
		}
		
//		String roleStr = "管理员";
//		if(orgUser!=null && ConstantUtil.USERTYPE_SYS_SERVER.equals(orgUser.getSysType())){
//			roleStr = "客服";
//		}
		if(systemUser != null){
			SystemUser currentSystemUser = userService.getCurrentSysAdmin(request);
			if(StringUtil.isNotBlank(systemUser.getLoginPass())){     //若管理员修改用户密码，则记录操作人ID
				systemUser.setPassEditor(currentSystemUser.getId());
			}
			if(StringUtil.isNotBlank(systemUser.getLoginName())){
				if("false".equals(loginNameValidate(systemUser.getLoginName(), systemUser.getId()))){
					return returnJsonStr(ConstantUtil.UPDATESYSTEMUSER_FAIL, exists);
				}
			}
			if(StringUtil.isNotBlank(systemUser.getEmail())){
				if("false".equals(emailValidate(systemUser.getEmail(), systemUser.getId()))){
					return returnJsonStr(ConstantUtil.CREATESITE_FAIL, ResourceHolder.getInstance().getResource("system.service.email.exists"));
				}
			}
			if(StringUtil.isNotBlank(systemUser.getLoginPass())){
				loginpass = systemUser.getLoginPass();
				systemUser.setLoginPass(MD5.encodePassword(systemUser.getLoginPass(), "MD5"));
			}
			try {
				sysUser = userService.updateSystemUser(systemUser);
			}catch (Exception e){
				return returnJsonStr(ConstantUtil.CREATESYSTEMUSER_FAIL, updateFailed);
			}
			try{
				if(sysUser != null){
					sysUser.setLoginPass(loginpass);//发送邮件如果有修改密码
					emailService.updateSystemUserEmail(sysUser);                //修改成功后发送邮件
					eventLogService.saveSystemEventLog(currentSystemUser,
							EventLogConstants.SYSTEM_SYSTEMUSER_UPDATE, updateSuccess, 
							EventLogConstants.EVENTLOG_SECCEED, sysUser, request);   //修改成功后写EventLog
				}else{
					eventLogService.saveSystemEventLog(currentSystemUser, 
							EventLogConstants.SYSTEM_SYSTEMUSER_UPDATE, updateFailed, 
							EventLogConstants.EVENTLOG_FAIL, sysUser, request);   //修改失败后写EventLog
					return returnJsonStr(ConstantUtil.UPDATESITE_FAIL, updateFailed);
				}
			} catch (Exception e) {
				eventLogService.saveSystemEventLog(currentSystemUser, 
						EventLogConstants.SYSTEM_SYSTEMUSER_UPDATE, updateFailed, 
						EventLogConstants.EVENTLOG_FAIL, sysUser, request);   //修改失败后写EventLog
			}
		}
		return returnJsonStr(ConstantUtil.UPDATESYSTEMUSER_SUCCEED, updateSuccess);
	}
	
	/**
	 * 删除系统管理员
	 * wangyong
	 * 2013-1-16
	 */
	@AsController(path = "delete/{id:([0-9]+)}")
	public Object delSysUser(@CParam("id") Integer id, HttpServletRequest request) throws Exception{
		logger.info("id=="+id);
		SystemUser currentSystemUser = userService.getCurrentSysAdmin(request);
		
		SystemUser orgUser = userService.getSystemUserById(id);
		
		
		String deleteSuccess = ResourceHolder.getInstance().getResource("system.admin.delete.succeed");
		String deleteFailed = ResourceHolder.getInstance().getResource("system.admin.delete.failed");
		if(orgUser!=null && ConstantUtil.USERTYPE_SYS_SERVER.equals(orgUser.getSysType())){
			deleteSuccess = ResourceHolder.getInstance().getResource("system.service.delete.succeed");
			deleteFailed = ResourceHolder.getInstance().getResource("system.service.delete.failed");
		}
		
//		String roleStr = "管理员";
//		if(orgUser!=null && ConstantUtil.USERTYPE_SYS_SERVER.equals(orgUser.getSysType())){
//			roleStr = "客服";
//		}
		boolean delFlag = userService.delSystemUser(id, currentSystemUser);
		int delStatus = ConstantUtil.DELSITE_SUCCEED;
		if(delFlag){
			//emailService.updateSystemUserEmail(id);系统管理员删除成功发送邮件通知
			eventLogService.saveSystemEventLog(currentSystemUser, 
					EventLogConstants.SYSTEM_SITE_DELETE, deleteSuccess, 
					EventLogConstants.EVENTLOG_SECCEED, id, request);   //删除站点成功后写EventLog
			setInfoMessage(request, ResourceHolder.getInstance().getResource("system.sysUser.delete." + delStatus));
		}else{
			delStatus = ConstantUtil.DELSITE_FAIL;
			eventLogService.saveSystemEventLog(currentSystemUser, 
					EventLogConstants.SYSTEM_SITE_DELETE, deleteFailed, 
					EventLogConstants.EVENTLOG_FAIL, id, request);   //删除站点失败后写EventLog
			setErrMessage(request, ResourceHolder.getInstance().getResource("system.sysUser.delete." + delStatus));
		}
		return new ActionForward.Forward("/system/sysUser/list");
	}
	
	/**
	 * 返回json字符串对象(创建，修改系统管理员出错时)
	 * status 失败
	 * object 失败原因
	 * wangyong
	 * 2013-2-5
	 */
	private String returnJsonStr(int status, Object object){
		JSONObject json = new JSONObject();
		JSONArray jsonArrSite = new JSONArray();
		json.put("status", status);
		jsonArrSite.add(object.toString());
		json.put("message", jsonArrSite);
		return json.toString();
	}
}
