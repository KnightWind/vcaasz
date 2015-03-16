package com.bizconf.vcaasz.util;

import java.io.File;

import com.libernate.liberc.LiberCFile;
import com.libernate.liberc.utils.LiberContainer;

public class UploadUtil {
	
	static final String STORAGE_BASE = LiberContainer.getContainer().
	getServletContext().getRealPath("/uploadfiles");
	
	
	static final String SITE_LOG_PATH = STORAGE_BASE + "/site_logo/";
	
	static {
		createDirs(SITE_LOG_PATH);
	}
	
	public static String getSiteLogoPath() {
		return SITE_LOG_PATH;
	}
	
	public static String getSiteLogoName(LiberCFile file, String siteBrand) {
		String exts [] = file.getOriginalFilename().split("\\.");
		String ext = "";
		if (exts.length > 1) {
			ext = exts[exts.length - 1];
		}
		return siteBrand + "_" + System.currentTimeMillis() + "." + ext;
	}
	
	public static void createDirs(String dir) {
		try {
			File file = new File(dir);
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} 
}
