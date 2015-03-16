package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.Notice;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.PageModel;
import com.bizconf.vcaasz.entity.SystemUser;
import com.bizconf.vcaasz.entity.UserBase;
import com.bizconf.vcaasz.logic.NoticeLogic;
import com.bizconf.vcaasz.service.NoticeService;
import com.bizconf.vcaasz.util.DateUtil;
import com.bizconf.vcaasz.util.StringUtil;

@Service
public class NoticeServiceImpl extends BaseService implements NoticeService {
	private final Logger logger = Logger.getLogger(NoticeServiceImpl.class);

	@Autowired
	NoticeLogic noticeLogic;
	
	/**
	 * 系统管理员发布系统公告
	 * 注意：需要事务控制！
	 * @param sysUser 系统用户
	 * @param title 主题
	 * @param content  内容 
	 * @param expireTime  过期时间
	 * @return
	 * @author wangyong
	 * @date 2013.1.17
	 */
	@Override
	public boolean publishSysNotice(SystemUser sysUser, String title,
			String content, Date expireTime) {
		boolean saveFlag = false;
		if(!noticeLogic.publishNoticeValidate(title, content, expireTime)){
			return false;
		}
		Notice notice = new Notice();
		String updateSql = "update t_notice set notice_status = ? where site_id =? and notice_status = ? and del_flag = ? ";
		if(sysUser != null){
			try{
				Object[] values = new Object[4];
				values[0] = ConstantUtil.NOTICE_UNPUBLISHED;   
				values[1] = 0;
				values[2] = ConstantUtil.NOTICE_PUBLISHED;
				values[3] = ConstantUtil.DELFLAG_UNDELETE;
				DAOProxy.getLibernate().executeSql(updateSql, values);    //删除当前正在发布的系统公告中
				notice.setBrowseCount(0);
				notice.setContent(content);
				notice.setCreateTime(DateUtil.getGmtDate(null));
				notice.setCreateUser(sysUser.getId());
				notice.setDelFlag(1);
				notice.setDelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
				notice.setDelUser(0);
				notice.setSiteId(0);    //SiteId系统公告为0
				notice.setStartTime(DateUtil.getGmtDate(null));
				notice.setNoticeStatus(1);
				notice.setStopTime(DateUtil.getGmtDate(expireTime));
				notice.setTitle(title);
				Notice noticeBack = DAOProxy.getLibernate().saveEntity(notice);
				if(noticeBack != null){    //DAOProxy.getLibernate().saveEntity若未能插入数据返回null
					saveFlag = true;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return saveFlag;
	}

	/**
	 * 系统管理员修改公告信息
	 * @param noticeId  
	 * @param title
	 * @param content
	 * @param expireTime
	 * @param sysUser  系统用户
	 * @return
	 * @author wangyong
	 * @date 2013.1.17
	 */
	@Override
	public boolean updateSysNotice(Integer noticeId, String title,
			String content, Date expireTime) {
		boolean saveFlag = false;
		if(!noticeLogic.updateNoticeValidate(title, content, expireTime)){
			return false;
		}
		Notice notice = new Notice();
		if(noticeId != null && noticeId.intValue() > 0){
			notice.setId(noticeId);
			if(StringUtil.isNotBlank(title)){
				notice.setTitle(title);
			}
			if(StringUtil.isNotBlank(content)){
				notice.setContent(content);
			}
			if(expireTime != null){
				notice.setStopTime(DateUtil.getGmtDate(expireTime));
			}
			try{
				Notice noticeBack = DAOProxy.getLibernate().updateEntity(notice, "noticeId", "title", "content", "stopTime");
				if(noticeBack != null){    //DAOProxy.getLibernate().updateEntity若未能插入数据返回null
					saveFlag = true;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return saveFlag;
	}
	
	/**
	 * 系统管理员根据主键ID号删除公告

	 * @param id
	 * @param sysUser
	 * @author wangyong
	 * @date 2013.1.17
	 * @return
	 */
	@Override
	public boolean deleteSysNoticeById(Integer id, SystemUser sysUser) {
		boolean updateFlag = false;
		if(id != null && id.intValue()>0 && sysUser != null){
			String updateSql = "update t_notice set del_flag = ?,del_time = ?,del_user = ? where id = ? ";
			Object[] values = new Object[4];
			values[0] = ConstantUtil.DELFLAG_DELETED;
			values[1] = DateUtil.getDateStrCompact(DateUtil.getGmtDate(null), "yyyy-MM-dd HH:mm:ss");
			values[2] = sysUser.getId();
			values[3] = id;
			try{
				updateFlag = DAOProxy.getLibernate().executeSql(updateSql, values);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return updateFlag;
	}

	/**
	 * 站点管理员发布公告信息
	 * 
	 * @param userAdmin
	 * @param title
	 * @param content
	 * @param expireTime
	 * @return
	 */
//	@Override
//	public boolean publishNotice(UserBase userAdmin, String title,
//			String content) {
//		boolean saveFlag = false;
//		if(!noticeLogic.publishNoticeValidate(title, content, null)){
//			return false;
//		}
//		Notice notice = new Notice();
//		if(userAdmin != null){
//			try{
//				notice.setBrowseCount(0);
//				notice.setContent(content);
//				notice.setCreateTime(DateUtil.getGmtDate(null));
//				notice.setCreateUser(userAdmin.getId());
//				notice.setDelFlag(1);
//				notice.setDelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
//				notice.setDelUser(0);
//				notice.setSiteId(userAdmin.getSiteId());
//				notice.setStartTime(DateUtil.getGmtDate(null));
//				notice.setNoticeStatus(1);
//				notice.setStopTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
//				notice.setTitle(title);
//				Notice noticeBack = DAOProxy.getLibernate().saveEntity(notice);
//				if(noticeBack != null){    //DAOProxy.getLibernate().saveEntity若未能插入数据返回null
//					saveFlag = true;
//				}
//			}catch (Exception e){
//				e.printStackTrace();
//			}
//		}
//		return saveFlag;
//	}
	
	/**
	 * 站点管理员发布公告信息
	 * 
	 * @param userAdmin
	 * @param title
	 * @param content
	 * @param expireTime
	 * @return
	 */
	@Override
	public boolean publishNotice(UserBase userAdmin, Notice notice) {
		boolean saveFlag = false;
		if(!noticeLogic.publishNoticeValidate(notice.getTitle(), notice.getContent(), null)){
			return false;
		}
		if(userAdmin != null){
			try{
				notice.setBrowseCount(0);
				notice.setCreateTime(DateUtil.getGmtDate(null));
				notice.setCreateUser(userAdmin.getId());
				notice.setDelFlag(1);
				notice.setDelTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
				notice.setDelUser(0);
				notice.setSiteId(userAdmin.getSiteId());
				notice.setStartTime(DateUtil.getGmtDate(null));
				notice.setNoticeStatus(1);
				notice.setStopTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1970-01-01 00:00:00"));
				Notice noticeBack = DAOProxy.getLibernate().saveEntity(notice);
				if(noticeBack != null){    //DAOProxy.getLibernate().saveEntity若未能插入数据返回null
					saveFlag = true;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return saveFlag;
	}

	/**
	 * 站点管理员修改公告信息
	 * @param noticeId
	 * @param title
	 * @param content
	 * @param expireTime
	 * @return
	 */
	@Override
	public boolean updateNotice(Integer noticeId, String title, String content, Integer loginedFlag) {
		boolean saveFlag = false;
		if(!noticeLogic.updateNoticeValidate(title, content, null)){
			return false;
		}
		Notice notice = new Notice();
		if(noticeId != null && noticeId.intValue() > 0){
			notice.setId(noticeId);
			if(StringUtil.isNotBlank(title)){
				notice.setTitle(title);
			}
			if(StringUtil.isNotBlank(content)){
				notice.setContent(content);
			}
			notice.setLoginedFlag(loginedFlag);
//			if(expireTime != null){
//				notice.setStopTime(DateUtil.getGmtDate(expireTime));
//			}
			try{
				Notice noticeBack = DAOProxy.getLibernate().updateEntity(notice, "id", "title", "content", "loginedFlag");
				if(noticeBack != null){    //DAOProxy.getLibernate().updateEntity若未能插入数据返回null
					saveFlag = true;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return saveFlag;
	}

	/**
	 * 根据主键ID查询公告信息
	 * @author wangyong
	 * @date 2013.1.17
	 * @param id
	 * @return
	 */
	@Override
	public Notice getNoticeById(Integer id) {
		Notice notice = null;
		if(id != null && id.intValue()>0){
			StringBuffer strSql = new StringBuffer(" select * from t_notice where id = ? ");
			Object[] values = new Object[1];
			try {
				values[0] = id;
				notice = DAOProxy.getLibernate().getEntityCustomized(Notice.class, strSql.toString(), values);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		return notice;
	}

	/**
	 * 站点管理员根据主键ID号删除公告
	 * @param id
	 * @param siteAdmin   站点管理员
	 * @return
	 */
	@Override
	public boolean deleteNoticeById(Integer id, UserBase siteAdmin) {
		boolean updateFlag = false;
		if(id != null && id.intValue()>0 && siteAdmin != null){
			String updateSql = "update t_notice set del_flag = ?,del_time = ?,del_user = ? where id = ? ";
			Object[] values = new Object[4];
			values[0] = ConstantUtil.DELFLAG_DELETED;
			values[1] = DateUtil.getDateStrCompact(DateUtil.getGmtDate(null), "yyyy-MM-dd HH:mm:ss");
			values[2] = siteAdmin.getId();
			values[3] = id;
			try{
				updateFlag = DAOProxy.getLibernate().executeSql(updateSql, values);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return updateFlag;
	}

	/**
	 * 获取系统管理员发布的公告信息
	 * 
	 * @param pageModel
	 * @author wangyong
	 * @date 2013.1.17
	 * @return
	 */
	@Override
	public List<Notice> getSysNoticeList(PageModel pageModel) {
		List<Notice> noticeList = null;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(" select * from t_notice where site_id = 0 and del_flag = ? order by id desc");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		if(pageModel != null){
			int recordNo = (Integer.parseInt(pageModel.getPageNo())-1) * pageModel.getPageSize();   //当前页第一条记录在数据库中位置
			strSql.append(" limit ? , ?  ");
			valueList.add(recordNo);
			valueList.add(pageModel.getPageSize());
		}
		try{
			Object[] values = valueList.toArray();
			noticeList = DAOProxy.getLibernate().getEntityListBase(Notice.class, strSql.toString(), values);
		}catch (Exception e){
			e.printStackTrace();
		}
		return noticeList;
	}

	/**
	 * 统计系统管理员发布的信息数量
	 * @author wangyong
	 * @date 2013.1.17
	 * @return
	 */
	@Override
	public Integer countSysNotice() {
		int rows = 0;
		StringBuffer strSql = new StringBuffer(" select count(1) from t_notice where site_id = 0 and del_flag = ?");
		Object[] values = new Object[1];
		values[0] = ConstantUtil.DELFLAG_UNDELETE;
		try {
			rows = DAOProxy.getLibernate().countEntityListWithSql(strSql.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * 根据站点ID号获取站点发布的公告信息列表
	 * @param pageModel
	 * @param siteUserId  普通站点管理员id(当超级站点管理员查询时，传入null即可)
	 * @return
	 */
	@Override
	public List<Notice> getNoticeListBySiteId(Integer siteId,
			PageModel pageModel, Integer siteUserId) {
		List<Notice> noticeList = null;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(" SELECT a.* FROM  t_notice a, t_user_base b WHERE 1=1 ");
		if(siteId != null && siteId.intValue() > 0){
			strSql.append(" AND a.site_id = ? ");
			valueList.add(siteId.intValue());
		}
		strSql.append(" AND a.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND b.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND a.create_user=b.id ");
		if(siteUserId != null && siteUserId.intValue() > 0){
			strSql.append(" AND b.create_user = ? ");
			valueList.add(siteUserId.intValue());
		}
		strSql.append(" order by a.start_time DESC ");   //查出列表无排序条件则为默认逆序
		if(pageModel != null){
			int recordNo = (Integer.parseInt(pageModel.getPageNo())-1) * pageModel.getPageSize();   //当前页第一条记录在数据库中位置
			strSql.append(" limit ? , ?  ");
			valueList.add(recordNo);
			valueList.add(pageModel.getPageSize());
		}
		try {
			Object[] values = valueList.toArray(); 
			noticeList = DAOProxy.getLibernate().getEntityListBase(Notice.class, strSql.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}

	/**
	 * 根据站点Id号统计发布的公告总数量
	 * @param siteId
	 * @param siteUserId  普通站点管理员id(当超级站点管理员查询时，传入null即可)
	 * @return
	 */
	@Override
	public Integer countNoticeBySiteId(Integer siteId, Integer siteUserId) {
		int rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(" SELECT count(1) FROM  t_notice a, t_user_base b WHERE 1=1 ");
		if(siteId != null && siteId.intValue() > 0){
			strSql.append(" AND a.site_id = ? ");
			valueList.add(siteId.intValue());
		}
		strSql.append(" AND a.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND b.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		strSql.append(" AND a.create_user = b.id ");
		if(siteUserId != null && siteUserId.intValue() > 0){
			strSql.append(" AND b.create_user = ? ");
			valueList.add(siteUserId.intValue());
		}
		Object[] values = valueList.toArray();
		try {
			rows = DAOProxy.getLibernate().countEntityListWithSql(strSql.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * 根据站点标识获取站点发布的公告信息列表
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<Notice> getNoticeListBySiteSign(String siteSign,
			PageModel pageModel) {
		return null;
	}

	/**
	 * 根据站点标识统计发布的公告总数量
	 * @param siteId
	 * @return
	 */
	@Override
	public List<Notice> countNoticeBySiteSign(String siteSign) {
		return null;
	}

	/**
	 * 获取正在发布的系统公告
	 * @author wangyong
	 * @date 2013.1.17
	 * @return
	 */
	@Override
	public Notice getCurrentSystemNotice() {
		List<Notice> noticeList = null;
		StringBuffer strSql = new StringBuffer(" select * from t_notice where site_id = 0 and del_flag = ? and notice_status = ? and stop_time > ?");
		try{
			Object[] values = new Object[3];
			values[0] = ConstantUtil.DELFLAG_UNDELETE;
			values[1] = ConstantUtil.NOTICE_PUBLISHED;
			values[2] = DateUtil.getGmtDate(null);
			noticeList = DAOProxy.getLibernate().getEntityListBase(Notice.class, strSql.toString(), values);
			if(noticeList != null && noticeList.size()>0){
				return noticeList.get(0);
			}
		}catch (Exception e){
			logger.error("获取正在发布的系统公告出错！"+e);
		}
		return null;
	}


	@Override
	public Integer countNoticeByUserIds(Integer siteId, Integer[] userIds) {
		int rows = 0;
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(" select count(1) from t_notice where 1=1 AND del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		if(siteId != null && siteId.intValue() > 0){
			strSql.append(" AND site_id = ? ");
			valueList.add(siteId.intValue());
		}
//		if(userIds != null && userIds.length>0){
//			strSql.append(" and create_user in (-1 ");
//			for(Integer id:userIds){
//				strSql.append(" , ");
//				strSql.append(" ? ");
//				valueList.add(id);
//			}
//			strSql.append(" ) ");
//		} 
		try {
			rows = DAOProxy.getLibernate().countEntityListWithSql(strSql.toString(), valueList.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	/**
	 * 企业用户获取公告
	 * @author alan
	 * @date 2013.3.6
	 */
	@Override
	public List<Notice> getUserNoticeList(Integer siteId, PageModel pageModel, Integer[] userIds) {
		List<Notice> noticeList = null;
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder strSql = new StringBuilder(" SELECT n.* FROM  t_notice n where 1=1 AND n.del_flag = ? ");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		if(siteId != null && siteId.intValue() > 0){
			strSql.append(" AND n.site_id = ? ");
			valueList.add(siteId.intValue());
		}
		
//		if(userIds != null && userIds.length>0){
//			strSql.append(" and n.create_user in (-1 ");
//			for(Integer id:userIds){
//				strSql.append(" , ");
//				strSql.append(" ? ");
//				valueList.add(id);
//			}
//			strSql.append(" ) ");
//		} 
		strSql.append(" order by n.start_time DESC ");   //查出列表无排序条件则为默认逆序
		if(pageModel != null && pageModel.getRowsCount()>0){
			int recordNo = (Integer.parseInt(pageModel.getPageNo())-1) * pageModel.getPageSize();   //当前页第一条记录在数据库中位置
			strSql.append(" limit ? , ?  ");
			valueList.add(recordNo);
			valueList.add(pageModel.getPageSize());
		}
		try {
			Object[] values = valueList.toArray(); 
			noticeList = DAOProxy.getLibernate().getEntityListBase(Notice.class, strSql.toString(), values);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return noticeList;
	}

	@Override
	public PageBean<Notice> getNotLoginedeNoticeList(Integer siteId, int pageNo) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuffer strSql = new StringBuffer(" SELECT * FROM  t_notice where 1=1 AND del_flag = ? AND logined_flag = ?");
		valueList.add(ConstantUtil.DELFLAG_UNDELETE);
		valueList.add(ConstantUtil.NOTICE_FLAG_ANYONE);
		if(siteId != null && siteId.intValue() > 0){
			strSql.append(" AND site_id = ? ");
			valueList.add(siteId.intValue());
		}
		strSql.append(" order by start_time DESC ");   //查出列表无排序条件则为默认逆序
		PageBean<Notice> page = getPageBeans(Notice.class, strSql.toString(), pageNo, valueList.toArray());
		return page;
	}

}
