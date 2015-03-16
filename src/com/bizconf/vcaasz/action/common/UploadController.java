/*
 * zmlk - TestController.java
 * 2011-8-5 下午12:13:00
 */
package com.bizconf.vcaasz.action.common;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.bizconf.vcaasz.action.BaseController;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.util.StringUtil;
import com.bizconf.vcaasz.util.UploadUtil;
import com.libernate.liberc.ActionForward;
import com.libernate.liberc.LiberCFile;
import com.libernate.liberc.annotation.AsController;
import com.libernate.liberc.annotation.CParam;
import com.libernate.liberc.annotation.ReqPath;
import com.libernate.liberc.annotation.httpmethod.Get;
import com.libernate.liberc.annotation.httpmethod.Post;
import com.libernate.liberc.exception.LiberCFileException;

/**
 * @Author Chris Gao (hongliang_gao@sina.com)
 * @version 2011-8-5 下午12:13:00
 */

@ReqPath("upload")
public class UploadController extends BaseController {
	@AsController @Get
	public Object preUpload() {
		return new ActionForward.Forward("/jsp/common/upload_common.jsp");
	}
	
	@AsController @Post
	public Object common(@CParam("commonFile") LiberCFile file, 
			@CParam("siteBrand") String siteBrand,@CParam("type") String type) {
		String fileName = UploadUtil.getSiteLogoName(file, siteBrand);
		String error = "";
		if(file.getSize() > ConstantUtil.SITELOG_LIMIT * 1024){
			error = "alert('" + "最大上传图片 "+ ConstantUtil.SITELOG_LIMIT + "KB" + "。');window.history.back();";
		}else{
			try {
				System.out.println();
				
				file.save(UploadUtil.getSiteLogoPath() + fileName
						, "jpg","JPG","GIF","gif","png","PNG");
			}
			catch (LiberCFileException e) {
				error = "alert('不支持您上传传的文件类型。');window.history.back();";
				fileName = "";
				e.printStackTrace();
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		String ret = "<script language=\"javascript\">";
		if(StringUtil.isNotBlank(error)){
			fileName = "";
		}
		
		if (type == null || "null".equals(type) || type.length() == 0) {
			type = "";
		}
		
		ret += error;
		ret += "parent.uploadCallback"+ type +"(\""+fileName+"\");";
		ret += "</script>";
		return ret;
	}
}
