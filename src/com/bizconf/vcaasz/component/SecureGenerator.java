package com.bizconf.vcaasz.component;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import com.bizconf.vcaasz.dao.DAOProxy;
import com.libernate.liberd.Libernate;

/**
 * @desc 
 * @author Administrator
 * @date 2013-5-15
 */
public class SecureGenerator {
	
	protected static Libernate libernate = DAOProxy.getLibernate();
//	private static long LASTNUM = 999999999l;
	
	public synchronized static String getSecureNum(){
		
		String sql = "select count(*) from t_conf_base where compere_secure = ? or user_secure = ?";
		String secureNum = "";
		try {
			int count = 1;
			while(count>0){
				secureNum = next();
				count = libernate.countEntityListWithSql(sql, new Object[]{secureNum,secureNum});
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return secureNum;
	}
	
	private static String next(){
		Random random = new Random();
		return String.valueOf((999999999+random.nextInt(999999999)));
	}
	
	 
	  
}
