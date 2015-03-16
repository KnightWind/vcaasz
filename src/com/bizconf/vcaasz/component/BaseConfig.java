package com.bizconf.vcaasz.component;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @desc 
 * @author Administrator
 * @date 2013-4-7
 */
public class BaseConfig {
	private final  Logger logger = Logger.getLogger(BaseConfig.class);
	private Properties prop;
	
	private BaseConfig(){
		prop = new Properties();
		try {
//			String path  = LiberContainer.getContainer().getServletContext().getRealPath("")+File.separator+"WEB-INF"+File.separator+"conf"+File.separator+"conf.properties";
//			FileInputStream in = new FileInputStream(path);
			prop.load(getClass().getResourceAsStream("/config/conf.properties"));
		} catch (IOException e) {
			logger.error("load the config fail!");
			e.printStackTrace();
		}
	}
	
	private static class GetObject{
		
		public static BaseConfig config = new BaseConfig();
	}
	
	
	public static BaseConfig getInstance(){
		
		return GetObject.config;
	}
	
	public  String getString(String key,String defValue){
		if(prop.getProperty(key)!=null){
			return prop.getProperty(key);
		}else{
			return defValue;
		}
	}
	
	
	public int getInt(String key,int defValue){
		if(prop.getProperty(key)!=null){
			try{
				return  Integer.parseInt(prop.getProperty(key));
			}catch (Exception e) {
				// TODO: handle exception
				return defValue;
			}
		}else{
			return defValue;
		}
	}
	
	public static void main(String args[]){
		BaseConfig config = BaseConfig.getInstance();		 
	}
}

