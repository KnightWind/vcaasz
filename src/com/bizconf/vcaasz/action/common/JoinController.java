package com.bizconf.vcaasz.action.common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.encrypt.MD5;
import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.TwoDimensionCode;
import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.JoinRandom;
import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.interceptors.SiteStatusInterceptor;
import com.bizconf.vcaasz.service.ClientAPIService;
import com.bizconf.vcaasz.service.ConfService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.BrowserUtil;
import com.bizconf.vcaasz.util.BrowserUtil.Client;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.DownLoadUtil;
import com.bizconf.vcaasz.util.HttpsUtils;
import com.bizconf.vcaasz.util.IntegerUtil;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.vcaasz.util.UserAgentUtils;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.utils.LiberContainer;

/**
 * 入会解析
 * */
@ReqPath({"/join","/j"})
@Interceptors(SiteStatusInterceptor.class)
public class JoinController extends BaseController {
	private final Logger logger = Logger.getLogger(JoinController.class);
	
	@Autowired
	ConfService confService;
	@Autowired
	UserService userService;
	@Autowired
	SiteService siteService;
	@Autowired
	ClientAPIService clientAPIService;
	
	static Object lock = new Object();
	
	/**
	 * 改版的客户端入会连接解析
	 * @param zoomId : zoom会议ID
	 * @param encrypt : 加密后的入会密码
	 * 
	 * */
	@AsController(path = "/{zoomId:([0-9]+)}")
	public Object gotoJoinForClient(@CParam("zoomId") String zoomId , HttpServletRequest request){
		
		String encrypt = request.getParameter("pwd");
		//检测安装环境
		String clientDownLoadUrl = "";
		ResourceBundle bundle = ResourceBundle.getBundle(ConstantUtil.CONFIG_CONF_PROPERTIES);
		String UserAgent = request.getHeader("User-Agent");//区分移动端和pc端
		String os = UserAgentUtils.checkOS(UserAgent);
		
		//查询对应会议记录
		ConfBase confBase = confService.getConfBaseByZoomId(zoomId);
		request.setAttribute("blank", "1");//非弹层入会
		if(confBase == null){
			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
			if(ConstantUtil.OS_MOBILE.equals(os) 
							|| ConstantUtil.OS_IPHONE.equals(os)
							|| ConstantUtil.OS_IPAD.equals(os) 
							|| ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
				return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
			}
			return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");//PC错误页面
		}

		SiteBase siteBase = siteService.getSiteBaseById(confBase.getSiteId());
		if(siteBase == null){
			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
			if(ConstantUtil.OS_MOBILE.equals(os) 
							|| ConstantUtil.OS_IPHONE.equals(os)
							|| ConstantUtil.OS_IPAD.equals(os) 
							|| ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
				return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
			}
			return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");//PC错误页面
		}
		
		String brand = SiteIdentifyUtil.getCurrentBrand();
		if ("www".equalsIgnoreCase(brand)) {
			String redirectUrl = "http://" + siteBase.getSiteSign() + "." + SiteIdentifyUtil.MEETING_CENTER_DOMAIN + "/j/" + zoomId ;
			if(!StringUtil.isEmpty(encrypt))
				redirectUrl += "?pwd="+encrypt;
			return new ActionForward.Redirect(redirectUrl);
		}
		
		encrypt = (encrypt!=null)?encrypt:"";
		request.setAttribute("pwd", encrypt);//必须使用参数传递过来的密码 
		//通过主持人id获取Token
		UserBase tokenUserBase = userService.getUserBaseById(confBase.getCompereUser());
		request.setAttribute("token", tokenUserBase.getZoomToken());
		request.setAttribute("hostId", tokenUserBase.getZoomId());
		request.setAttribute("uname", tokenUserBase.getTrueName());
		request.setAttribute("UUID", confBase.getUuid());//uuid 
		request.setAttribute("action", "join");
		request.setAttribute("conf", JSONObject.fromObject(confBase));
		
		request.setAttribute("zoom_j_min", bundle.getString("zoom_j_min"));
		request.setAttribute("zoom_all_min", bundle.getString("zoom_all_min"));
		request.setAttribute("zoom_http", bundle.getString("zoom_http"));
		
		//识别出来的系统
		request.setAttribute("system", os);
		if(ConstantUtil.OS_MOBILE.equals(os) 
				|| ConstantUtil.OS_IPHONE.equals(os)
				|| ConstantUtil.OS_IPAD.equals(os) 
				|| ConstantUtil.OS_ANDROID.equals(os)){		//移动客户端成功跳转
			//mobile的app下载地址
			String apk = bundle.getString(ConstantUtil.OS_ANDROID);
			String ipk = bundle.getString(ConstantUtil.OS_IPHONE);
			request.setAttribute("apk", apk);
			request.setAttribute("ipk", ipk);
			return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile.jsp");
		}else if(ConstantUtil.OS_WIN7.equals(os)){
			clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WIN7);
		}else if(ConstantUtil.OS_WINXP.equals(os)) {
			clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WINXP);
		}else if(ConstantUtil.OS_MAC.equals(os)){
			clientDownLoadUrl = bundle.getString(ConstantUtil.OS_MAC);
		}
		//在pc上使用终端下载地址
		request.setAttribute("clientDownLoadUrl", clientDownLoadUrl);
		return new ActionForward.Forward("/jsp/common/zoomJoinPage_outter.jsp");//pc端非弹层成功页面
	}
	
	
	/**
	 * 短链接入会解析
	 * @param confInfo 是由ConfId和joinType组成的字符串
	 * */
	@AsController(path = "/{confInfo:([0-9]+)}/{token:([0-9a-zA-Z]+)}")
	public Object gotojoinPage(@CParam("confInfo") String confInfo,@CParam("token")String token , HttpServletRequest request){
		
		if(StringUtil.isEmpty(confInfo)){
			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
			return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
		}
		int len = confInfo.length();
		String joinType = confInfo.substring(len-1, len);
		String confId = confInfo.substring(0, len-1);
		return gotojoinPage(joinType, null, confId, null, token, null, 0, request);
	}
	
	/**
	 * 检测clickOnce url是否可用 可用返回clickOnce baseURL
	 * 
	 * */
	@AsController(path = "/getClickOnceStatu")
	public Object getClickOnceStatu(HttpServletRequest request){
		JSONObject json = new JSONObject();
		ResourceBundle bundle = ResourceBundle.getBundle(ConstantUtil.CONFIG_CONF_PROPERTIES);
		String clickOnceUrl = bundle.getString(ConstantUtil.CLICKONCE_URL);
		//clickOnce application 可用
		if(!clickOnceUrl.startsWith("http")){
			String basepath = LiberContainer.getContainer().getServletContext().getRealPath("/");
			String filePath = basepath+java.io.File.separator+clickOnceUrl;
			File app = new File(filePath);
			if(app.exists()){
				json.put("state", "1");
				json.put("url", clickOnceUrl);
			}else{
				json.put("state", "0");
			}
		}
		else if(HttpsUtils.URLenable(clickOnceUrl)){
			json.put("state", "1");
			json.put("url", clickOnceUrl);
		}else{
			json.put("state", "0");
		}
		return json.toString();
	}
	
	/**
	 * 针对于首页一键入会IE
	 * */
	@AsController(path = "/ie/{confInfo:([0-9]+)}")
	public Object joinMeetingByConfNo(@CParam("confInfo") String confInfo,HttpServletRequest request){
		JSONObject json = new JSONObject();
		
		ConfBase perConf = confService.getConfBase(null,confInfo);
		if (perConf == null) {
			perConf = confService.getConfBaseByZoomPMI(confInfo);
		}
		if(perConf == null){
			json.put("status", 1);
			json.put("message","会议ID不正确");
			return json;
		}
		//通过主持人id获取Token
		UserBase tokenUserBase = userService.getUserBaseById(perConf.getCompereUser());
		if(tokenUserBase == null){
			json.put("status", 2);
			json.put("message","该会议ID主持人不存在");
			return json;
		}
		json.put("status", 0);
		json.put("UUID",perConf.getUuid());
//		json.put("token",tokenUserBase.getZoomToken());
//		json.put("hostId",tokenUserBase.getZoomId());
//		json.put("uname",tokenUserBase.getTrueName());
//		json.put("action","join");
//		json.put("conf",JSONObject.fromObject(perConf));
		return json;
	}
	
	
	/**
	 * 通过会议码加入会议
	 * @param joinType 入会类型：1 是开始会议按钮 ；2 是会议号码加入方式 ；3,是邮件主持人入会;4,是邮件与会者入会
	 * @param code 入会的zoom会议号码
	 * @param cId  入会的会议id
	 * @param token  加密 （加密规则是 "bizconfzoom"+joinType+cId 进行32位MD5加密）
	 * @param blank  弹层标记blank=0
	 * @return
	 */
	@AsController(path = "gotojoinPage")
	public Object gotojoinPage(@CParam("joinType") String joinType,@CParam("code") String code,@CParam("cId") String cId,
			@CParam("uname") String uname,@CParam("token") String token ,@CParam("blank") String blank,
			@CParam("role") int role, HttpServletRequest request){
		
		//检测安装环境
		String clientDownLoadUrl = "";
		ResourceBundle bundle = ResourceBundle.getBundle(ConstantUtil.CONFIG_CONF_PROPERTIES);
		String UserAgent = request.getHeader("User-Agent");//区分移动端和pc端
		String os = UserAgentUtils.checkOS(UserAgent);
		
		UserBase currentUser=userService.getCurrentUser(request);
		Integer siteId = null;//站点id
		boolean isLogin=false;
		if(currentUser!=null && currentUser.getId() !=null && currentUser.getId().intValue() > 0 ){//登录的情况
			isLogin=true;
			siteId = currentUser.getSiteId();
			request.setAttribute("currentUser", currentUser);
			request.setAttribute("token", currentUser.getZoomToken());
			request.setAttribute("hostId", currentUser.getZoomId());
			request.setAttribute("uname", currentUser.getTrueName());// 2014-07-21 new Add
		}
		request.setAttribute("isLogin", isLogin);
		request.setAttribute("joinType", joinType);

		if("1".equals(joinType)){//1 是开始会议按钮 ，即会议id
			if(StringUtil.isEmpty(cId)){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
			}
			ConfBase perConf = confService.getConfBasebyConfId(Integer.parseInt(cId.trim()));
			if(perConf==null){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
			}
			if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
					|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
				return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
			}
			request.setAttribute("UUID", perConf.getUuid());//uuid 2014-07-08 add
			request.setAttribute("pwd", perConf.getHostKey());//密码 2014-07-04 add
			request.setAttribute("action", "start");
			request.setAttribute("conf", JSONObject.fromObject(perConf));
		}else if("2".equals(joinType)){//2 是会议号码加入方式,(在用户登录的情况下必须要查找当前站点下边的会议；在未登录的情况下不考虑站点问题)
			if(StringUtil.isEmpty(code)){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				if(blank !=null && "0".equals(blank)){
					return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
				}
				if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
						|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
					return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
				}
				return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
			}
			code = code.trim();//New Add
			/** 在用户登录的情况下必须要查找当前站点下边的会议；在未登录的情况下不考虑站点问题 */
			ConfBase perConf = confService.getConfBase(siteId,code);
			if (perConf == null) {
				perConf = confService.getConfBaseByZoomPMI(code);
			}
			if(perConf==null){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_3);
				request.setAttribute("blank", blank!=null?blank:"");
				if(blank !=null && "0".equals(blank)){
					return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
				}
				if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
						|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
					return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
				}
				return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
			}
			/**在没有登录的情况下*/
			if(currentUser==null){
				//通过主持人id获取Token
				UserBase tokenUserBase = userService.getUserBaseById(perConf.getCompereUser());
				request.setAttribute("token", tokenUserBase.getZoomToken());
				request.setAttribute("hostId", tokenUserBase.getZoomId());
				request.setAttribute("uname", tokenUserBase.getTrueName());// 2014-07-21 new Add
			}
			request.setAttribute("UUID", perConf.getUuid());//uuid 2014-07-08 add
			request.setAttribute("action", "join");
			request.setAttribute("conf", JSONObject.fromObject(perConf));
		}else if("3".equals(joinType)){//3是通过Email邮件地址主持人入会
			if(StringUtil.isEmpty(cId)){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				request.setAttribute("blank", blank!=null?blank:"0");//New Add
				if(blank !=null && "0".equals(blank)){
					return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
				}
				if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
						|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
					return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
				}
				return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
			}
			
			//对joinType和cId组织成一个含有bizconfzoom的字符串进行32位md5加密
			String encrypt = "bizconfzoom"+joinType+cId;
			MD5 md5 = new MD5();
			String md5Str = md5.encrypt(encrypt);
			logger.info("md5 === "+ md5Str);
			logger.info("加密token === "+ token);
			if(md5Str==null || token == null || !md5Str.equals(token)){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_107);
				request.setAttribute("blank", blank!=null?blank:"0");//New Add
				if(blank !=null && "0".equals(blank)){
					return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
				}
				return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
			}
			
			ConfBase perConf = confService.getConfBasebyConfId(Integer.parseInt(cId));
			if(perConf==null){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				request.setAttribute("blank", blank!=null?blank:"0");//New Add
				if(blank !=null && "0".equals(blank)){
					return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
				}
				if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
						|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
					return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
				}
				return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
			}
			/**在没有登录的情况下*/
			if(currentUser==null){
				//通过主持人id获取Token
				UserBase tokenUserBase = userService.getUserBaseById(perConf.getCompereUser());
				request.setAttribute("token", tokenUserBase.getZoomToken());
				request.setAttribute("hostId", tokenUserBase.getZoomId());
				request.setAttribute("blank", blank!=null?blank:"0");//New Add
				request.setAttribute("uname", tokenUserBase.getTrueName());// 2014-07-21 new Add
			}
			/**在没有登录的情况下*/
			request.setAttribute("UUID", perConf.getUuid());//uuid 2014-07-08 add
			request.setAttribute("pwd", perConf.getHostKey());//密码 2014-07-04 add
			request.setAttribute("action", "start");
			request.setAttribute("conf", JSONObject.fromObject(perConf));
		}else if("4".equals(joinType)){//4,是邮件与会者入会
			if(StringUtil.isEmpty(cId)){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				request.setAttribute("blank", blank!=null?blank:"0");//New Add
				if(blank !=null && "0".equals(blank)){
					return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
				}
				if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
						|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
					return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
				}
				return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
			}
			if(role==0){//role=0是普通入会者，role= 1 是系统管理人员
				//对joinType和cId组织成一个含有bizconfzoom的字符串进行32位md5加密
				String encrypt = "bizconfzoom"+joinType+cId;
				MD5 md5 = new MD5();
				String md5Str = md5.encrypt(encrypt);
				logger.info("md5 === "+ md5Str);
				logger.info("加密token === "+ token);
				if(md5Str==null || token == null || !md5Str.equals(token)){
					request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_107);
					request.setAttribute("blank", blank!=null?blank:"0");//New Add
					if(blank !=null && "0".equals(blank)){
						return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
					}
					if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
							|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
						return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
					}
					return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
				}
			}else{
				request.setAttribute("role", 1);
			}
			ConfBase perConf = confService.getConfBasebyConfId(Integer.parseInt(cId));
			if(perConf==null){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				request.setAttribute("blank", blank!=null?blank:"0");//New Add
				if(blank !=null && "0".equals(blank)){
					return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
				}
				if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
						|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
					return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
				}
				return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
			}
			request.setAttribute("UUID", perConf.getUuid());//uuid 2014-07-08 add
			request.setAttribute("pwd", perConf.getHostKey());//密码 2014-07-04 add
			request.setAttribute("action", "join");
			request.setAttribute("conf", JSONObject.fromObject(perConf));
		}else{//入会方式有误
			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_0);
			if(blank !=null && "0".equals(blank)){
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");//错误页面
			}
			if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
					|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){//移动客户端错误跳转
				return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile_error.jsp");
			}
			return new ActionForward.Forward("/jsp/common/join_msg_portal.jsp");
		}
		
		request.setAttribute("zoom_j_min", bundle.getString("zoom_j_min"));
		request.setAttribute("zoom_all_min", bundle.getString("zoom_all_min"));
		request.setAttribute("zoom_http", bundle.getString("zoom_http"));
		//识别出来的系统
		request.setAttribute("system", os);
		if(ConstantUtil.OS_MOBILE.equals(os) || ConstantUtil.OS_IPHONE.equals(os)
				|| ConstantUtil.OS_IPAD.equals(os) || ConstantUtil.OS_ANDROID.equals(os)){		//移动客户端成功跳转
			//mobile的app下载地址
			String apk = bundle.getString(ConstantUtil.OS_ANDROID);
			String ipk = bundle.getString(ConstantUtil.OS_IPHONE);
			request.setAttribute("apk", apk);
			request.setAttribute("ipk", ipk);
			return new ActionForward.Forward("/jsp/common/zoomJoinPage_mobile.jsp");
			
			//新版本windows客户端为统一客户端，不在区分win7 or  xp
		}else if(ConstantUtil.OS_WIN7.equals(os) || ConstantUtil.OS_WINXP.equals(os)){
			clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WIN7);
			String clickOnceUrl = bundle.getString(ConstantUtil.CLICKONCE_URL);
			//clickOnce application 可用
			if(HttpsUtils.URLenable(clickOnceUrl)){
				request.setAttribute("clickOnceUrl", clickOnceUrl);
			}
		//}else if(ConstantUtil.OS_WINXP.equals(os)) {
		//	clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WINXP);
		}else if(ConstantUtil.OS_MAC.equals(os)){
			clientDownLoadUrl = bundle.getString(ConstantUtil.OS_MAC);
		}
		//在pc上使用终端下载地址
		request.setAttribute("clientDownLoadUrl", clientDownLoadUrl);
		if(blank!=null && "0".equals(blank)){
			return new ActionForward.Forward("/jsp/common/zoomJoinPage_webpc.jsp");		//pc端弹层成功页面
		}
		return new ActionForward.Forward("/jsp/common/zoomJoinPage_outter.jsp");		//pc端非弹层成功页面
	}
	
	/**
	 * 判断ClickOnce
	 * @return
	 */
	private boolean clickOneceEnable(){
		
		return false;
	}
	/**
	 * 通过会议码加入会议
	 * @param joinType 入会类型：1 是开始会议按钮 ；2 是会议号码加入方式 ；3,是邮件主持人入会;4,是邮件与会者入会
	 * @param code 入会的zoom会议号码
	 * @param cId  入会的会议id
	 * @return
	 */
	@AsController(path = "fireFoxDown")
	public Object fireFoxDown(@CParam("confNo") String confNo,@CParam("status") String status,
			HttpServletRequest request){
		String UserAgent = request.getHeader("User-Agent");
		String reqURL = "https://www.zoomus.cn/j/"+confNo+"?status=manual&pwd=&pk=";
		String responseContent = HttpsUtils.sendSSLPostRequesttest(reqURL, UserAgent);
		
		Document document = Jsoup.parse(responseContent);
		Elements links = document.select("a[href]"); 
		String downloadURL = links.attr("href");
		request.setAttribute("downloadUrl", downloadURL);
		return null;
	}
	
	
	@AsController(path = "gotoQccodePage")
	public Object gotoQccodePage(@CParam("confId") int confId,@CParam("hostflag") int hostflag,
			@CParam("token") String token,HttpServletRequest request){
		
		//校验token
		String tokenStr = confId+ConstantUtil.TOKEN_KEY+hostflag;
		if(!StringUtil.genToken(tokenStr).equals(token)){
			
			return new ActionForward.Forward("/jsp2.0/common/qcimage_token_error.jsp");
		}
		ConfBase conf = confService.getConfBasebyConfId(confId);
		
		//开始时间是本次会议时区的开始时间
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(conf.getStartTime());
		calendar.add(Calendar.HOUR, (int)(conf.getTimeZone()/(1000*3600)));
		conf.setStartTime(calendar.getTime());		//初始化开始时间
		
		request.setAttribute("conf", conf);
		request.setAttribute("confId", confId);
		request.setAttribute("hostflag", hostflag);
		return new ActionForward.Forward("/jsp2.0/common/qccodePage.jsp");
	}
	
	@AsController(path = "genQccodeImg")
	public Object genQccodeImg(@CParam("confId") int cid,@CParam("hostflag") int hostflag,
			HttpServletRequest request,HttpServletResponse response){
		try {
			TwoDimensionCode handler = new TwoDimensionCode();
			ConfBase conf = confService.getConfBasebyConfId(cid);
			if(conf==null) {
				//TODO confId不存在的情况需要处理
			}else{
				//对joinType和cId组织成一个含有bizconfzoom的字符串进行32位md5加密
				String encrypt = "bizconfzoom";
				MD5 md5 = new MD5();
				
				String content = "http://"+SiteIdentifyUtil.getCurrentDomine();
				if(hostflag == 1){//主持人通过email入会
					content += "/join/gotojoinPage?joinType="+ConfConstant.HOST_JOIN_TYPE_EMAIL+"&cId="+conf.getId()+"&token="+md5.encrypt(encrypt + ConfConstant.HOST_JOIN_TYPE_EMAIL + conf.getId());
				}else{//参会者通过email入会
					content += "/join/gotojoinPage?joinType="+ConfConstant.USER_JOIN_TYPE_EMAIL+"&cId="+conf.getId()+"&token="+md5.encrypt(encrypt + ConfConstant.USER_JOIN_TYPE_EMAIL + conf.getId());
				}
				BufferedImage image = handler.qRCodeCommon(content, "png", 9);
				response.setDateHeader("Expires", -1);
				response.setHeader("Cache-Control", "no-cache");
				response.setHeader("Pragma", "no-cache");
				response.setContentType("image/png");
				ImageIO.write(image, "png", response.getOutputStream());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 	会议安全码页面跳转
	 * @param joinType
	 * @param request
	 * @return
	 */
	@AsController(path = "joinpage")
	public Object joinPage(@CParam("joinType") String joinType,@CParam("cId") String cId,@CParam("uId") int uId,HttpServletRequest request){
		UserBase currentUser=null;
		Integer confStatus;
		currentUser=userService.getCurrentUser(request);
		request.setAttribute("currentUser",currentUser);
		request.setAttribute("joinType",joinType);
		request.setAttribute("domain",SiteIdentifyUtil.getCurrentDomine());
		
		String userAgent=request.getHeader("USER-AGENT");
		Client client = BrowserUtil.getClient(userAgent);
		
		ResourceBundle bundle = ResourceBundle.getBundle(ConstantUtil.CONFIG_CONF_PROPERTIES);
		String protacolUrl = bundle.getString("zoom_http");
		request.setAttribute("zoom_http", protacolUrl);
		
		if (StringUtil.isEmpty(joinType) ) {
			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_1);
			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
		}
		/**
		 * 加入会议标识	
		 * joinType=1通过会议ID号  ，
		 * joinType=2是通过通过会议安全码
		 * */  
		Integer joinTypeNum=IntegerUtil.parseInteger(joinType);
		if(joinType==null || joinTypeNum.intValue()<ConfConstant.JOIN_TYPE_CONFID || joinTypeNum.intValue() >ConfConstant.JOIN_TYPE_OURURL){
			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_0);
			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
		}
		//joinType=1通过会议ID号  
		if(ConfConstant.JOIN_TYPE_CONFID.equals(joinTypeNum)){
			if(StringUtil.isEmpty(cId)){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
			}
			Integer confId=IntegerUtil.parseInteger(cId);
			ConfBase confBase = null;
			if(cId!=null && confId!=null && confId.intValue()>0){
				confBase=confService.getConfBasebyConfId(confId);
//				confBase=confService.getConfBasebyCondition(currentUser.getSiteId(),confId);
			}
			if(confBase==null){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
			}
		
//			confStatus=confBase.getConfStatus();
//			if(ConfConstant.confStatus==null ){
//				
//			}
			if(checkTooEarly(confBase)){//验证会议到开始时间  false
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_7);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
			}
		 
				//如果不是公开 会议验证用户是否登录，如果登录，则直接进入会议,未登录时，去登录
				if(currentUser!=null && currentUser.getId()!=null && currentUser.getId().intValue() >0){
					if(client.getBrowserName().startsWith("FireFox") && (client.getBrowserVersion().startsWith("3.6.") || "3.6".equals(client.getBrowserVersion()))){
						request.setAttribute("jump",true);
						return new ActionForward.Forward("/jsp/common/join_page.jsp");
					}else{
						return new ActionForward.Redirect("/join?cId="+cId+"&joinType="+joinType);//.Forward("/jsp/common/join_public.jsp");
					}
				}
				return new ActionForward.Forward("/jsp/user/login.jsp");
		}
		if(ConfConstant.JOIN_TYPE_SECURE_CODE.equals(joinTypeNum)){
			return new ActionForward.Forward("/jsp/common/join_page.jsp");
		}
		if(ConfConstant.HOST_JOIN_TYPE_EMAIL.equals(joinTypeNum)){
			if(uId>0){
				currentUser=userService.getUserBaseById(uId);
			}else{
				currentUser=new UserBase();
				currentUser.setId(0);
			}
			
			Integer confId=IntegerUtil.parseInteger(cId);
			ConfBase confBase = null;
			if(cId!=null && confId!=null && confId.intValue()>0){
				confBase=confService.getConfBasebyConfId(confId);
			}
			if(confBase==null){
				return  ConfConstant.JOIN_ERROR_CODE_2;
			}
			if(ConfConstant.CONF_STATUS_FINISHED.equals(confBase.getConfStatus())){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_4);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
			}
			if(ConfConstant.CONF_STATUS_CANCELED.equals(confBase.getConfStatus())){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_12);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
			}
			if(checkTooEarly(confBase)){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_7);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
			}
			String scode=request.getParameter("scode");
			
			if(scode==null || "".equals(scode.trim())){
				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_6);
				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
			}
			request.setAttribute("cId",cId);
			request.setAttribute("uId",uId);
			request.setAttribute("scode",scode);
			request.setAttribute("currentUser", null);
			if(uId<=0){
				return new ActionForward.Forward("/jsp/common/join_page.jsp");
			}else{
				if(client.getBrowserName().startsWith("FireFox") && (client.getBrowserVersion().startsWith("3.6.") || "3.6".equals(client.getBrowserVersion()))){
					request.setAttribute("jump",true);
					return new ActionForward.Forward("/jsp/common/join_page.jsp");
				}else{
//					return new ActionForward.Redirect("/join?cId="+cId+"&joinType="+joinType);//.Forward("/jsp/common/join_public.jsp");
					return new ActionForward.Redirect("/join?joinType="+ConfConstant.HOST_JOIN_TYPE_EMAIL+"&scode="+scode+"&cId="+cId+"&uId="+uId);
				}
			}
		}
		if(ConfConstant.JOIN_TYPE_OURURL.equals(joinTypeNum)){
			
		}
		return new ActionForward.Forward("/jsp/common/join_page.jsp");
	
	}
	
	/**
	 * 通过ID号或者是安全会议码加入会议
	 * @param cId 会议id
	 * @param code 会议号码
	 * @param cPass
	 * @param request
	 * @author Administrator Darren
	 * @return
	 */
