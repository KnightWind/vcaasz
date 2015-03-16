package com.bizconf.vcaasz.component.email;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bizconf.vcaasz.entity.UserBase;
import com.libernate.liberc.utils.LiberContainer;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
/**
 * @desc freemarker模板解析器
 * @author martin
 * @date 2013-1-31
 */
public class EmailContentGenerator {
	
	final String INTERNAL_CONTEXT_PATH = "/data/webapps/audio-portal/templates";
	final String FORMAL_CONTEXT_PATH = "/data/app/confcloud/templates";
	
	private final  Logger logger = Logger.getLogger(EmailContentGenerator.class);
	private EmailContentGenerator(){
		try{
			String tempDirPath = LiberContainer.getContainer().getServletContext().getRealPath("templates");
			this.init(tempDirPath);
		}catch(Exception e){
			logger.error("init EmailContentGenerator config failed! maybe is get tempDirPath error!");
			e.printStackTrace();
			File file = new File(FORMAL_CONTEXT_PATH);
			if (!file.exists()) {
				this.init(INTERNAL_CONTEXT_PATH);
			}
			else {
				this.init(FORMAL_CONTEXT_PATH);
			}
		}
	}
	private static EmailContentGenerator instance = new EmailContentGenerator();
	public static synchronized EmailContentGenerator getInstance(){
		return instance;
	}
	private Configuration cfg = null;
	
	/**
	 * 初始化生成器
	 */
	public void init(String dir){
		File tempDir = new File(dir);
		if(!tempDir.isDirectory()){
			tempDir.mkdir();
		}
		try{
			cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(tempDir);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setDefaultEncoding("UTF-8");
		}catch(Exception e){
			e.printStackTrace();
			logger.fatal("init EmailContentGenerator failed!");
		}
	}
	
	/**
	 * 根据ftl模板生成内容
	 * @param tempName  ftl模板文件名
	 * @param datas
	 * @return
	 */
	public String genContent(String tempName,Map<String, Object> datas){
		String content = "";
		StringWriter out = new StringWriter();
		try{
			Template temp = cfg.getTemplate(tempName);
			temp.setEncoding("UTF-8");
			temp.process(datas, out);
			content = out.getBuffer().toString();
		}catch (Exception e) {
			System.out.println(tempName + ":" + e.getLocalizedMessage() + "-" + e.getCause());
			e.printStackTrace();
		}finally{
			out.flush();
			try{
				out.close();
			}catch (Exception e) {
				logger.error("the string writer close fail!");
			}
		}
		return content;
	}
	
	/**
	 * 解析从DB中获取的邮件模板
	 * @param tempContent  db中获取的邮件模板内容
	 * @param datas  保存模板中变量的内容
	 * @return
	 * @throws Exception 
	 */
	public String parseTempToContent(String tempContent,Map<String, Object> datas){
		String content = "";
		StringReader reader = new StringReader(tempContent);
		StringWriter out = new StringWriter();
		try{
			Template temp = new Template("temp", reader, this.cfg);
			temp.setEncoding("UTF-8");
			temp.process(datas, out);
			content = out.getBuffer().toString();
		}catch (Exception e) {
			e.printStackTrace();
			//throw e;
		}finally{
			out.flush();
			try{
				out.close();
				reader.close();
			}catch (Exception e) {
				logger.error("the string reader/out close fail!");
			}
		}
		return content;
	}
	
	
	public static void main(String...strings){
		
		Map<String,Object> datas = new HashMap<String,Object>();
		UserBase userBase = new UserBase();
		userBase.setLoginName("wangchaobo && martin");
		datas.put("user", userBase);
		datas.put("time", " 2013-01-30");
		String dbInfo = "<html>test ${user.loginName} 会议时间：${time}</html>";
		EmailContentGenerator.getInstance().init("C://test");
		String testcontentString = EmailContentGenerator.getInstance().parseTempToContent(dbInfo, datas);
		//String testcontentString = EmailContentGenerator.getInstance().genContent("test.ftl", datas);
		System.out.println(testcontentString);
	}
}
