package com.bizconf.vcaasz.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bizconf.vcaasz.entity.UserBase;



public class BeanUtil {

	public static void main(String[] args) {
//		System.out.println(field2Att("id"));
//		System.out.println(field2Att("site_id"));
//		System.out.println(field2Att("site_id_Name"));
//
//		System.out.println(att2Field("siteIdNameFieldName"));
		
		UserBase userBase = new UserBase();
		userBase.setId(12);
		userBase.setLoginName("wadtest");
		userBase.setTrueName("hehehe");
		userBase.setUserEmail("wad@gmail.com");
		
		Map<String, Object> map = obj2Map(userBase,new String[]{"id","loginName","trueName","userEmail"});
		map.entrySet();
		for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			System.out.print("key == "+entry.getKey());
			System.out.println("  value= " +entry.getValue().toString() );
		}
	}
	
	
	/**
	 * 将字段转成Model中的属性
	 * @param fieldName
	 * @return
	 */
	public static String field2Att(String fieldName){
		StringBuffer attBuffer=null;
		if(StringUtil.isNotBlank(fieldName)){
			 attBuffer=new StringBuffer();
			 String[] fieldWordArr=fieldName.split("_");
			 char[] wordChars=null;
			 if(fieldWordArr!=null){
				 String fieldWord=null;
				 for(int ii=0;ii<fieldWordArr.length;ii++){
					 fieldWord=fieldWordArr[ii];
					 if(StringUtil.isNotBlank(fieldWord)){
						 if(ii==0){
							 attBuffer.append(fieldWord);
						 }else{
							 wordChars=fieldWord.toCharArray();
							 if(wordChars[0]>=97 && wordChars[0] <=122){
								 wordChars[0]-=32;
							 }
							 attBuffer.append(new String(wordChars));
						 }
					 }
					 fieldWord=null;
				 }
			 }
			 fieldWordArr=null;
			 wordChars=null;
		}
		return attBuffer.toString();
	}
	
	/**
	 * 将Model中的属性转成Field字段
	 * @param arrName
	 * @return
	 */
	public static String att2Field(String arrName){
		StringBuffer fieldBuffer=null;
		if("serialVersionUID".equals(arrName)){
			return arrName;
		}
		if(StringUtil.isNotBlank(arrName)){
			fieldBuffer=new StringBuffer();
			char[] arrChars=arrName.toCharArray();
			for(char eachChar:arrChars){
				if (eachChar >= 65 && eachChar <= 90) {
					fieldBuffer.append("_");
					eachChar+=32;
				}
				fieldBuffer.append(String.valueOf(eachChar));
			}
			arrChars=null;
		}
		return fieldBuffer.toString();
	}

	/**
	 * @param obj
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> obj2Map(Object obj,String... attrs) {

		Map<String, Object> map = new HashMap<String, Object>();
		//指定的属性
		Set<String> attrsSet = new HashSet<String>();
		Collections.addAll(attrsSet, attrs);
		//所有的属性
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0, len = fields.length; i < len; i++) {
			String varName = fields[i].getName();
			if(attrsSet.size()>0 && !attrsSet.contains(varName)){
				continue;
			}
			try {
				// 获取原来的访问控制权限
				boolean accessFlag = fields[i].isAccessible();
				// 修改访问控制权限
				fields[i].setAccessible(true);
				Object o = fields[i].get(obj);
				if (o != null)map.put(varName, o);
				// 恢复访问控制权限
				fields[i].setAccessible(accessFlag);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
		}
		return map;
	}
	
	//去除所有元素为空的数组
	public static void trimObjs(List<Object[]> datas){
		for (Iterator it = datas.iterator(); it.hasNext();) {
			Object[] objects = (Object[]) it.next();
			boolean flag = false;
			for (int i = 0; i < objects.length; i++) {
				if(objects[i]!=null && !objects[i].toString().equals("")){
					flag = true;
					break;
				}
			}
			if(flag) continue;
			it.remove();
		}
	}
	
}
