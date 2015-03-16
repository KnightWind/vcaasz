package com.bizconf.vcaasz.action.system;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.component.zoom.ZoomUserOperationComponent;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.RcIP;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.interceptors.SystemUserInterceptor;
import com.bizconf.vcaasz.service.DataCenterService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.service.UserService;
import com.bizconf.vcaasz.util.StringUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.Interceptors;
import com.libernate.liberc.annotation.ReqPath;

/**
 * @desc 多数据中心管理
 * @author Darren
 * @date 2014-12-23
 */
@ReqPath({"dataCenter"})
@Interceptors({SystemUserInterceptor.class})
public class DataCenterController extends BaseController{

	private final  Logger logger = Logger.getLogger(DataCenterController.class);
	
	@Autowired
	SiteService siteService;
	@Autowired
	UserService userService;
	@Autowired
	DataCenterService dataCenterService;
	
	@Autowired
	ZoomUserOperationComponent userOperationComponent;
	
	/**
	 * 查看数据中心列表
	 * */
	@AsController(path="list")
	public Object getDataCenterList(HttpServletRequest request,HttpServletResponse response){
		
		SystemUser  currentSysUser = userService.getCurrentSysAdmin(request);
		//需要合并默认处理的数据中心
		List<DataCenter> centers = dataCenterService.queryAll();
		request.setAttribute("centers", centers);
		request.setAttribute("currentSysUser", currentSysUser);
		return new ActionForward.Forward("/jsp2.0/system/data_center_token_list.jsp");
	}
	
	
	/**
	 * 跳转添加/修改数据中心
	 * */
	@AsController(path="addOrUpdate")
	public Object addOrUpdateDataCenter(@CParam("dataCenterId") Integer id, HttpServletRequest request,HttpServletResponse response){
		
		SystemUser  currentSysUser = userService.getCurrentSysAdmin(request);
		DataCenter dataCenter = null;
		if(id!=null){//修改跳转
			dataCenter = dataCenterService.queryDataCenterById(id);
		}
		request.setAttribute("dataCenter", dataCenter);
		request.setAttribute("currentSysUser", currentSysUser);
		return new ActionForward.Forward("/jsp2.0/system/data_center_token_add.jsp");
	}
	
	/**
	 * 保存新增、修改的数据中心
	 * */
	@AsController(path="save")
	public Object saveDataCenter(DataCenter center,HttpServletRequest request,HttpServletResponse response){
		boolean result = true;
		if(center!=null && center.getId()!=null && center.getId()!= 0){//修改
			result = dataCenterService.updateDataCenter(center);
		}else{//新增
			result = dataCenterService.saveDataCenter(center);
		}
		if(result==false){
			return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_FAILTRUE, "保存数据中心数据失败");
		}
		return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_SUCCESS, "保存数据中心数据成功");
	}
	
	
	/**
	 * 删除数据中心
	 * */
	@AsController(path="del")
	public Object deleteDataCenter(@CParam("id") Integer id, HttpServletRequest request,HttpServletResponse response){
		
		DataCenter retCenter = dataCenterService.queryDataCenterById(id);
		if(retCenter == null){
			return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_FAILTRUE, "删除数据中心数据失败");
		}
		
		//判断该数据中心是否已经建立了用户
		List<Map<String, Object>> retMaps = userOperationComponent.listAll(retCenter.getApiKey(), retCenter.getApiToken(), 0, 0);
		if(!retMaps.isEmpty() || retMaps.size() > 0 ){
			return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_FAILTRUE, "数据中心已经被占用,不可删除");
		}
		
		boolean result = dataCenterService.deleteDataCenterById(retCenter);
		if(result==false){
			return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_FAILTRUE, "删除数据中心数据失败");
		}
		return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_SUCCESS, "删除数据中心数据成功");
	}
	
	/**
	 * 数据中心RC的IP地址管理
	 * */
	@AsController(path="listIp")
	public Object listRCIpList(@CParam("centerId") Integer centerId,@CParam("pageNo") Integer pageNo,@CParam("pageSize") Integer pageSize, HttpServletRequest request,HttpServletResponse response){
		
		PageBean<RcIP> rcIPBean = dataCenterService.getRcIpListByCenterId(centerId,pageNo,pageSize);
		request.setAttribute("pageModel", rcIPBean);
		request.setAttribute("centerId", centerId);
		return new ActionForward.Forward("/jsp/system/rciplist.jsp");
	}
	/**
	 * 数据中心RC的IP地址管理 :添加或者修改跳转
	 * */
	@AsController(path="addOrUpdateIp")
	public Object addOrUpdateRCIpList(@CParam("id") Integer id,@CParam("centerId") Integer centerId,HttpServletRequest request,HttpServletResponse response){
		
		if(id != null && id > 0){
			RcIP rcIP = dataCenterService.getRcEntityById(id);
			if(rcIP == null){
				return new ActionForward.Forward("");
			}
			request.setAttribute("rcIP", rcIP) ;
		}
		request.setAttribute("centerId", centerId) ;
		return new ActionForward.Forward("/jsp/system/rc_ip_add.jsp");
	}
	
	
	/**
	 * 数据中心RC的IP地址管理 :保存
	 * */
	@AsController(path="saveRcIp")
	public Object saveRCIpList(RcIP rcIP, HttpServletRequest request,HttpServletResponse response){
		
		if(!dataCenterService.addOrUpdateRC(rcIP)){
			return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_FAILTRUE, "删除ip地址失败");
		}
		return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_SUCCESS, "保存ip地址成功");
	}
	/**
	 * 数据中心RC的IP地址管理：删除
	 * */
	@AsController(path="delRcIp")
	public Object delRCIpList(@CParam("id") Integer rcId,HttpServletRequest request,HttpServletResponse response){
		
		if(!dataCenterService.delRCIpById(rcId)){
			return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_FAILTRUE, "删除ip地址失败");
		}
		return StringUtil.returnJsonStr(ConstantUtil.SAVE_DATA_CENTER_SUCCESS, "删除ip地址成功");
	}
}
