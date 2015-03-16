package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConfConstant;
import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.entity.ConfBase;
import com.bizconf.vcaasz.entity.ConfUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.service.ConfServiceForPad;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class ConfServiceForPadImpl  extends BaseService implements ConfServiceForPad {
	private final Logger logger=Logger.getLogger(ConfServiceForPadImpl.class);
	private final String PAD_CONF_SORT_ASC="1";	//会议开始时间正排
	private final String PAD_CONF_SORT_DESC="2";//会议开始时间倒排
	private final String PAD_QUERY_TYPE_PARTED="0";//我参与的会议
	private final String PAD_QUERY_TYPE_CREATED="1";//我创建的会议
	private final String PAD_QUERY_TYPE_ALL="2";//所有的会议
	private final String PAD_CONF_TYPE_IMMED="0";//即时会议
	private final String PAD_CONF_TYPE_RESER="1";//预约会议
	private final String PAD_CONF_TYPE_CYCLE="2";//周期会议
	private final String PAD_CONF_TYPE_PERMANENT="3";//永久会议


	
	public List<ConfBase> getConfListForPad(UserBase userBase,Date startDate,Date endDate,String queryType,
			int pageNum,int pageSize,String startDateSort,String confType){
		logger.info(userBase);
		logger.info("startDate="+startDate);
		logger.info("endDate="+endDate);
		logger.info("queryType="+queryType);
		logger.info("pageNum="+pageNum);
		logger.info("pageSize="+pageSize);
		logger.info("startDateSort="+startDateSort);
		logger.info("confType="+confType);
		logger.info("");
		List<ConfBase> confList=null;
		
		if(userBase==null || startDate==null || endDate==null){
			return confList;
		}
		StringBuffer sqlBuffer=new StringBuffer();
		List<Object> valueList=new ArrayList<Object>();
		

		sqlBuffer.append(" SELECT tcb.* FROM t_conf_base tcb ");
		sqlBuffer.append("	WHERE tcb.`site_id` = ? ");
		valueList.add(userBase.getSiteId());
		
		sqlBuffer.append("		AND tcb.`conf_status` <= ?	");
		valueList.add(ConfConstant.CONF_STATUS_OPENING);
		
		sqlBuffer.append("		AND tcb.`del_flag` = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		
		sqlBuffer.append("		AND tcb.`permanent_conf` = ? ");
		
		if(PAD_CONF_TYPE_PERMANENT.equals(confType)){
			valueList.add(ConfConstant.CONF_PERMANENT_ENABLED_MAIN);
		}else{
			valueList.add(ConfConstant.CONF_PERMANENT_UNABLE);
		}
		
		sqlBuffer.append("		AND ( ");
		sqlBuffer.append("				(tcb.start_time <= ? and tcb.end_time > ?) ");
		valueList.add(startDate);
		valueList.add(startDate);
		
		sqlBuffer.append("				OR (tcb.start_time <= ? AND tcb.end_time > ?)  ");
		valueList.add(endDate);
		valueList.add(endDate);
		
		sqlBuffer.append("				OR (tcb.start_time >= ? AND tcb.end_time <= ?) ");
		valueList.add(startDate);
		valueList.add(endDate);
		
		sqlBuffer.append("			) ");
		if(!PAD_CONF_TYPE_PERMANENT.equals(confType)){
			if(PAD_QUERY_TYPE_CREATED.equals(queryType)){
					sqlBuffer.append("	AND tcb.`create_user` = ? ");
					valueList.add(userBase.getId());
			}
			if(StringUtil.isEmpty(queryType) || PAD_QUERY_TYPE_PARTED.equals(queryType)){
				
					sqlBuffer.append("	AND tcb.`create_user` != ? ");
					valueList.add(userBase.getId());
				sqlBuffer.append("	AND (");
				//-- 公开会议
				sqlBuffer.append("			tcb.`public_flag` = ? ");
				valueList.add(ConfConstant.CONF_PUBLIC_FLAG_TRUE);
				
				sqlBuffer.append("			OR tcb.`public_flag` = ? ");
				valueList.add(ConfConstant.CONF_PUBLIC_FLAG_USER);
				
				sqlBuffer.append("			OR tcb.`id` IN (");
				sqlBuffer.append("					SELECT tcu.conf_id FROM t_conf_user tcu WHERE  tcu.conf_id = tcb.id ");
				sqlBuffer.append("							AND tcu.user_id = ?  AND tcu.host_flag = ? AND tcb.public_flag = ?");
				valueList.add(userBase.getId());
				valueList.add(ConfConstant.CONF_USER_PARTICIPANT);
				valueList.add(ConfConstant.CONF_PUBLIC_FLAG_FALSE);
				
				
				sqlBuffer.append("			)");
				sqlBuffer.append("		)");
				
				sqlBuffer.append("");
			}
		}
		
		sqlBuffer.append("");
		sqlBuffer.append("");
		sqlBuffer.append(" order by tcb.start_time ");
		sqlBuffer.append(PAD_CONF_SORT_DESC.equals(startDateSort)?" desc ":" asc ");
		
		
		
		
		
		
//		//我主持的、所有注册用户可见的、所有公开会议、我被邀请的、永久会议
//		sqlBuffer.append(" select distinct tcb.* from t_conf_base tcb ");
//		sqlBuffer.append(" where tcb.del_flag=? and tcb.site_id=? ");
//		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
//		valueList.add(userBase.getSiteId());
//		sqlBuffer.append(" and (");
//		if(PAD_QUERY_TYPE_CREATED.equals(queryType) || PAD_QUERY_TYPE_ALL.equals(queryType)){//我创建的会议
//			sqlBuffer.append("  tcb.create_user=? ");
//			valueList.add(userBase.getId());
//		}
//		if(PAD_QUERY_TYPE_PARTED.equals(queryType)  || PAD_QUERY_TYPE_ALL.equals(queryType)){//所有注册用户可见的、所有公开会议、我被邀请的
//			if(PAD_QUERY_TYPE_ALL.equals(queryType)){
//				sqlBuffer.append(" or ");
//			}
//			sqlBuffer.append("( ");
//			sqlBuffer.append(" tcb.public_flag=? ");//公开会议
//			valueList.add(ConfConstant.CONF_PUBLIC_FLAG_TRUE);
//			sqlBuffer.append(" or tcb.public_flag=?");//所有注册用户可见的会议
//			valueList.add(ConfConstant.CONF_PUBLIC_FLAG_USER);
//			sqlBuffer.append(" or ( tcb.id in (");
//			sqlBuffer.append(" 		select tcu.conf_id from t_conf_user tcu where  tcu.conf_id = tcb.id ");
//			sqlBuffer.append(" 					and tcu.user_id=?  and tcu.host_flag=?");
//			valueList.add(userBase.getId());
//			valueList.add(ConfConstant.CONF_HIDE_FLAG_FALSE);
//			sqlBuffer.append(" 					and tcb.public_flag=? ");
//			valueList.add(ConfConstant.CONF_PUBLIC_FLAG_FALSE);
//			sqlBuffer.append(" 		)");
//			sqlBuffer.append(" 	)");
//			sqlBuffer.append(" )");
//		}
//		sqlBuffer.append(" )");
//		
//		
//		sqlBuffer.append("  and (");
//		sqlBuffer.append(" 		tcb.permanent_conf <= ?");//非永久会议+永久会议主会议
//		valueList.add(ConfConstant.CONF_PERMANENT_ENABLED_MAIN);
//		
//		sqlBuffer.append(" 			and (");
//		sqlBuffer.append(" 					(tcb.start_time<=? and tcb.end_time>?)");
//		valueList.add(startDate);
//		valueList.add(startDate);
//		sqlBuffer.append(" 					or ");
//		sqlBuffer.append(" 					(tcb.start_time<=? and tcb.end_time>?)");
//		valueList.add(endDate);
//		valueList.add(endDate);
//		sqlBuffer.append(" 					or ");
//		sqlBuffer.append(" 					(tcb.start_time>=? and tcb.end_time <=?)");
//		valueList.add(startDate);
//		valueList.add(endDate);
//		sqlBuffer.append(" 				)");
//		sqlBuffer.append(" 		)");
//		
//		sqlBuffer.append(" ");
//		sqlBuffer.append(" ");
//		sqlBuffer.append(" ");
//		sqlBuffer.append(" ");
		
	
		sqlBuffer.append("");
		sqlBuffer.append("");
		sqlBuffer.append("");
		sqlBuffer.append("");
		logger.info("sqlBuffer=="+sqlBuffer);
		try {
			confList= libernate.getEntityListBase(ConfBase.class, sqlBuffer.toString(), valueList.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return confList;
	}
	
	

	@Override
	public ConfBase getConfBaseByHwId(String hwId) {

		if (!StringUtil.isEmpty(hwId)) {
			try {
				return libernate.getEntity(ConfBase.class, "conf_hwid", hwId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 根据主持人会议安全号取到会议信息
	 * wangyong
	 * 2013-3-11
	 */
	public ConfBase getConfBaseByCompereSecure(String compereSecure){
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_conf_base WHERE 1=1 AND compere_secure = ? ");
		Object[] values = new Object[1];
		values[0] = compereSecure;
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！",e);
		}
		return confBase;
	}
	
	/**
	 * 根据与会者会议安全号取到会议信息
	 * wangyong
	 * 2013-3-11
	 */
	public ConfBase getConfBaseByUserSecure(String userSecure){
		ConfBase confBase = null;
		StringBuffer strSql = new StringBuffer(" SELECT * FROM t_conf_base WHERE 1=1 AND user_secure = ? ");
		Object[] values = new Object[1];
		values[0] = userSecure;
		try {
			confBase = libernate.getEntityCustomized(ConfBase.class, strSql.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！",e);
		}
		return confBase;
	}
	
	
	
	
	public List<ConfUser> getUserListByHwId(String hwId){
		List<ConfUser> userList = null;
		if(StringUtil.isEmpty(hwId)){
			return null;
		}
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT tcu.* FROM t_conf_user tcu,t_conf_base tcb ");
		sqlBuffer.append(" WHERE tcu.`del_flag`=? AND tcb.`del_flag`=? AND tcu.`conf_id`=tcb.`id` ");
		sqlBuffer.append(" 		AND tcb.`conf_hwid`=? ");
		sqlBuffer.append("");
		Object[] values = new Object[3];
		values[0] = ConstantUtil.DELFLAG_UNDELETE;
		values[1] = ConstantUtil.DELFLAG_UNDELETE;
		values[2] = hwId;
		try {
			userList = libernate.getEntityListBase(ConfUser.class, sqlBuffer.toString(), values);
		} catch (SQLException e) {
			logger.error("根据会议ID号获取会议信息出错！",e);
		}
		
		return userList;
		
		
	}
}
