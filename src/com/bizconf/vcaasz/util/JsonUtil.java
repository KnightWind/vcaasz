package com.bizconf.vcaasz.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.log4j.Logger;

import com.bizconf.vcaasz.entity.EmailConfig;

public class JsonUtil {
	private final static Logger logger = Logger.getLogger(JsonUtil.class);

	public static void main(String[] args) throws Exception {
		List<Object> emailList=new ArrayList<Object>();
		EmailConfig emailConfig=new EmailConfig();
		emailConfig.setId(10);
		emailConfig.setEmailName("dick");
		emailConfig.setEmailHost("fmail.bizconf.cn");
		emailConfig.setEmailSender("dick_zhao@bizconf.cn");
		emailConfig.setEmailAccount("dick_zhao@bizconf.cnqqqqqqq");
		emailConfig.setEmailPassword("qwer1234");
		emailList.add(emailConfig);
		emailConfig=new EmailConfig();
		emailConfig.setId(10);
		emailConfig.setEmailName("dickwwwwww");
		emailConfig.setEmailHost("fmail.bizconf.cn");
		emailConfig.setEmailSender("dick_zhao@bizconf.cn");
		emailConfig.setEmailAccount("dick_zhao@bizconf.cnqqqqqqq");
		emailConfig.setEmailPassword("qwer1234");
		emailList.add(emailConfig);
		
		System.out.println("AAAAAAAA---->>>>"+parseJsonWithFullFieldByObject(emailConfig).toString());
//		System.out.println(parseJsonWithList(emailList,"id","emailName","emailHost"));
//		
//		String jsonStr="{\"siteName\":\"asda\",\"siteSign\":\"asda\",\"siteLogo\":\"\",\"siteFlag\":\"0\",\"license\":\"sdf\",\"expireDate\":\"2013-01-01\"}";
//		jsonStr="[{\"siteName\":\"asda\",\"siteSign\":\"asda\",\"siteLogo\":\"\",\"siteFlag\":\"0\",\"license\":\"sdf\",\"expireDate\":\"2013-01-01\"},{\"clientName\":\"asdas\",\"loginName\":\"bizadmin\",\"loginPass\":\"admin\",\"userEmail\":\"asdasd\",\"mobile\":\"asdasd\",\"remark\":\"asdasd\"}]";
//////		List list=parseObjectListWithJsonString(jsonStr);
//		Object[] objs=parseObjectArrWithJsonString(jsonStr);
//		System.out.println(objs.length);//.getSiteName());
	}
	 


	/**
	 * 将一个对象 转成JsonObject对象 
	 * @param object   必填
	 * @param fields   必填
	 * @return
	 */
	
	public static JSONObject parseJsonWithObject(Object object, String... fields){
		JSONObject json = null;
		json=JSONObject.fromObject(object);
//		if (object != null) {
//			if (fields != null && fields.length > 0) {
//				json = new JSONObject();
//				for (String field : fields) {
//					if (field != null && !"".equals(field.trim())) {
//						json.put(field, ObjectUtil.getFieldValue(object, field));
//					}
//				}
//			}
//		}
		return json;
	}
	
	/**
	 * 将一个对象 转成JsonObject对象 
	 * @param object   必填
	 * @param fields   必填
	 * @return
	 */
	
	public static JSONObject parseJsonWithFullFieldByObject(Object object){
		String[] fieldNameArray=ObjectUtil.getFieldFromObject(object);
		JSONObject json = null;
		if (object != null) {
			if (fieldNameArray != null && fieldNameArray.length > 0) {
				json = new JSONObject();
				Object eachValue=null;
				Field field=null;
				for (String fieldName : fieldNameArray) {
					if (fieldName != null && !"".equals(fieldName.trim())) {
						eachValue= ObjectUtil.getFieldValue(object, fieldName);
						field=ObjectUtil.getFieldByObjectAndFieldName(object, fieldName);
//						logger.info("field.getType()=="+field.getType());
						if(eachValue == null){
							eachValue = "";
						}else{
//							logger.info(fieldName+" --->>"+field.getType());
							if("class java.util.Date".equals(String.valueOf(field.getType()))){
								eachValue=DateUtil.dateToString((Date)eachValue,"");
							}
						}
						json.put(fieldName,eachValue);
						eachValue=null;
					}
				}
			}
		}
		return json;
	}
	
	
	/**
	 * 将List转成一个JsonArray
	 * @param list  必填
	 * @param fields  必填
	 * @return
	 */

