package com.bizconf.vcaasz.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.web.util.HtmlUtils;

import com.bizconf.vcaasz.entity.SiteBase;
import com.bizconf.vcaasz.entity.UserBase;

public class ObjectUtil {
	

	private static final Logger logger = Logger.getLogger(ObjectUtil.class);
	
	private static final String [][] HTML_CODE_MAP=new String[][]{
		{"<","&lt;"},
		{">","&gt;"},
		{" ","&nbsp;"},
		{"'","&apos;"},
		{"\"","&quot;"}
	} ;
	
	
	
	
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Object[]> eachObjectList=new ArrayList<Object[]>();
		Object[] objArr=new Object[3];
		SiteBase siteBase1=new SiteBase();
		SiteBase siteBase2=new SiteBase();;
		siteBase1.setSiteName("Meeting");
		siteBase1.setCreateTime(new Date());

		siteBase2.setSiteName("int");
		objArr[0]=siteBase1;
		objArr[1]=siteBase2;
		objArr[2]=new String[]{"id"};
		eachObjectList.add(objArr);
		UserBase userBase1=new UserBase();
		UserBase userBase2=new UserBase();;
		userBase1.setLoginName("zhaost");
		userBase1.setCreateTime(new Date());
		userBase2.setLoginName("Dick");
		objArr=new Object[3];
		objArr[0]=userBase1;
		objArr[1]=userBase2;
		objArr[2]=new String[]{"id"};
		eachObjectList.add(objArr);
//		siteBase2.setSiteName("int");
////		List<Object[]> siteDiff=compareData(siteBase1,siteBase2,new String[]{"id"});
//		String compareStr=compareJsonData(siteBase1,siteBase2,new String[]{"id","sitesign"});
//		System.out.println(compareStr);
		//
		String jsonStr="[{\"beanName\":\"com.bizconf.vcaasz.entity.SiteBase\",\"o\":{\"id\":\"\",\"siteName\":\"Meeting\",\"createTime\":{\"date\":20,\"day\":2,\"hours\":10,\"minutes\":29,\"month\":7,\"seconds\":14,\"time\":1376965754300,\"timezoneOffset\":-480,\"year\":113}},\"n\":{\"id\":\"\",\"siteName\":\"int\",\"createTime\":\"\"}},{\"beanName\":\"com.bizconf.vcaasz.entity.UserBase\",\"o\":{\"id\":\"\",\"loginName\":\"zhaost\",\"createTime\":{\"date\":20,\"day\":2,\"hours\":10,\"minutes\":29,\"month\":7,\"seconds\":14,\"time\":1376965754301,\"timezoneOffset\":-480,\"year\":113}},\"n\":{\"id\":\"\",\"loginName\":\"Dick\",\"createTime\":\"\"}}]";

//		String jsonStr=compareJsonDataArray(eachObjectList);
		List<Object[]> diffList=getCompareDataFromJsonStr(jsonStr);
		System.out.println(diffList.size());
	}
	
	
	/**
	 * 将指定的字段转成OFFSET的日期数据
	 * @param object
	 * @param offset
	 * @param fields
	 * @return
	 */
	public static Object offsetDate(Object object,Long offset,String[] fields){
		if(object!=null && offset!= null && offset.longValue() !=0 && fields!=null && fields.length >0 ){
			Date gmtDate=null;
			Date offsetDate=null;
			for(String field:fields){
				if(field!=null && !"".equals(field.trim())){
					try {
						gmtDate=(Date) getFieldValue(object,field);
						offsetDate=DateUtil.getOffsetDateByGmtDate(gmtDate, offset);
						object=setFieldValue(object,field,offsetDate);
					} catch (Exception e) {
						// TODO Auto-generated catch block
//						e.printStackTrace();
					}
				}
			}
		}
		return object;
	}
	
	
	
	public static List offsetDateWithList(List list, Long offset,String[] fields){
		List tmpList=null;
		if(list!=null && list.size() > 0 ){
			tmpList=new ArrayList();
			for(Object object:list){
				tmpList.add(offsetDate(object,offset,fields));
			}
		}
		return tmpList;
	}
	
	/**
	 * 对List中指定属性HTML转义存到DB中
	 * @param list
	 * @param fields
	 * @return
	 */
	public static List parseHtmlWithList(List list,String...fields){
		List tmpList=null;
		if(list!=null && list.size()>0 && fields!=null && fields.length >0){
			tmpList=new ArrayList();
			for(Object object:list){
				tmpList.add(parseHtml(object,fields));
			}
		}
		return tmpList;
	}
	

	/**
	 * 对List中指定属性的HTML代码 转成可见实际代码
	 * @param list
	 * @param fields
	 * @return
	 */
	public static List parseCharWithList(List list,String...fields){
		List tmpList=null;
		if(list!=null && list.size()>0 && fields!=null && fields.length >0){
			tmpList=new ArrayList();
			for(Object object:list){
				tmpList.add(parseChar(object,fields));
			}
		}
		return tmpList;
	}

	
	
	/**
	 * 转成HTML字符
	 * @param object   需要转义的对象
	 * @param fields   指定字段
	 * @return
	 */
	public static Object parseHtml(Object object,String... fields){
		if(object!=null && fields !=null && fields.length > 0){
			String svalue="";
			String nvalue="";
			for(String field:fields){
				if(field!=null && !"".equals(field.trim())){
					svalue=String.valueOf(getFieldValue(object,field));
					nvalue=HtmlUtils.htmlEscape(svalue);
					object=setFieldValue(object,field,nvalue);
					nvalue=null;
					svalue=null;
				}
			}
		}
		return object;
	}
	
	
	
	/**
	 * 转成实际字符
	 * @param object   需要转义的对象
	 * @param fields   指定字段
	 * @return
	 */
	public static Object parseChar(Object object,String... fields){
		if(object!=null && fields !=null && fields.length > 0){
			String svalue="";
			String nvalue="";
			for(String field:fields){
				if(field!=null && !"".equals(field.trim())){
					svalue=String.valueOf(getFieldValue(object,field));
					nvalue=HtmlUtils.htmlUnescape(svalue);
					object=setFieldValue(object,field,nvalue);
					nvalue=null;
					svalue=null;
				}
			}
		}
		return object;
	}
	
	/**
	 * 根据指定属性名称获取对象的属性值
	 * @param dateFormat true：对date类型的属性进行字符串格式化 ； false:不进行格式化
	 * wangyong
	 * 2013-8-21
	 */
	public static Object getFieldValue(Object object, String fieldName, boolean dateFormat) {
		if(!dateFormat){
			return getFieldValue(object, fieldName);
		}
		Object valObject =null;
		if(object!=null){
			Class<? extends Object> clszz = object.getClass();
			Method getMdthod=null;
			char[] fieldChars=null;
			try {
				fieldChars=fieldName.toCharArray();
				
				//过滤掉已经过时的属性
				Field field = clszz.getDeclaredField(fieldName);
				Deprecated deprecated = field.getAnnotation(Deprecated.class);
				if(deprecated!=null) return null;
				
				
				String typeName = field.getType().getName();
				fieldChars[0]-=32;
				getMdthod = clszz.getMethod("get"+ new String(fieldChars));
				valObject=(Object) getMdthod.invoke(object);
				if ("java.util.Date".equalsIgnoreCase(typeName) && valObject != null) {
					valObject = DateUtil.format((Date) valObject,"yyyy-MM-dd HH:mm:ss");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return valObject;
	}
	
	
	public static Object getFieldValue(Object object,String fieldName) {
		Object valObject =null;
		if(object!=null){
			Class clszz=object.getClass();
			String getMethodName="";
			Method getMdthod=null;
			char[] fieldChars=null;
			try {
				
				//过滤过时的属性 
				Field field = clszz.getDeclaredField(fieldName);
				Deprecated deprecated = field.getAnnotation(Deprecated.class);
				if(deprecated!=null) return null;
				
				
				fieldChars=fieldName.toCharArray();
				fieldChars[0]-=32;
				getMdthod = clszz.getMethod("get"+new String(fieldChars));
				valObject=(Object) getMdthod.invoke(object);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				fieldChars=null;
				getMdthod=null;
				clszz=null;
			}
		}
		
		return valObject;
	}
	
	
	public static Field getFieldByObjectAndFieldName(Object object,String fieldName){
		Field field=null;
		if(object!=null){
			Class clszz=object.getClass();
			Field[] fieldArray=clszz.getDeclaredFields();
			if(fieldArray!=null && fieldArray.length >0){
				for(Field eachField:fieldArray){
					if(fieldName!=null && eachField!=null  && fieldName.equals(eachField.getName())){
						field=eachField;
						break;
					}
				}
			}
		}
		return field;
		
	}

	public static Object setFieldValue(Object object,String fieldName,Object value) {
		if(object!=null){
			Class clszz=object.getClass();
			String setMethodName="";
			Method setMdthod=null;
			char[] fieldChars=null;
			Field field =null;
			try {
				fieldChars=fieldName.toCharArray();
				fieldChars[0]-=32;
				field = object.getClass().getDeclaredField(fieldName);
				setMethodName="set"+new String(fieldChars);
				setMdthod=clszz.getDeclaredMethod(setMethodName, field.getType()); 
				setMdthod.invoke(object, value);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				fieldChars=null;
				setMdthod=null;
				setMethodName=null;
				clszz=null;
			}
		}
		
		return object;
	}
	
	
	
	
	/**
	 * 获取对象的所有字段
	 * */
	public static String[] getFieldFromObject(Object object){
		if (object == null) {
			return null;
		}
		Class clszz = object.getClass();
		Field[] fieldArray = clszz.getDeclaredFields();
		List<String> fieldList = new ArrayList<String>();
		if (fieldArray != null && fieldArray.length > 0) {
			String eachName = "";
			for (Field eachField : fieldArray) {
				if (eachField != null) {
					eachName = eachField.getName();
					if (!"serialVersionUID".equalsIgnoreCase(eachName)) {
						fieldList.add(eachName);
					}
				}
			}
		}
		fieldArray = null;
		clszz = null;
		return (fieldList == null || fieldList.size() <= 0) ? null : fieldList.toArray(new String[0]);
	}
	

	/**
	 * 根据Json字符串生成比较数据的列表
	 * @param jsonStr
	 * @return
	 * 		Object[0]  beanName
	 * 		Object[1]  FieldName
	 * 		Object[2]  oValue
	 * 		Object[3]  nValue
	 */
	public static List<Object[]> getCompareDataFromJsonStr(String jsonStr){
		if(StringUtil.isEmpty(jsonStr)){
			return null;
		}
		JSONArray jsonArr=JSONArray.fromObject(jsonStr);
		if(jsonArr==null || jsonArr.size()<=0){
			return null;
		}
		int jsonLen=jsonArr.size();
		JSONObject eachJson=null;
		JSONObject oJson=null;
		JSONObject nJson=null;
		String beanName=null;
//		Object beanObject=null;
//		Object o=null;
//		Object n=null;
		List<Object[]> compareDataList=new ArrayList<Object[]>();
		String eachFieldName="";
		Object oValue=null;
		Object nValue=null;
		Object[] eachDataArr=null;
		for(int ii=0;ii<jsonLen;ii++){
			eachJson=JSONObject.fromObject(jsonArr.get(ii));
			if(eachJson != null){
				beanName=null;
//				beanObject=null;
				beanName=eachJson.getString("beanName");
				if(StringUtil.isEmpty(beanName)){
					continue;
				}
//				try {
//					beanObject=Class.forName(beanName).newInstance();
//				} catch (InstantiationException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IllegalAccessException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				if(beanObject == null){
//					continue;
//				}
				oJson=eachJson.getJSONObject("o");
				
				nJson=eachJson.getJSONObject("n");
				if(oJson.size()!=nJson.size()){
					continue;
				}
				Iterator itt = oJson.keys();  
				if(itt!=null){
					while (itt.hasNext()) {
						eachFieldName = itt.next().toString();
						oValue = oJson.getString(eachFieldName);
						nValue = nJson.getString(eachFieldName);
						eachDataArr=new Object[4];
						eachDataArr[0]=beanName;
						eachDataArr[1]=eachFieldName;
						eachDataArr[2]=oValue;
						eachDataArr[3]=nValue;
						compareDataList.add(eachDataArr);
					}
				}
				itt=null;
				
			}
			
		}
//		System.out.println("jsonArr.size()="+jsonArr.size());
//		System.out.println(jsonArr);
		return compareDataList;
	}
	
	
	
	/**
	 * 根据多个对象列表，生成Json
	 * @param objectList
	 * @return
	 * 		Object[0]   o对象,旧对象
	 * 		Object[1]	n对象,新对象
	 * 		Object[2]	必须显示的字段名的字符串数组，new String[]{"id"}
	 */
	
	
	public static String compareJsonDataArray(List<Object[]> objectList){
		if(objectList==null || objectList.size()<=0){
			return null;
		}
		JSONArray jsonArr=new JSONArray();
		String eachCompareData=null;
		for(Object[] eachObjectArray:objectList){
			if(eachObjectArray!=null && eachObjectArray.length==3){
				eachCompareData=compareJsonData(eachObjectArray[0],eachObjectArray[1],(String[])eachObjectArray[2]);
				if(!StringUtil.isEmpty(eachCompareData)){
					jsonArr.add(eachCompareData);
				}
			}
			
		}
		return (jsonArr==null || jsonArr.size()<=0)?null:jsonArr.toString();
	}
	
	public static String compareJsonData(Object o,Object n,String[] fieldArr){
		List<Object[]> diffDataList=compareData(o,n,fieldArr);
		if(diffDataList==null || diffDataList.size()<=0){
			return null;
		}
		JSONObject fullJson=new JSONObject();
		JSONObject oJson = new JSONObject();
		JSONObject nJson = new JSONObject();
		fullJson.put("beanName", diffDataList.get(0)[0]);
		for(Object[] eachObj : diffDataList){
			if(eachObj != null){
				oJson.put(String.valueOf(eachObj[1]), eachObj[2]==null?"":eachObj[2]);
				nJson.put(String.valueOf(eachObj[1]), eachObj[3]==null?"":eachObj[3]);
			}
		}
		fullJson.put("o", oJson);
		fullJson.put("n", nJson);
		
//		JSONObject 
		
		return fullJson.toString();
	}
	
	/**
	 * 两个相同的Bean对象中的数据不同之处
	 * @param o
	 * @param n
	 * @return
	 */
	public static List<Object[]> compareData(Object o,Object n,String[] fieldArr){
		if (o == null && n == null) {
			return null;
		}
		String oName = "";
		String nName = "";
		String beanName = "";
		if (o != null) {
			oName = o.getClass().getName();
		}
		if (!"".equals(oName)) {
			beanName = oName;
		}
		if (n != null) {
			nName = n.getClass().getName();
		}
		if (!"".equals(nName)) {
			beanName = nName;
		}

		// 两个Bean的类不相同时,返回Null
		if (o != null && n != null) {
			if (!oName.equalsIgnoreCase(nName)) {
				return null;
			}
		}
		String[] fieldNameArray = null;
		if (o != null) {
			fieldNameArray = getFieldFromObject(o);
		}
		if (n != null && fieldNameArray == null) {
			fieldNameArray = getFieldFromObject(n);
		}

		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] eachData = null;
		if(o == null || n == null){
			for (String eachFieldName : fieldNameArray) {
				if (eachFieldName != null) {
					eachData=new Object[4];
					eachData[0]=beanName;
					eachData[1]=eachFieldName;
					eachData[2]=(o == null)?null:getFieldValue(o, eachFieldName, true);
					eachData[3]=(n == null)?null:getFieldValue(n, eachFieldName, true);
					dataList.add(eachData);
				}
			}
			return dataList;
		}
		Object oFieldValue = null;
		Object nFieldValue = null;
		for (String eachFieldName : fieldNameArray) {
			if (eachFieldName != null) {
				oFieldValue = getFieldValue(o, eachFieldName, true);
				nFieldValue = getFieldValue(n, eachFieldName, true);
				if (fieldArr != null && fieldArr.length > 0) {
					for (String mustFieldName : fieldArr) {
						if (eachFieldName.equalsIgnoreCase(mustFieldName)) {
							eachData = new Object[4];
							eachData[0] = beanName;
							eachData[1] = eachFieldName;
							eachData[2] = oFieldValue;
							eachData[3] = nFieldValue;
							dataList.add(eachData);
						}
					}
				}
				
				// 两个Value都为null时不做处理
				if (oFieldValue == null && nFieldValue == null) {
					continue;
				}
				if ((oFieldValue == null && nFieldValue != null)
						|| (oFieldValue != null && nFieldValue == null)
						|| !oFieldValue.equals(nFieldValue)) {
					eachData = new Object[4];
					eachData[0] = beanName;
					eachData[1] = eachFieldName;
					eachData[2] = oFieldValue;
					eachData[3] = nFieldValue;
					dataList.add(eachData);
				}
			}
		}
		
		fieldNameArray = null;
		oFieldValue = null;
		nFieldValue = null;
		return dataList;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 对象数据拷贝
	 * 
	 * 返回 toObject
	 * 
	 * id,username,password
	 * */
	
	public static Object copyObject(Object fromObject,Object toObject,String copyField) throws Exception{
		if(copyField==null ||  "".endsWith(copyField)){
			return null;
		}
		Object fromValue=null;
		Method fromMethodGet,toMethodGet,toMethodSet;
		Class fromClass;
		Class toClass;
		fromClass=fromObject.getClass();
		toClass=toObject.getClass();
		char[] nameChars;
		String tmpMethodName;
		Field[] fromFieldArr = fromObject.getClass().getDeclaredFields(); 
		String tmpField=","+copyField+",";
		String oneFieldName="";
		final int  arrLen=fromFieldArr.length;
		for(int ii=0;ii<arrLen;ii++){
			oneFieldName = fromFieldArr[ii].getName();    
			if(tmpField.indexOf(","+oneFieldName+",")>-1){
				nameChars=oneFieldName.toCharArray();
				nameChars[0]-=32;
				tmpMethodName=new String(nameChars);
				fromMethodGet = fromClass.getMethod("get"+tmpMethodName);
				toMethodGet = toClass.getMethod("get"+tmpMethodName);
				fromValue=(Object) fromMethodGet.invoke(fromObject);
				toMethodSet=toClass.getDeclaredMethod("set"+tmpMethodName, fromFieldArr[ii].getType()); 
				if(toMethodSet!=null && toObject!=null){
					toMethodSet.invoke(toObject, fromValue);
				}
			}
		}
		oneFieldName="";
		nameChars=null;
		fromFieldArr=null;
		fromMethodGet=null;
		toMethodGet=null;
		toMethodSet=null;
		fromClass=null;
		toClass=null;
		return toObject;
	}
	
	

}