//	@AsController(path = "")
//	public Object joinMeeting(@CParam("cId") String cId,@CParam("cPass") String cPass,
//			@CParam("code") String code,@CParam("userName") String userName,
//			@CParam("rId") int rId,@CParam("uId") int uId,HttpServletRequest request){
//		
//		UserBase currentUser=null;
//		currentUser=userService.getCurrentUser(request);
//		boolean isLogin=false;
//		request.setAttribute("cId", cId);
//		request.setAttribute("userName", userName);
//		request.setAttribute("domain",SiteIdentifyUtil.getCurrentDomine());
//		if(currentUser!=null && currentUser.getId() !=null && currentUser.getId().intValue() > 0 ){
//			isLogin=true;
//			request.setAttribute("currentUser", currentUser);
//		}
//
//		ConfBase perConf=null;
//		//获取ip
//		String userIp=StringUtil.getIpAddr(request);
//		request.setAttribute("isLogin", isLogin);
//
//		String userAgent=request.getHeader("USER-AGENT");
//		String ieBit = "Win32";
//
//		Client client = BrowserUtil.getClient(userAgent);
//		System.out.println("userAgent=="+userAgent);
//		if (!StringUtil.isEmpty(userAgent)) {
//			userAgent = userAgent.toLowerCase();
//			if (userAgent.indexOf("msie") > 0) {
//				if (userAgent.indexOf("win64") > 0) {
//					ieBit = "Win64";
//				}
//			} else {
//				ieBit = "";
//			}
//		}
//		request.setAttribute("ieBit", ieBit);
//		request.setAttribute("client", client);
//		String reload=request.getParameter("reload");//通过会议号登录为空,开始会议按钮
//		
//		if(!StringUtil.isEmpty(reload)){
//			request.setAttribute("reload", reload);	
//			request.setAttribute("code", code);	
//			request.setAttribute("cPass", cPass);
//			request.setAttribute("scode", request.getParameter("scode"));
//			request.setAttribute("userId", request.getParameter("userId"));
//			request.setAttribute("userType", request.getParameter("userType"));
//			if(rId>0){
//				JoinRandom joinRandom=clientAPIService.getJoinRandomById(rId);
//				if(joinRandom==null || joinRandom.getId()<= 0){
//					logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_8 +", rId="+rId );
//					request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_8);
//					return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//				}
//				
//				String preParam=clientAPIService.makePreParam(joinRandom,userIp);
//				
//				request.setAttribute("preParam", preParam);
//			}
//			return new ActionForward.Forward("/jsp/common/join_plug.jsp");
//			
//		}
//		
//		if (StringUtil.isEmpty(cId) && StringUtil.isEmpty(code)) {
//			logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_1 +", cId="+cId +", code="+code );
//			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_1);
//			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//		}
//		Integer joinType=0;
//		joinType=IntegerUtil.parseInteger(request.getParameter("joinType"));
//		request.setAttribute("joinType",joinType);
//		
//		
//		ConfBase confBase = null;
//		SiteBase siteBase=null;
//		//获取站点信息
//		String siteSign=SiteIdentifyUtil.getCurrentBrand();
//		siteBase=siteService.getSiteBaseBySiteSign(siteSign);
//		
//		if(siteBase==null){
//			logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_9 +", siteBase="+siteBase );
//			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_9);
//			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//		}
//		
//		int userRole=8;
//		
//		if(ConfConstant.JOIN_TYPE_CONFID.equals(joinType)){//通过会议id
//			Integer confId = 0;
//			if (!StringUtil.isEmpty(cId)) {
//				confId = IntegerUtil.parseInteger(cId);
//				if (confId != null && confId.intValue() > 0) {
//					confBase = confService.getConfBasebyConfId(confId);
//				}
//			}
//			if(confBase == null ){
//				logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_2 +", cId="+cId );
//				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_2);
//				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//			}
//			
//			
//			Integer permanentConf = confBase.getPermanentConf();
//			if(ConfConstant.CONF_PERMANENT_ENABLED_MAIN.equals(permanentConf)){
//				perConf=confBase;
//				ConfBase childConf=confService.getPermanentChildConf(confBase.getId());
//				if(childConf==null){
//					childConf=confService.createChildConf(confBase);
//				}
//				if(childConf==null){
//					logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_10 +", perConf="+perConf );
//					request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_10);
//					return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//				}
//				confBase=childConf;
//			}
//			
//			
//			
//			if(checkTooEarly(confBase)){
//				request.setAttribute("msgFlag", ConfConstant.JOIN_ERROR_CODE_7);
//				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//			}
//			
//			//验证会议是否公开会议,同时验证会议密码
//			Integer publicFlag=confBase.getPublicFlag();
//			String publicPass=confBase.getPublicConfPass();
//			if(ConfConstant.CONF_PUBLIC_FLAG_TRUE .equals(publicFlag)){
//				if(!StringUtil.isEmpty(publicPass)){
//					if((cPass==null || "".equals(cPass.trim()))){
//						return new ActionForward.Forward("/jsp/common/join_page.jsp");
//					}
//					if(!cPass.equals(publicPass)){
//						logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_5 +", publicPass="+publicPass +",cPass="+cPass);
//						request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_5);
//						return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//					}
//				}
//			}
//			
//			Integer confCreator=confBase.getCreateUser();
//			if(isLogin && currentUser.getId()!=null && confCreator!=null && confCreator.intValue() > 0 && currentUser.getId().equals(confCreator)){
//				userRole = 3;
//			}
//		}
//		/**
//		 * 通过会议号码入会
//		 * */
//		if(ConfConstant.JOIN_TYPE_SECURE_CODE.equals(joinType)){
//			if(StringUtil.isEmpty(code)){
//				return new ActionForward.Forward("/jsp/common/join_page.jsp");
//			}
//			
//			//通过zoom创建之后返回的id进行入会操作
//			String joinUrl = confService.getJoinConfByZoomId(code,"ej0cnACVSLC5-S3EQIXsog");
//			
//			
//			//参会者安全会议号
//			confBase = confService.getConfBaseByUserSecure(code);
//			if (confBase == null) {
//				//主持人安全会议号
//				confBase = confService.getConfBaseByCompereSecure(code);
//			}
//			if(confBase==null || (siteBase.getId()!=null && !siteBase.getId().equals(confBase.getSiteId())) ){
//
//				logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_3 +", confBase="+confBase );
//				logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_3 +", siteBase="+siteBase );
//				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_3);
//				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//			}
//			perConf=confBase;
//			Integer permanentConf = confBase.getPermanentConf();
//			if(ConfConstant.CONF_PERMANENT_ENABLED_MAIN.equals(permanentConf)){
//				ConfBase childConf=confService.getPermanentChildConf(confBase.getId());
//				if(childConf==null){
//					childConf=confService.createChildConf(confBase);
//				}
//				if(childConf==null){
//					logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_10 +", perConf="+perConf );
//					request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_10);
//					return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//				}
//				confBase=childConf;
//			}
//		}
//
//		if(ConfConstant.JOIN_TYPE_EMAIL.equals(joinType)){
//			if(uId>0){
//				currentUser=userService.getUserBaseById(uId);
//			}else{
//				currentUser=new UserBase();
//				currentUser.setId(0);
//				currentUser.setTrueName(userName);
//				currentUser.setClientName(userName);
//			}
//			code=request.getParameter("scode");
//			if(StringUtil.isEmpty(code)){
//				logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_8 +", code="+code );
//				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_8);
//				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//			}
//			//参会者安全会议号
//			confBase = confService.getConfBaseByUserSecure(code);
//			if (confBase == null) {
//				//主持人安全会议号
//				confBase = confService.getConfBaseByCompereSecure(code);
//			}
//
//			if(confBase==null){
//				logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_6 +", confBase="+confBase );
//				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_6);
//				return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//			}
//			
//			//验证公开会议密码，通过链接加入
//			String publicPass=confBase.getPublicConfPass();
//			if(confBase.isPublic() && !StringUtil.isEmpty(publicPass)){
//				if(!publicPass.equals(cPass)){
//					logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_5 +", publicPass="+publicPass +",cPass="+cPass);
//					request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_5);
//					return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//				}
//			}
//			
//			perConf=confBase;
//			//验证永久会议信息
//			Integer permanentConf = confBase.getPermanentConf();
//			if(ConfConstant.CONF_PERMANENT_ENABLED_MAIN.equals(permanentConf)){
//				
//				ConfBase childConf=confService.getPermanentChildConf(confBase.getId());
//				if(childConf==null){
//					childConf=confService.createChildConf(confBase);
//				}
//				if(childConf==null  || confBase.getId()==null || confBase.getId().intValue() <= 0  ){
//					logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_10 +", perConf="+perConf );
//					request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_10);
//					return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//				}
//				confBase=childConf;
//			}
//			
//			
//		}
//		
//		if(ConfConstant.JOIN_TYPE_SECURE_CODE.equals(joinType)|| ConfConstant.JOIN_TYPE_EMAIL.equals(joinType)){
//			
//			String hostCode=confBase.getCompereSecure();
//			String userCode=confBase.getUserSecure();
//			if(ConfConstant.CONF_PERMANENT_ENABLED_MAIN.equals(perConf.getPermanentConf())){
//				hostCode=perConf.getCompereSecure();
//				userCode=perConf.getUserSecure();
//			}
//			if(!StringUtil.isEmpty(code)){
//				if(code.equals(userCode)){
//					userRole = 8;
//				}
//				if(code.equals(hostCode)){
//					userRole = 3;
//				}
//			}
//		
//		}
//		if(ConfConstant.JOIN_TYPE_OURURL.equals(joinType)){
//			currentUser=new UserBase();
//			String userType=request.getParameter("userType");
//			String userId=request.getParameter("userId");
//			currentUser.setId(IntegerUtil.parseInteger(userId));
//			currentUser.setTrueName(userName);
//			currentUser.setClientName(userName);
//		}
//		if(confBase==null || confBase.getId()==null){
//			logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_8 +", confBase="+confBase );
//			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_8);
//			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//		}
//		if(!(ConfConstant.CONF_STATUS_SUCCESS.equals(confBase.getConfStatus())
//				|| ConfConstant.CONF_STATUS_OPENING.equals(confBase.getConfStatus())
//				|| ConfConstant.CONF_STATUS_CREATE_FAILED.equals(confBase.getConfStatus())
//				)){
//			logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_4 +", confBase="+confBase );
//			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_4);
//			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//		}
//		
//		//验证在线人数与站点的License数
////		int siteLicCount = siteService.queryASSiteInfo(siteBase.getSiteSign()).getLicense();
////		confManagementService.setingOnlineUserNum(confBase);
////		logger.info("confBase.pcNum="+confBase.getPcNum());
////		logger.info("siteLicCount="+siteLicCount);
////		if (confBase.getPcNum() >= (siteLicCount-1)) {
////			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_11);
////			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
////		}
//		
//		if(checkTooEarly(confBase)){
//			logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_7 +", confBase="+confBase );
//			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_7);
//			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//		}
//		
//		if(currentUser==null){
//			currentUser=new UserBase();
//			currentUser.setId(0);
//			currentUser.setTrueName(userName);
//			currentUser.setClientName(userName);
//		}
//		
//		String startStatus = ConstantUtil.AS_SUCCESS_CODE;
//		// 到此步，如果会议处于未开始状态，则必须启动
//		//if(confBase.getStartTime().after(DateUtil.getGmtDate(null)) && ConfConstant.CONF_STATUS_SUCCESS.equals(confBase.getConfStatus())){
//		if(ConfConstant.CONF_STATUS_SUCCESS.equals(confBase.getConfStatus()) 
//				|| ConfConstant.CONF_STATUS_CREATE_FAILED.equals(confBase.getConfStatus())){
//			//会议启动，同步锁控制
//			synchronized (lock) {
//				ConfBase confFromAS = confManagementService.queryConfInfo(confBase.getConfHwid(), siteBase, currentUser);
//				if (logger.isInfoEnabled()) {
//					logger.info("when start conf, confFromAS:" + confFromAS + ", confId:" + confBase.getId());
//				}
//				if (confFromAS != null && (ConfConstant.CONF_STATUS_SUCCESS.equals(confFromAS.getConfStatus()) 
//						|| ConfConstant.CONF_STATUS_CREATE_FAILED.equals(confFromAS.getConfStatus()))) {
//					startStatus=confManagementService.startConf(confBase, null, currentUser);
//					if(ConstantUtil.AS_SUCCESS_CODE.equalsIgnoreCase(startStatus)){
//						confService.updateStartTime(confBase, DateUtil.getGmtDate(null));
//						confService.updateConfStatus(confBase,ConfConstant.CONF_STATUS_OPENING);
//					}
//				}
//			}
//		}
//		if(perConf!=null && perConf.getId().equals(confBase.getBelongConfId()) 
//				&& ConfConstant.CONF_STATUS_SUCCESS.equals(perConf.getConfStatus())){
//			confService.updateConfStatus(perConf,ConfConstant.CONF_STATUS_OPENING);
//		}
//		logger.info("Join Conf startStatus="+startStatus);
//		if(!ConstantUtil.AS_SUCCESS_CODE.equalsIgnoreCase(startStatus)){
//			logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_8 +", confBase="+confBase +";startStatus="+startStatus);
//			if (ConstantUtil.AS_ERROR_START_FAILED.equalsIgnoreCase(startStatus)) {
//				request.setAttribute("errorCode", ConstantUtil.AS_ERROR_START_FAILED);
//			}
//			else {
//				request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_8);
//			}
//			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//		}
//		JoinRandom joinRandom=null;
//		
//		if(rId>0){
//			joinRandom=clientAPIService.getJoinRandomById(rId);
//		}
//		
//		if(joinRandom==null){
//			joinRandom=new JoinRandom();
//			joinRandom.setConfId(confBase.getId());
//			joinRandom.setUserId(currentUser.getId());
//			joinRandom.setUserName(currentUser.getTrueName());
//			joinRandom.setUserEmail(currentUser.getUserEmail());
//			joinRandom.setUserRole(userRole);
//			joinRandom.setCreateTime(DateUtil.getGmtDate(null));
//			joinRandom.setLanguage(LanguageHolder.getCurrentLanguage());
//			joinRandom.setClientIp(StringUtil.getIpAddr(request));
//			joinRandom=clientAPIService.saveRandom(joinRandom);
//		}
//		if(joinRandom==null || joinRandom.getId()<= 0){
//
//			logger.info("join conf  fail : errorCode" +ConfConstant.JOIN_ERROR_CODE_8 +", joinRandom="+joinRandom);
//			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_8);
//			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
//		}
//		String preParam=clientAPIService.makePreParam(joinRandom,userIp);
//		
//		request.setAttribute("preParam", preParam);
//		request.setAttribute("cId", confBase.getId());
//		request.setAttribute("rId", joinRandom.getId());
//		request.setAttribute("code", code);
//		request.setAttribute("cPass", cPass);
//		
//		
//		return new ActionForward.Forward("/jsp/common/join_plug.jsp");
//		
//	}
		
	
	
	
	/**
	 * 下载Client
	 * @param request
	 * @return
	 */
	@AsController(path = "download")
	public Object download(@CParam("cId") String cId,@CParam("userName") String userName,@CParam("rId") int rId,HttpServletRequest request,HttpServletResponse response){
		
		ConfBase confBase = null;
		Integer confId = 0;
		if (!StringUtil.isEmpty(cId)) {
			confId = IntegerUtil.parseInteger(cId);
			if (confId != null && confId.intValue() > 0) {
				confBase = confService.getConfBasebyConfId(confId);
			}
		}
		try {
			userName = URLDecoder.decode(userName,"utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		logger.info(confBase);
		if(confBase==null ){
			return "会议号 error";
		}
		JoinRandom joinRandom=null;
		if(rId>0){
			joinRandom=clientAPIService.getJoinRandomById(rId);
		}

		String ieBit = request.getParameter("ieBit");
		logger.info(joinRandom);
		String webRootPath = LiberContainer.getContainer().getServletContext().getRealPath("/");
		String filePath = webRootPath + "download/mcStartUp.exe";
		String fileBaseName = "mcStartUp";
		if ("64".equals(ieBit)) {
			filePath = webRootPath + "download/mcStartUp64.exe";
			fileBaseName = "mcStartUp64";
		}

		UserBase currentUser = null;
		currentUser = userService.getCurrentUser(request);
		logger.info(currentUser);
		if (currentUser == null) {
			currentUser = new UserBase();
			currentUser.setId(0);
			currentUser.setTrueName(userName);
			currentUser.setClientName(userName);
		}
		if (joinRandom == null) {
			joinRandom = new JoinRandom();
			joinRandom.setConfId(confBase.getId());
			joinRandom.setUserId(currentUser.getId());
			joinRandom.setUserEmail(currentUser.getUserEmail());
			joinRandom.setUserName(currentUser.getTrueName());
			joinRandom.setUserRole(8);
			joinRandom.setCreateTime(DateUtil.getGmtDate(null));
			joinRandom.setLanguage(LanguageHolder.getCurrentLanguage());
			joinRandom.setClientIp(StringUtil.getIpAddr(request));
			joinRandom = clientAPIService.saveRandom(joinRandom);
		}
		if (joinRandom == null || joinRandom.getId() <= 0) {
			request.setAttribute("errorCode", ConfConstant.JOIN_ERROR_CODE_8);
			return new ActionForward.Forward("/jsp/common/join_msg.jsp");
		}

		String confparam = clientAPIService.makeSuffixForSetup(joinRandom);
		String radomForName = String.valueOf(Math.round(Math.random() * 99999));
		String downLoadName = radomForName + "" + fileBaseName + "_" + confparam + ".exe";

		logger.info(confparam);
		try {
			DownLoadUtil.downloadFileNewName(response, filePath, downLoadName, false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	private boolean checkTooEarly(ConfBase confBase){
		 
		return false;
	}
	
	
	
	@AsController(path = "gotoPairingMeeting")
	public Object gotoPairingMeeting(@CParam("confNo") String confNo,HttpServletRequest request){
		
		request.setAttribute("confNo", confNo);
		return new ActionForward.Forward("/jsp2.0/common/pairing_page.jsp");
	}
	
	/**
	 * 配对入会
	 * @param confNo
	 * @param pairCode
	 * @param request
	 * @return
	 */
	@AsController(path = "pairingMeeting")
	public Object pairingMeeting(@CParam("confNo") String confNo,
			@CParam("pairCode") String pairCode,
			HttpServletRequest request){
		JSONObject json = new JSONObject();
		int retCode = confService.joinByH323OrSip(confNo, pairCode);
		json.put("state", retCode);
		return json.toString();
	}
}
