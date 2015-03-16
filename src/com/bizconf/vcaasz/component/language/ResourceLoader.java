package com.bizconf.vcaasz.component.language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 
 * @author Chris Gao
 *
 */
public class ResourceLoader {
	
	static Log logger = LogFactory.getLog(ResourceLoader.class);
	
	private static Map<String, Map<String, String>> LANGUAGE_PACKAGE_MAP = 
		new HashMap<String, Map<String, String>>();
	
	private static Map<String, String> resourceConfigMap = new HashMap<String, String>();
	
	private static ResourceLoader instance = new ResourceLoader();
	
	private ResourceLoader() {
		try {
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ResourceLoader getInstance() {
		return instance;
	}
	
	public Map<String, String> getPackage(String resourceKey) {
		if (resourceKey == null) {
			return LANGUAGE_PACKAGE_MAP.get(LanguageHolder.DEFAULT_LANGUAGE);
		}
		
		Map<String, String> map =  LANGUAGE_PACKAGE_MAP.get(resourceKey);
		if (map == null) {
			return LANGUAGE_PACKAGE_MAP.get(LanguageHolder.DEFAULT_LANGUAGE);
		}
		
		return map;
	}
	
	private void load() throws IOException {
		
		//读取已经配置的语言资源文件
		InputStream inputStream = getClass().getResourceAsStream("/config/language_resource_config.txt");
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		
		while (line != null) {
			String [] config = line.split(",");
			resourceConfigMap.put(config[0], config[1]);
			line = br.readLine();
		}
		
		br.close();
		
		//加载各语言资源文件
		for (Entry<String, String> e : resourceConfigMap.entrySet()) {
			Map<String, String> map = parseLanguageResourceAsMap(e.getKey());
			LANGUAGE_PACKAGE_MAP.put(e.getKey(), map);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("Language resource load finished.");
		}
	}
	
	
	/**
	 * 解析资源
	 * 
	 * @param sourceKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> parseLanguageResourceAsMap(String sourceKey) {
		
		String fileName = resourceConfigMap.get(sourceKey);
		if (fileName == null) {
			return null;
		}
		
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream input = getClass().getResourceAsStream("/config/" + fileName);
		try {
			Document document = reader.read(input);
			List<Element> elements = document.getRootElement().elements("resource");
			
			for (Element element : elements) {
				map.put(element.elementText("key"), element.elementText("value"));
			}
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		try {
			if (input != null) {
				input.close();
			}
		} catch (IOException e) {
			logger.error("", e);
		}
		
		return map;
	}
	
	public static void main(String [] args) {
//		try {
//			ResourceLoader.getInstance();
//			for (Entry<String, String> e : resourceConfigMap.entrySet()) {
//				System.out.println(e.getKey() + " " + e.getValue());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		System.out.println(ResourceHolder.getInstance().getResource("system.login.name"));
	}
}
