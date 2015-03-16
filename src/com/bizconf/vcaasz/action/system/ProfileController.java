package com.bizconf.vcaasz.action.system;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.encrypt.MD5;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberInvocation;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

@Interceptors({SystemUserInterceptor.class})
@ReqPath("profile")
public class ProfileController extends BaseController {
	
	private final Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	public Object doRequest(LiberInvocation inv) {
		return new ActionForward.Forward("/jsp/system/profile.jsp");
	}
	
	/**
	 * 进入偏好设置
	 * @param inv
	 * @return
	 */
	@AsController(path = "tofaverSetting")
	public Object tofaverSetting(LiberInvocation inv) {
		SystemUser sysUser = userService.getCurrentSysAdmin(inv.getRequest());
		inv.getRequest().setAttribute("sysUser", sysUser);
		return new ActionForward.Forward("/jsp2.0/system/faverSetting.jsp");
	}
	
	/**
	 * 保存偏好设置
	 * @param inv
	 * @return
	 */
	@AsController(path = "savefaverSetting")
	public Object savefaverSetting(@CParam("pageSize") int pageSize,LiberInvocation inv) {
		SystemUser sysUser = userService.getCurrentSysAdmin(inv.getRequest());
		sysUser.setPageSize(pageSize);
		if(userService.saveOrUpdateSysUser(sysUser)){
			this.setInfoMessage(inv.getRequest(), "个人偏好修改成功");
		}else{
			this.setErrMessage(inv.getRequest(), "个人偏好修改失败");
		}
		return new ActionForward.Forward("/system/profile/tofaverSetting");
	}
	
	@AsController
	public Object submit(SystemUser newUser,@CParam("orgPass") String orgPass, LiberInvocation inv) {
		SystemUser oldUser = userService.getCurrentSysAdmin(inv.getRequest());
		if(StringUtil.isNotBlank(orgPass)){
			String inputOrgPass = MD5.encodePassword(orgPass, "MD5");
			if(!oldUser.getLoginPass().equalsIgnoreCase(inputOrgPass)){
				this.setErrMessage(inv.getRequest(), "个人信息修改失败，原始密码输入错误");
				return new ActionForward.Forward("/system/profile");
			}
		}
		oldUser.setEnName(newUser.getEnName());
		oldUser.setTrueName(newUser.getTrueName());
		oldUser.setEmail(newUser.getEmail());
		oldUser.setTelephone(newUser.getTelephone());
		oldUser.setMobile(newUser.getMobile());
		oldUser.setPassEditor(oldUser.getId());
		
		if(StringUtil.isNotBlank(oldUser.getEmail())){
			if("false".equals(emailValidate(oldUser.getEmail(), oldUser.getId()))){
				this.setErrMessage(inv.getRequest(), "该邮箱已存在!");
				inv.getRequest().setAttribute("currentSystemUser", oldUser);
				return new ActionForward.Forward("/jsp/system/profile.jsp");
			}
		}
		
		if (newUser.getLoginPass() != null && newUser.getLoginPass().length() > 0) {
			if(StringUtil.isEmpty(orgPass)){
				this.setErrMessage(inv.getRequest(), "如需修改密码，请输入正确的原始密码");
				return new ActionForward.Forward("/system/profile");
			}
			oldUser.setLoginPass(MD5.encodePassword(newUser.getLoginPass(), "MD5"));
		}
		
		try {
			DAOProxy.getLibernate().updateEntity(oldUser);
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrMessage(inv.getRequest(), "个人信息修改失败");
		}
		
		this.setInfoMessage(inv.getRequest(), "个人信息修改成功");
		
		return new ActionForward.Forward("/system/profile");
	}
	
	/**
	 * 创建(修改)系统管理员时验证邮箱是否已存在
	 * return true(不存在) false(已存在)
	 * wangyong
	 * 2013-6-17
	 */
	private String emailValidate(@CParam("userEmail") String userEmail, @CParam("userId") int userId){
		String flag = "true";
		if(StringUtil.isNotBlank(userEmail)){
			SystemUser systemUser = userService.getSystemUserByEmail(userEmail.trim());
			if(systemUser != null && userId != 0 && systemUser.getId().intValue() != userId){    //修改用户
				logger.info("邮箱名"+userEmail+"已存在!");
				flag = "false";
			}
		}
		return flag;
	}
	
}
