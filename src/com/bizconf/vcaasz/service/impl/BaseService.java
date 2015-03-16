package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.constant.ConstantUtil;
import com.bizconf.vcaasz.dao.DAOProxy;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.UserBase;
import com.libernate.liberd.Libernate;

/**
 * 其它公共引用也可以加到这里
 * 
 * @author Chris
 *
 */
public class BaseService {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	protected static Libernate libernate = DAOProxy.getLibernate();
	
	/**
	 * 一种可复用性直接查询分页对象的查询方法
	 * @param c 查询对象的类
	 * @param sql 查询sql 如select * from  t_tableName 
	 * @param pageNo 当前页
	 * @param pageSize  每页显示条
	 * @param objs  查询参数
	 * @return PageBean pageModel类的改装 兼容pageModel
	 */
	@SuppressWarnings("unchecked")
	public <T>PageBean<T> getPageBeans(Class<?> c,String sql,int pageNo,int pageSize,boolean moreFlag,Object...objs){
		PageBean<T> page = new PageBean<T>();
		if(pageNo<1){
			pageNo = 1;
		}
		if(pageSize<1){
			pageSize = ConstantUtil.PAGESIZE_DEFAULT;
		}
		try {
			sql = sql.toLowerCase();
			String countsql = " select count(*) " + sql.substring(sql.indexOf("from"));
			int count = libernate.countEntityListWithSql(countsql, objs);
			if(!moreFlag && pageNo>1 && count<=(pageNo-1)*pageSize){
				pageNo = pageNo - 1;
			}
			String limitsql = sql+" limit "+(pageNo-1)*pageSize +","+pageSize;
			List<T> dataList = (List<T>) libernate.getEntityListBase(c,limitsql,objs);
			
			page.setPageNo(pageNo+"");
			page.setPageSize(pageSize);
			page.setRowsCount(count);
			page.setDatas(dataList);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return page;
	}
	
	public <T>PageBean<T> getPageBeans(Class<?> c,String sql,Object...objs){
		return getPageBeans(c, sql, 0, 0,objs);
	}
	
	public <T>PageBean<T> getPageBeans(Class<?> c,String sql,int pageNo,Object...objs){
		return getPageBeans(c, sql, pageNo, 0,objs);
	}
	
	public <T>PageBean<T> getPageBeans(Class<?> c,String sql,int pageNo,int pageSize,Object...objs){
		
		return getPageBeans(c, sql, pageNo, pageSize, false,objs);
	}

	public PageBean<UserBase> getHostsBySite(String keyWord, int siteId,
			int pageNo, int pagesize) {
		return null;
	}
	
}
