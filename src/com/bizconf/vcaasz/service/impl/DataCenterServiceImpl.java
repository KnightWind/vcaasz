package com.bizconf.vcaasz.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizconf.vcaasz.entity.DataCenter;
import com.bizconf.vcaasz.entity.PageBean;
import com.bizconf.vcaasz.entity.RcIP;
import com.bizconf.vcaasz.service.DataCenterService;

/**
 * @desc 数据中心Service
 * @author Administrator
 * @date 2014-12-23
 */
@Service
public class DataCenterServiceImpl extends BaseService implements DataCenterService {

	@Override
	public List<DataCenter> queryAll() {
		try {
			return libernate.getEntityList(DataCenter.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<DataCenter>();
	}

	@Override
	public DataCenter queryDataCenterById(Integer id) {
		
		try {
			return libernate.getEntity(DataCenter.class, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateDataCenter(DataCenter center) {
		
		try {
			DataCenter dataCenter = libernate.updateEntity(center);
			if(dataCenter!=null){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean saveDataCenter(DataCenter center) {
		
		try {
			DataCenter dataCenter = libernate.saveEntity(center);
			if(dataCenter!=null){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDataCenterById(DataCenter center) {
		
		try {
			return libernate.deleteEntity(center);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public PageBean<RcIP> getRcIpListByCenterId(Integer centerId,int pageNo,int pageSize) {
		
		String sql = "select * from t_rc_ip where center_id = ?";
		Object[] ids = new Integer[]{centerId};
		return getPageBeans(RcIP.class, sql, pageNo, pageSize,ids);
		
	}

	@Override
	public boolean delRCIpById(Integer rcId) {
		
		try {
			RcIP rcIP = libernate.getEntity(RcIP.class, rcId);
			if(rcIP == null){
				return false;
			}
			libernate.deleteEntity(rcIP);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addOrUpdateRC(RcIP rcIP) {
		try {
			if(rcIP!=null && rcIP.getId() > 0){
				libernate.updateEntity(rcIP, "ip_address");
				return true;
			}else{
				libernate.saveEntity(rcIP);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public RcIP getRcEntityById(Integer id) {
		
		try {
			return libernate.getEntity(RcIP.class, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RcIP> getListIPsByCenterId(Integer centerId) {
		
		try {
			String sql = "select * from t_rc_ip where center_id = ?";
			return libernate.getEntityListBase(RcIP.class, sql, new Object[]{centerId});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String getRCIPs(Integer centerId){
		String rcIpsStr = "";
		try {
			String sql = "select * from t_rc_ip where center_id = ?";
			List<RcIP> rcIPs = libernate.getEntityListBase(RcIP.class, sql, new Object[]{centerId});
			
			if(rcIPs == null){
				return rcIpsStr;
			}
			for(RcIP rc:rcIPs){
				rcIpsStr += rc.getIpAddress() + " , ";
			}
			if(rcIpsStr.trim().endsWith(",")){
				rcIpsStr = rcIpsStr.trim();
				rcIpsStr = rcIpsStr.substring(0, rcIpsStr.length() - 1);
			}
			return rcIpsStr;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rcIpsStr;
	}
}
