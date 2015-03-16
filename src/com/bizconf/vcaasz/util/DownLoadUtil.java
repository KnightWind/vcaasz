package com.bizconf.vcaasz.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DownLoadUtil {
	private static final Logger logger=Logger.getLogger(DownLoadUtil.class);

	public static void downloadFile(HttpServletResponse response,String filePath,boolean delFlag) throws Exception {
		String fileName = ""; // 文件名，输出到用户的下载对话框
		// 从文件完整路径中提取文件名，并进行编码转换，防止不能正确显示中文名
		if (filePath.lastIndexOf("/") > 0) {
			fileName =  filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
		}
		//创建file对象   
		File file=new File(filePath);   
		// 设置响应头和保存文件名
		response.reset();
		response.setContentLength((int)file.length()); 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		//response.setHeader("Content-Disposition","attachment;filename="+ new String(fileName.getBytes("GB2312"),"UTF-8")); 
		//读出文件到i/o流   
		FileInputStream fis=new FileInputStream(file);   
		BufferedInputStream buff=new BufferedInputStream(fis);   
		byte [] b=new byte[1024];//相当于我们的缓存   
		long k=0;//该值用于计算当前实际下载了多少字节   
		//从response对象中得到输出流,准备下载   
		OutputStream myout=response.getOutputStream();   
		//开始循环下载   
		while(k<file.length()){   
			int j=buff.read(b,0,1024);   
			k+=j;   
			//将b中的数据写到客户端的内存   
			myout.write(b,0,j);   
		}   
		//将写入到客户端的内存的数据,刷新到磁盘   
		myout.flush(); 
		myout.close();
		fis.close();
		buff.close();
		fis=null;
		buff=null;
		if(delFlag){
			file.delete();
		}
		file=null;
		
		//System.out.println("文件下载完毕.");
	}
	
	
	
	

	public static void downloadFileNewName(HttpServletResponse response,String filePath,String newFileName,boolean delFlag) throws Exception {
		String fileName = ""; // 文件名，输出到用户的下载对话框
		// 从文件完整路径中提取文件名，并进行编码转换，防止不能正确显示中文名
		if (filePath.lastIndexOf("/") > 0) {
			fileName =  filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
		} else if (filePath.lastIndexOf("\\") > 0) {
			fileName = filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.length());
		}
		//创建file对象   
		File file=new File(filePath);   
		System.out.println("download Util File = "+file);
		if(file!=null){

			System.out.println("download Util File.length = " + file.length());
		}
		// 设置响应头和保存文件名
		response.reset();
		response.setContentLength((int)file.length()); 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-msdownload");// application/octet-stream  application/x-msdownload
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Content-Disposition", "attachment; filename=" + newFileName);
		//response.setHeader("Content-Disposition","attachment;filename="+ new String(fileName.getBytes("GB2312"),"UTF-8")); 
		//读出文件到i/o流   
		FileInputStream fis=new FileInputStream(file);
		BufferedInputStream buff=new BufferedInputStream(fis);
		byte [] b=new byte[1024];//相当于我们的缓存   
		long k=0;//该值用于计算当前实际下载了多少字节   
		//从response对象中得到输出流,准备下载   
		OutputStream os=response.getOutputStream();   
		//开始循环下载   
		while(k<file.length()){   
			int j=buff.read(b,0,1024);   
			k+=j;   
			//将b中的数据写到客户端的内存   
			os.write(b,0,j);   
		}   
		//将写入到客户端的内存的数据,刷新到磁盘   
		os.flush(); 
		os.close();
		fis.close();
		buff.close();
		fis=null;
		buff=null;
		if(delFlag){
			file.delete();
		}
		file=null;
		
		//System.out.println("文件下载完毕.");
	}
}
