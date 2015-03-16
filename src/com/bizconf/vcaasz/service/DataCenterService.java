package com.bizconf.vcaasz.service;

import java.util.List;

import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.RcIP;

/**
 * @desc 数据中心Service
 * @author Darren
 * @date 2014-12-23
 */
public interface DataCenterService {

	/** 查询全部数据中心 */
	public List<DataCenter> queryAll();

	/** 通过Id查找数据中心  */
	public DataCenter queryDataCenterById(Integer id);

	/** 修改数据中心 */
	public boolean updateDataCenter(DataCenter center);

	/** 保存数据中心  */
	public boolean saveDataCenter(DataCenter center);

	/** 删除数据中心 */
	public boolean deleteDataCenterById(DataCenter center);

	/** 通过centerId获取rc的ip列表 */
	public PageBean<RcIP> getRcIpListByCenterId(Integer centerId,int pageNo,int pageSize);

	/** 修改rc的ip */
	public boolean addOrUpdateRC(RcIP rcIP);

	/**
	 * 删除
	 * */
	public boolean delRCIpById(Integer rcId);
	/**
	 * 通过id获取rc的ip 
	 * */
	public RcIP getRcEntityById(Integer id);
	
	/**
	 * 根据数据中心ID查询RC的IP地址
	 * */
	public List<RcIP> getListIPsByCenterId(Integer centerId);
	
	/**
	 * 根据数据中心ID查询RC的IP地址字符串，以“，”分隔开
	 * */
	public String getRCIPs(Integer centerId);
	
}
