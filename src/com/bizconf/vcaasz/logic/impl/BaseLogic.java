package com.bizconf.vcaasz.logic.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bizconf.vcaasz.dao.DAOProxy;
import com.libernate.liberd.Libernate;

/**
 * 其它公共引用也可以加到这里
 * 
 * @author Chris
 *
 */
public class BaseLogic {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	
	protected static Libernate libernate = DAOProxy.getLibernate();
	
}
