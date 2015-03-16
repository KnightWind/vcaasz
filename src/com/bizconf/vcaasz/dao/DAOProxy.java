/*
 * zmlk - LiberDFactory.java
 * 2011-8-2 下午10:39:35
 */
package com.bizconf.vcaasz.dao;

import com.libernate.liberd.Libernate;


/**
 * @Author Chris Gao (hongliang_gao@sina.com)
 * @version 2011-8-2 下午10:39:35
 * 
 */
public class DAOProxy {
	public static Libernate getLibernate() {
		return Libernate.getInstance("libernate.datasource.bizconf.audio");
	}
}
