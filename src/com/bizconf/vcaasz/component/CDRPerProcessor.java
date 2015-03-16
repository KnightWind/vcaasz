package com.bizconf.vcaasz.component;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;
/**
 * @desc CDR账单的预处理
 * @author martin
 * @date 2013-1-31
 */
public class CDRPerProcessor {
	
	//原始cdr存放路径
	final String DEF_CONTEXT_PATH = BaseConfig.getInstance().getString("cdrdatapath", "/data/cdr");
	
	//预处理后的CDR存放路径
	final String DEF_SAVE_PATH = BaseConfig.getInstance().getString("cdrdonepath", "/data/cdr/done");
	
	//处理后的原始CDR转存路径
	final String DEF_RESTORE_PATH =  BaseConfig.getInstance().getString("cdrrestorepath", "/data/cdr/restore");
	
	//cdr用户名前缀
	final String PREFIX = "pc_";
	
	private File cdrDir = null;
	
	public File getCdrDir() {
		return cdrDir;
	}
	public void setCdrDir(File cdrDir) {
		this.cdrDir = cdrDir;
	}
	
	//private final  Logger logger = Logger.getLogger(CDRPerProcessor.class);
	private CDRPerProcessor(){
			 File dir = new File(DEF_CONTEXT_PATH);
			 if(!dir.isDirectory()){dir.mkdir();}
			 setCdrDir(dir);
			 
			 File dir1 = new File(DEF_SAVE_PATH);
			 if(!dir1.isDirectory()){dir1.mkdir();}
			 
			 File dir2 = new File(DEF_RESTORE_PATH);
			 if(!dir2.isDirectory()){dir2.mkdir();}
	}
	
	private static CDRPerProcessor instance = new CDRPerProcessor();
	
	public static synchronized CDRPerProcessor getInstance(){
		return instance;
	}
	
	public void PerProcess(){
		File[] cdrs = getCdrDir().listFiles();
		try{
			for (int i = 0; i < cdrs.length; i++) {
				File cdr = cdrs[i];
				if(cdr!=null&& cdr.isFile() && cdr.exists()){
					String cdr_name = cdr.getName();
					//转存文件
					String restorePath = DEF_RESTORE_PATH+File.separator+cdr_name;
					storeFile(cdr, restorePath);
					
					//处理输出新文件
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(cdr),"utf-8"));
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(DEF_SAVE_PATH+File.separator+cdr_name)),"UTF-8"));
					String temp = null;
					while((temp=reader.readLine())!=null){
						String[] datas = temp.split(",");
						
						if(datas.length<11){
							bw.write(temp);
							bw.newLine();
							continue;
						}
						String contentline = "";
						
						String username = datas[11];
						String cdr_type = datas[10];
						if(datas.length>29 && cdr_type.equals("02")){
							//如果这一位没有
							if(username.trim().equals("")){
								username = PREFIX+"user";
							//检查第一位字符是否为数字
							}else if(Character.isDigit(username.trim().charAt(0))){
								
								datas[11] = PREFIX+datas[11];
							}
						} 
						contentline = getCdrFormArray(datas);
						bw.write(contentline);
						bw.newLine();
					}
					bw.flush();
					bw.close();
					reader.close();
					
					cdr.delete();
				}
			}
		}catch (Exception e) {
			
			System.out.println("PerProcess exception  ");
			e.printStackTrace();
		} 
	}
	
	private String getCdrFormArray(String[] datas){
		StringBuilder builder = new StringBuilder();
		if(datas!=null && datas.length>0){
			for (int i = 0; i < datas.length; i++) {
				builder.append(datas[i]);
				builder.append(",");
			}
			builder.deleteCharAt(builder.lastIndexOf(","));
		}
		return builder.toString();
	}
	
	private void storeFile(File file, String storePath){
		BufferedInputStream in = null;
	    BufferedOutputStream out = null; 
		try{
			in = new BufferedInputStream(new FileInputStream(file));
			out = new BufferedOutputStream(new FileOutputStream(new File(storePath)));
			int temp=0;
			byte[] bts = new byte[1024*20];
        	while((temp = in.read(bts))!=-1){
        		out.write(bts,0,temp);
        	}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if (out!=null) {
					out.close();
				}
				if (in!=null) {
					in.close();
				}
			}catch (Exception e) {
				System.out.println("close buffer failed! ");
			}
		}
	}
	
	public static void main(String... args){
		
//		CDRPerProcessor p = CDRPerProcessor.getInstance();
//		p.PerProcess();
		
		
	}
}
