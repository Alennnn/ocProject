package com.online.college.common.web.shiro;

import java.io.IOException;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.online.college.common.web.shiro.freemarker.ShiroTags;

import freemarker.template.TemplateException;

/**
 * shiro freemarker 整合
 */
public class ShiroFreeMarkerConfigurer extends FreeMarkerConfigurer {
	@Override
	public void afterPropertiesSet() throws IOException, TemplateException {
		super.afterPropertiesSet();
		this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
	}
}
