package com.online.college.common.web;

import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringBeanFactory {
	public static Object getBean(String[] paths, String name){
		XmlWebApplicationContext ctx = new XmlWebApplicationContext();
        ctx.setConfigLocations(paths);
        ctx.setServletContext(new MockServletContext(""));
        ctx.refresh();
        return ctx.getBean(name);
	}
	public static Object getBean(String name){
		String[] paths = { "applicationContext.xml" };
		return getBean(paths,name);
	}
}
