package com.bizconf.vcaasz.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.exception.OuterConfException;
import com.bizconf.vcaasz.service.OuterService;
import com.bizconf.vcaasz.util.Dom4jUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;

@ReqPath("/conference")
public class OuterController extends BaseController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private OuterService outerService;
	
	@AsController(path = "userLog")
	public Object userLog(HttpServletRequest request){
		Document doc = Dom4jUtil.getDocumentByXmlFile("D:/userLog.xml");
		if(doc != null){
			return getUserOLList(doc.asXML(), request.getParameter("debug"), request);
		}
		return null;
	}
	
	@AsController(path = "log")
	public Object log(HttpServletRequest request){
		Document doc = Dom4jUtil.getDocumentByXmlFile("D:/log.xml");
		if(doc != null){
			return getConfUserLog(doc.asXML(), request.getParameter("debug"), request);
		}
		return null;
	}
	
	@AsController(path = "join")
	public Object join(HttpServletRequest request, HttpServletResponse response){
		Document doc = Dom4jUtil.getDocumentByXmlFile("D:/join.xml");
		if(doc != null){
			return join(doc.asXML(), request.getParameter("debug"), request, response);
		}
		return null;
	}
	
	@AsController(path = "month")
	public Object month(HttpServletRequest request){
		Document doc = Dom4jUtil.getDocumentByXmlFile("D:/month.xml");
		if(doc != null){
			return getConfListByMonth(doc.asXML(), request.getParameter("debug"), request);
		}
		return null;
	}
	
	@AsController(path = "number")
	public Object number(HttpServletRequest request){
		Document doc = Dom4jUtil.getDocumentByXmlFile("D:/number.xml");
		if(doc != null){
			return getUserNumOL(doc.asXML(), request.getParameter("debug"), request);
		}
		return null;
	}
	
	@AsController(path = "status")
	public Object status(HttpServletRequest request){
		Document doc = Dom4jUtil.getDocumentByXmlFile("D:/status.xml");
		if(doc != null){
			return getConfStatus(doc.asXML(), request.getParameter("debug"), request);
		}
		return null;
	}
	
	@AsController(path = "license")
	public Object license(HttpServletRequest request){
		Document doc = Dom4jUtil.getDocumentByXmlFile("D:/license.xml");
		if(doc != null){
			return getLicenceNum(doc.asXML(), request.getParameter("debug"), request);
		}
		return null;
	}
	
	@AsController(path = "time")
	public Object time(HttpServletRequest request){
//		StringBuffer sbf = new StringBuffer();
//		sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//		sbf.append("<param>");
//		sbf.append("	<siteId>t-hhh</siteId>");
//		sbf.append("	<random>1293230129</random>");
//		sbf.append("	<authId>4297f44b13955235245b2497399d7a93</authId>");
//		sbf.append("</param>");
		Document doc = Dom4jUtil.getDocumentByXmlFile("D:/time.xml");
		if(doc != null){
			return getServerTime(doc.asXML(), request.getParameter("debug"), request);
		}
//		return getServerTime(sbf.toString(), request.getParameter("debug"), request);
		return null;
	}
	
	/**
	 * 查询服务器时间
	 * jack
	 * 2013-12-10
	 */
	@AsController(path = "timestamp.jsp")
	public Object getServerTime(@CParam("params") String params, @CParam("debug") String debug,HttpServletRequest request){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("查询服务器时间" + params + ",debug:" + debug);
		String xmlStr = outerService.getServerTime(params);
		logger.info(xmlStr);
		return new ActionForward.TextXML(xmlStr);
	}
	
	/**
	 * 通过URL直接入会
	 * jack
	 * 2013-12-16
	 */
	@AsController(path = "join_mtg.jsp")
	public Object join(@CParam("params") String params, @CParam("debug") String debug, HttpServletRequest request, HttpServletResponse response){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("通过URL直接入会" + params + ",debug:" + debug);
		try {
			outerService.joinConf(params, request, response);
		} catch (OuterConfException e) {
			e.printStackTrace();
			logger.info("通过URL直接入会错误码:" + e.getErrorCode());
			request.setAttribute("errorCode", e.getErrorCode());
			return new ActionForward.Forward("/jsp/common/outer_join_msg.jsp");
		}
		return new ActionForward.Forward("/jsp/common/outer_join_plug.jsp");
	}
	
	/**
	 * 按月查询活动记录
	 * 1.必须依靠查询服务器时间接口的数据
	 * jack
	 * 2013-12-10
	 */
	@AsController(path = "conf_list.jsp")
	public Object getConfListByMonth(@CParam("params") String params, @CParam("debug") String debug,HttpServletRequest request){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("按月查询活动记录" + params + ",debug:" + debug);
		String xmlStr = outerService.getConfListByMonth(params);
		logger.info(xmlStr);
		return new ActionForward.TextXML(xmlStr);
	}
	
	/**
	 * 查询单个活动中用户进出记录
	 * 1.必须依靠查询服务器时间接口的数据
	 * jack
	 * 2013-12-10
	 */
	@AsController(path = "conf_log.jsp")
	public Object getConfUserLog(@CParam("params") String params, @CParam("debug") String debug,HttpServletRequest request){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("查询单个活动中用户进出记录" + params + ",debug:" + debug);
		String xmlStr = outerService.getConfUserLog(params);
		logger.info(xmlStr);
		return new ActionForward.TextXML(xmlStr);
	}
	
	/**
	 * 查询当前时刻剩余的点数
	 * 1.必须依靠查询服务器时间接口的数据
	 * jack
	 * 2013-12-10
	 */
	@AsController(path = "license.jsp")
	public Object getLicenceNum(@CParam("params") String params, @CParam("debug") String debug,HttpServletRequest request){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("查询当前时刻剩余的点数" + params + ",debug:" + debug);
		String xmlStr = outerService.getLicenceNum(params);
		logger.info(xmlStr);
		return new ActionForward.TextXML(xmlStr);
	}
	
	/**
	 * 查询单个活动的状态
	 * 1.必须依靠查询服务器时间接口的数据
	 * jack
	 * 2013-12-10
	 */
	@AsController(path = "conf_status.jsp")
	public Object getConfStatus(@CParam("params") String params, @CParam("debug") String debug,HttpServletRequest request){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("查询单个活动的状态" + params + ",debug:" + debug);
		String xmlStr = outerService.getConfStatus(params);
		logger.info(xmlStr);
		return new ActionForward.TextXML(xmlStr);
	}
	
	/**
	 * 查询单个正在进行中活动的在线人数
	 * 1.必须依靠查询服务器时间接口的数据
	 * jack
	 * 2013-12-10
	 */
	@AsController(path = "count_online.jsp")
	public Object getUserNumOL(@CParam("params") String params, @CParam("debug") String debug,HttpServletRequest request){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("查询单个正在进行中活动的在线人数" + params + ",debug:" + debug);
		String xmlStr = outerService.getUserNumOL(params);
		logger.info(xmlStr);
		return new ActionForward.TextXML(xmlStr);
	}
	
	/**
	 * 查询单个正在进行中活动的在线人数列表
	 * 1.必须依靠查询服务器时间接口的数据
	 * jack
	 * 2013-12-10
	 */
 
	@AsController(path = "online_list.jsp")
	public Object getUserOLList(@CParam("params") String params, @CParam("debug") String debug,HttpServletRequest request){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("查询单个正在进行中活动的在线人数列表" + params + ",debug:" + debug);
		String xmlStr = outerService.getUserOLList(params);
		logger.info(xmlStr);
		return new ActionForward.TextXML(xmlStr);
	}
	
	/**
	 * 导出进出会日志的Excel
	 * 1.必须依靠查询服务器时间接口的数据
	 * 
	 */
 
	@AsController(path = "export_joinlog.jsp")
	public Object export(@CParam("params") String params, @CParam("debug") String debug,HttpServletRequest request){
		if("1".equals(debug)|| "true".equalsIgnoreCase(debug)){
			return params;
		}
		logger.info("导出生成Excel  " + params + ",debug:" + debug);
		String xmlStr = outerService.getExportExcelPath(params);
		logger.info(xmlStr);
		return new ActionForward.TextXML(xmlStr);
	}
 
	

}
