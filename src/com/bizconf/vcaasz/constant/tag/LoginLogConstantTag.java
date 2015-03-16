package com.bizconf.vcaasz.constant.tag;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import com.bizconf.vcaasz.constant.LoginLogConstant;

public class LoginLogConstantTag  extends TagSupport {
	private static final long serialVersionUID = 2759673510971259743L;

	private final Logger logger = Logger.getLogger(this.getClass());
	public String clzName = LoginLogConstant.class.getName();
	protected String var = null;
	 public int doStartTag() throws JspException {
	        Class c = null;
	        try {
	            c = Class.forName(clzName);
	        } catch (ClassNotFoundException cnf) {
	        	logger.error("ClassNotFound - maybe a typo?");
	            throw new JspException(cnf.getMessage());
	        } 

	       try {
	            if (var == null || var.length() == 0) {
	                throw new JspException("常量参数var必须填写！");
	            } else {
	                try {
	                    Object value = c.getField(var).get(this);
	                    pageContext.setAttribute(var, value);
//	                    try {
//							pageContext.getOut().print(value);
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
	                } catch (NoSuchFieldException nsf) {
	                	logger.error(nsf.getMessage());
	                    throw new JspException(nsf);
	                }
	            }
	         } catch (IllegalAccessException iae) {
	        	 logger.error("Illegal Access Exception - maybe a classloader issue?");
	            throw new JspException(iae);
	        }
	     return (SKIP_BODY);
	}

	@Override
	public int doEndTag() throws JspException {

		return this.EVAL_PAGE;
	}

	/**
	 * @jsp.attribute
	 */
	public void setVar(String var) {
		this.var = var;
	}

	public String getVar() {
		return (this.var);
	}

	/**
	 * Release all allocated resources.
	 */
	public void release() {
		super.release();
		clzName = null;
	}

	private static final Map scopes = new HashMap();
	static {
		scopes.put("page", new Integer(PageContext.PAGE_SCOPE));
		scopes.put("request", new Integer(PageContext.REQUEST_SCOPE));
		scopes.put("session", new Integer(PageContext.SESSION_SCOPE));
		scopes.put("application", new Integer(PageContext.APPLICATION_SCOPE));
	}

	public int getScope(String scopeName) throws JspException {
		Integer scope = (Integer) scopes.get(scopeName.toLowerCase());

		if (scope == null) {
			throw new JspException("Scope '" + scopeName
					+ "' not a valid option");
		}

		return scope.intValue();
	}
}
