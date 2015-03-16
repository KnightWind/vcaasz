package com.bizconf.vcaasz.action;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.bizconf.vcaasz.component.TwoDimensionCode;
import com.bizconf.vcaasz.component.language.LanguageHolder;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.service.IpLocatorService;
import com.bizconf.vcaasz.service.SiteService;
import com.bizconf.vcaasz.util.SiteIdentifyUtil;
import com.bizconf.vcaasz.util.UrlUtils;
import com.bizconf.vcaasz.util.UserAgentUtils;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.utils.LiberContainer;

/**
 * 下载中心
 * @author wangyong
 *
 */
//@ReqPath("downCenter")
@ReqPath({"/download","downCenter"})
public class DownloadController {
	
	private static final String dirPath = LiberContainer.getContainer().getServletContext().getRealPath("download")+File.separator;

	@Autowired
	IpLocatorService ipLocatorService;
	@Autowired
	SiteService siteService;
	/**
	 * 下载中心下载会议客户端
	 * wangyong
	 * 2013-4-26
	 */
	@AsController(path = "downConfClient")
	public void downConfClient(HttpServletRequest request,HttpServletResponse response){
//		String dirPath = LiberContainer.getContainer().getServletContext().getRealPath("download")+File.separator;
		downLoad("mcsetup.exe", dirPath, request, response);
	}
	
	@AsController(path = "downClient")
	public Object downClient(@CParam("s") Integer isSupport,HttpServletRequest request){
		
		//iphone=https://itunes.apple.com/cn/app/id546505307
		String UserAgent = request.getHeader("User-Agent");
		String apkDownLoadUrl="";//andorid下载地址
		String ipkDownLoadUrl="";//ios设备下载地址
		String clientDownLoadUrl="";//非移动设备客户端下载地址
		String os = UserAgentUtils.checkOS(UserAgent);
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(ConstantUtil.CONFIG_CONF_PROPERTIES);
			
			apkDownLoadUrl = bundle.getString(ConstantUtil.OS_ANDROID);
			ipkDownLoadUrl = bundle.getString(ConstantUtil.OS_IPHONE);
			if(os.equals(ConstantUtil.OS_WIN7)){
				clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WIN7);
			}else if(os.equals(ConstantUtil.OS_WINXP)) {
				clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WINXP);
			}else if(os.equals(ConstantUtil.OS_MAC)){
				clientDownLoadUrl = bundle.getString(ConstantUtil.OS_MAC);
			}
			//如果是移动设备
			else{
				//设置个默认的Win7
				clientDownLoadUrl = bundle.getString(ConstantUtil.OS_WIN7);
			}
			request.setAttribute("isSupport", isSupport);//jsp页面的footer和背景去掉
			request.setAttribute("apkDownLoadUrl", apkDownLoadUrl);
			request.setAttribute("ipkDownLoadUrl", ipkDownLoadUrl);
			request.setAttribute("clientDownLoadUrl", clientDownLoadUrl);
			request.setAttribute("system", os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String lan = LanguageHolder.getCurrentLanguage();
		if(lan!=null && "en-us".equals(lan)){
			return new ActionForward.Forward("/jsp/user/down_center_en.jsp");
		}
//		return new ActionForward.Forward("/jsp/user/download_center.jsp");
		return new ActionForward.Forward("/jsp/user/down_center_zh.jsp");
	}
	
	/**
	 * 下载中心生成二维码图片
	 * */
	@AsController(path="QccodeImg")
	public Object getQccodeImg(HttpServletRequest request,HttpServletResponse response){
		
		try {
			TwoDimensionCode handler = new TwoDimensionCode();
			ResourceBundle bundle = ResourceBundle.getBundle(ConstantUtil.CONFIG_CONF_PROPERTIES);
			String content = UrlUtils.getDownLoadUrl(bundle.getString("downCenter"));
			BufferedImage image = handler.qRCodeCommon(content, "png", 5);
			response.setDateHeader("Expires", -1);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			response.setContentType("image/png");
			ImageIO.write(image, "png", response.getOutputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@AsController(path = "goDownload")
	public Object goDownload(HttpServletRequest request){
		return new ActionForward.Forward("/jsp2.0/user/download_center_beforelogin.jsp");
	}
	
	/**
	 * 下载中心下载视频转换软件
	 * wangyong
	 * 2013-4-26
	 */
	@AsController(path = "downVideoTrans")
	public void downVideoTrans(HttpServletRequest request,HttpServletResponse response){
//		String dirPath = LiberContainer.getContainer().getServletContext().getRealPath("download")+File.separator;
		downLoad("VideoTranslate.rar", dirPath, request, response);
	}
	
	private void downLoad(String fileName, String dirPath, HttpServletRequest request,HttpServletResponse response){
		String tempPath = dirPath + fileName;
		File file = new File(tempPath);
//		response.setContentType("octets/stream");
		//修改替换成
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Length", "" + file.length());
		
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        BufferedInputStream in = null;
        BufferedOutputStream out = null; 
        try {
        	in = new BufferedInputStream(new FileInputStream(file));
        	out = new BufferedOutputStream(response.getOutputStream());
        	byte[] bts = new byte[1024*20];
        	int temp = 0;
        	while((temp = in.read(bts))!=-1){
        		out.write(bts,0,temp);
        	}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				//response.setBufferSize(1024*8);
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
	 * 下载客户端 帮助文档
	 * @param request
	 * @param response
	 * Alan Liu
	 * 2013-11-19
	 */
	@AsController(path = "downClientHelp")
	public Object downClientHelp(HttpServletRequest request,HttpServletResponse response){
		return new ActionForward.Forward("/jsp/user/download_client_help.jsp");
	}
	
	/**
	 * 下载android的APK
	 * */
//	@AsController(path = "zoom.apk")
	@AsController(path = "confcloud.apk")
	public Object downloadApk(HttpServletRequest request,HttpServletResponse response){
		String UserAgent = request.getHeader("User-Agent");
		if(UserAgentUtils.isMobile(UserAgent,"mobile") || UserAgentUtils.isMobile(UserAgent,"iphone") 
				|| UserAgentUtils.isMobile(UserAgent,"ipad") || UserAgentUtils.isMobile(UserAgent,"android") ){
			if(UserAgent.toLowerCase().indexOf("micromessenger") > -1 || UserAgent.toLowerCase().indexOf("qq") > -1){	//腾讯的微信和qq产品跳转提示页面
				return new ActionForward.Forward("/jsp/common/download_error_tencent.jsp");
			}
		}
		downLoad("confcloud.apk", dirPath, request, response);
		return null;
	}
}
