package com.bizconf.vcaasz.util;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jUtil {
	
	/**
	 * 根据Xml文件路径取Document对象
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Document getDocumentByXmlFile(String filePath) {
		if (StringUtil.isEmpty(filePath)) {
			return null;
		}
		Document document = null;
		SAXReader saxreader = null;
		try {
			saxreader = new SAXReader();
			document = saxreader.read(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			saxreader = null;
		}
		return document;
	}

	/**
	 * 根据Xml格式的字符串获取Document对象
	 * @param xmlStr   XML格式的字符串
	 * @param charsetName    字符串编码,例如：utf-8
	 * @return
	 */
	public static Document getDocumentByXmlString(String xmlStr, String charsetName){
		if (StringUtil.isEmpty(xmlStr) || StringUtil.isEmpty(charsetName)) {
			return null;
		}
		SAXReader saxreader = null;
		Document document = null;
		try {
			saxreader = new SAXReader();
			document = saxreader.read(new ByteArrayInputStream(xmlStr.getBytes(charsetName)));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		} finally {
			saxreader = null;
		}
		return document;
	}
	
	/**
	 * 是否去掉Document对象中的头部声明，返回xml字符串
	 * @param isHideDocType true:去掉Document对象中的头部声明, false:返回带头部声明的xml字符串
	 * jack
	 * 2013-12-10
	 */
	public static String getXmlStrWithoutHead(Document document, boolean isHideDocType){
		if (document == null) {
			return null;
		}
		if (isHideDocType) {
			return document.getRootElement().asXML(); 
		}
		return document.asXML();
	}
	
	private static void addCDATA(Element element, String tagName, String tagValue) {
		if (element == null || StringUtil.isEmpty(tagName)) {
			return;
		}
		element.addElement(tagName).addCDATA(StringUtil.isEmpty(tagValue)? "":tagValue);
	}
	
	public static void elementSetText(Element element, String name, String text){
		addCDATA(element, name, text);
	}

}
