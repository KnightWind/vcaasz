package com.bizconf.vcaasz.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.libernate.liberc.utils.LiberContainer;

/** 
 *   
 * @package com.bizconf.vcaasz.component 
 * @description: TODO
 * @author Martin
 * @date 2014年7月4日 上午11:53:38 
 * @version 
 */
public class ReportWriter {
	
	
	final String INTERNAL_CONTEXT_PATH = "/data/webapps/vcaasz/data";
	final String FORMAL_CONTEXT_PATH = "/data/app/vcaasz/templates";
	final String FILE_NAME = "report.data";
	
	private final  Logger logger = Logger.getLogger(ReportWriter.class);
	private ReportWriter(){
		try{
			String tempDirPath = LiberContainer.getContainer().getServletContext().getRealPath("data");
			this.init(tempDirPath);
		}catch(Exception e){
			logger.error("init ReportWriter config failed! maybe is get tempDirPath error!");
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
	
	private BufferedWriter bw = null;
	
	private static ReportWriter instance = new ReportWriter();
	public static synchronized ReportWriter getInstance(){
		return instance;
	}
	 
	/**
	 * 初始化路径
	 */
	public void init(String dir){
		try {
			File tempDir = new File(dir);
			if(!tempDir.isDirectory()){
				tempDir.mkdir();
			}
			File dataFile = new File(dir+File.separator+FILE_NAME);
			if(!dataFile.exists()){
					dataFile.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(dataFile, true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public synchronized void writerData(Object msg){
		try {
			bw.write(msg.toString());
			bw.newLine();
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	
	
	public static void main(String...strings){
		
	 
	}
}
