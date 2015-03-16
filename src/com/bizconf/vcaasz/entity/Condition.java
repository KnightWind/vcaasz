package com.bizconf.vcaasz.entity;

import java.util.ArrayList;
import java.util.List;

public class Condition implements  java.io.Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 233963482215363524L;
	
	private Object object;
	
	/*
	 * Object[]  
	 *     0    比较方式
	 *     1   字段名
	 *     2   字段值
	 *     3   字段值，如果是between则会有第二个值
	 * */
	private List<Object[]> conditionList;
	
	public Condition(){
		conditionList=new ArrayList<Object[]>();
	}
	
	public void equal(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="equal";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}

	public void greaterThan(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="greaterThan";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}
	public void greaterEqual(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="greaterEqual";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}

	public void lessThan(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="lessThan";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}

	public void lessEqual(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="lessEqual";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}


	public void isNull(String fieldName){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="isNull";
		eachCondition[1]=fieldName;
		eachCondition[2]=null;
		conditionList.add(eachCondition);
		eachCondition=null;
	}

	public void isNotNull(String fieldName){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="isNotNull";
		eachCondition[1]=fieldName;
		eachCondition[2]=null;
		conditionList.add(eachCondition);
		eachCondition=null;
	}
	

	public void like(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="like";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}

	public void leftLike(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="leftLike";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}

	public void rightLike(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="rightLike";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}


	public void notLike(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="notLike";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}
	public void notLeftLike(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="notLeftLike";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}

	public void notRightLike(String fieldName,Object value){
		Object[] eachCondition=new Object[3];
		eachCondition[0]="notRightLike";
		eachCondition[1]=fieldName;
		eachCondition[2]=value;
		conditionList.add(eachCondition);
		eachCondition=null;
	}
	

	public void between(String fieldName,Object... value){
		Object[] eachCondition=new Object[4];
		eachCondition[0]="between";
		eachCondition[1]=fieldName;
		eachCondition[2]=value[0];
		eachCondition[3]=value[1];
		conditionList.add(eachCondition);
		eachCondition=null;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getConditionSql(){
		StringBuffer condBuffer=new StringBuffer();
		
		if(conditionList!=null && conditionList.size()>0){
			condBuffer.append(" 1 = 1 ");
			for(Object[] eachCond:conditionList){
				if(eachCond!=null){
					condBuffer.append(" and "+getEverySql(eachCond));
				}
			}
		}
		return condBuffer.toString();
	}
	
	private String getEverySql(Object[] everyCondition){
		String condSql="";
		if(everyCondition!=null && everyCondition.length > 0){
			String operaType=String.valueOf(everyCondition[0]);
			String fieldName=String.valueOf(everyCondition[1]);
			Object eachValue=everyCondition[2];
			String eachValueType="";
			String opera="";
			if(operaType!=null  && !"".equals(operaType.trim())){
				if("".equals(condSql)){
					eachValueType=getTypeName(eachValue);
					if("equal".equals(operaType)){
						opera=" = ";
					}else if("greaterThan".equals(operaType)){
						opera=" > ";
					}else if("greaterEqual".equals(operaType)){
						opera=" >= ";
					}else if("lessThan".equals(operaType)){
						opera=" < ";
					}else if("lessEqual".equals(operaType)){
						opera=" <= ";
					}
					if(opera!=null && !"".equals(opera.trim())){
						if ("short".equals(eachValueType)
								|| "integer".equals(eachValueType)
								|| "long".equals(eachValueType)
								|| "float".equals(eachValueType)
								|| "double".equals(eachValueType)) {
							
							condSql=fieldName + opera + eachValue;
						} else if ("string".equals(eachValueType)
								|| "date".equals(eachValueType)) {
							condSql=fieldName + opera + "'"+escape(eachValue)+"'";
						}
						return condSql;
					}
				}
				if("".equals(condSql)){
					if("like".equals(operaType)){
						condSql=fieldName +" like '%"+escape(eachValue)+"%'";
					}else if("leftLike".equals(operaType)){
						condSql= fieldName +" like '"+escape(eachValue)+"%'";
					}else if("rightLike".equals(operaType)){
						condSql= fieldName +" like '%"+escape(eachValue)+"'";
					}else if("notLike".equals(operaType)){
						condSql= fieldName +"  not like '%"+escape(eachValue)+"%'";
					}else if("notLeftLike".equals(operaType)){
						condSql= fieldName +" not like '"+escape(eachValue)+"%'";
					}else if("notRightLike".equals(operaType)){
						condSql= fieldName + " not like '%"+escape(eachValue)+"'";
					}
				}
				if("isNull".equals(operaType)){
					condSql=fieldName +" is null ";
				}else if("isNotNull".equals(operaType)){
					condSql=fieldName +" is not null ";
				}
				if("between".equals(operaType)){
					Object eachValuea=everyCondition[2];
					Object eachValueb=everyCondition[3];
					String aType=getTypeName(eachValuea);
					String bType=getTypeName(eachValueb);
					if(aType.equals(bType)){
						if ("short".equals(eachValueType)
								|| "integer".equals(eachValueType)
								|| "long".equals(eachValueType)
								|| "float".equals(eachValueType)
								|| "double".equals(eachValueType)) {
							
							condSql=fieldName + " between "+String.valueOf(eachValuea)+" and " + String.valueOf(eachValueb);
						} else if ("string".equals(eachValueType)
								|| "date".equals(eachValueType)) {
							condSql=fieldName + " between '"+String.valueOf(eachValuea)+"' and '" + String.valueOf(eachValueb)+"'";
						}
					}
				}
				
			}
		}
		return condSql;
	}
	
	private String escape(Object str){
		String escapeStr= String.valueOf(str).replaceAll("'", "\\\\'");
		return escapeStr;
		
	}
	private String getTypeName(Object value){
		String typeName="";
		if(value!=null){
			Class clazz=value.getClass();
			if(clazz!=null){
				String clazzName=clazz.getName();
				typeName=clazzName.substring(clazzName.lastIndexOf(".")+1).toLowerCase();
			}
			clazz=null;
		}
		
		return typeName;
	}
/*
 * if ("short".equals(eachValueType)
							|| "integer".equals(eachValueType)
							|| "long".equals(eachValueType)
							|| "float".equals(eachValueType)
							|| "double".equals(eachValueType)) {
						
						opera=fieldName + opera + eachValue;
					} else if ("string".equals(eachValueType)
							|| "date".equals(eachValueType)) {
						opera=fieldName + opera + "'"+eachValue+"'";
					}
 * */

}