	public static JSONArray parseJsonWithList(List list, String... fields){
		JSONArray array=null;
		if (list !=null && list.size()>0){
			if (fields != null && fields.length > 0) {
				array=new JSONArray();
				for(Object object :list){
					if(object!=null ){
						array.add(parseJsonWithObject(object,fields));
					}
				}
			}
		}
		return array;
		
	}
	
	/**
	 * 将单个Json对象 字符串转成Bean
	 * @param str
	 * @param clazz
	 * @return
	 */
	public static Object parseObjectWithJsonString(String str,Class clazz){  
		JSONObject jsonObj = JSONObject.fromObject(str);   
		JsonConfig jsonConfig = new JsonConfig();   
		jsonConfig.setRootClass(clazz);
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] {"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"})); 
		Object object = (Object) JSONObject.toBean(jsonObj,jsonConfig);  
		return object;
	}
	
	/**
	 * 将字符串转成Object数组
	 * @param str
	 * @return
	 */
	public static Object[] parseObjectArrWithJsonString(String str){
		Object[] objArr=null;
		JSONArray jsonArr= JSONArray.fromObject(str);
		objArr =jsonArr.toArray();
		return objArr;
	}
	
	/** 
     * 将JAVA的MAP转换成JSON字符串， 
     * 只转换第一层数据 
     * @param map 
     * @return 
     */  
    public static String parseMapToJsonStr(Map<?,?> map){  
    	if(map==null||map.isEmpty()){  
            return "null";  
        }  
        String jsonStr = "{";  
        Set<?> keySet = map.keySet();  
        for (Object key : keySet) {  
            jsonStr += "\""+key+"\":\""+map.get(key)+"\",";       
        }  
        jsonStr = jsonStr.substring(0,jsonStr.length()-1);  
        jsonStr += "}";  
        return jsonStr;  
    }  

    /**    
     * 把数据对象转换成json字符串    
     * DTO对象形如：{"id" : idValue, "name" : nameValue, ...}    
     * 数组对象形如：[{}, {}, {}, ...]    
     * map对象形如：{key1 : {"id" : idValue, "name" : nameValue, ...}, key2 : {}, ...}    
     * @param object    
     * @return    
     */     
    public static String parseObjectToJsonStr(Object object) throws Exception{      
        String jsonString = null;      
        //日期值处理器      
        JsonConfig jsonConfig = new JsonConfig();      
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());      
        if(object != null){      
            if(object instanceof Collection || object instanceof Object[]){      
                jsonString = JSONArray.fromObject(object, jsonConfig).toString();      
            }else{      
                jsonString = JSONObject.fromObject(object, jsonConfig).toString();      
            }      
        }      
        return jsonString == null ? "{}" : jsonString;      
    }   

//	public static List<Object> parseObjectArrWithJsonString(String str,Class clazz){
//		List<Object> list=null;
//		JSONArray jsonArr= JSONArray.fromObject(str);
////		Object[] objArr =jsonArr.toArray();
////		if(objArr!=null  && objArr.length>0){
////			list=new ArrayList<Object>();
////			for(Object object:objArr){
////				if(object!=null){
////					JSONObject.toBean(object,clazz); 
////				}
////			}
////		}
//		list=JSONArray.toList(jsonArr, clazz);
//
//		return list;
//	}
////	JSONArray jsonArray2= JSONArray.fromObject(jsonArrayString);

	
}
