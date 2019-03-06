package com.online.college.web.auth;

import java.io.PrintWriter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.online.college.common.util.JsonUtil;
import com.online.college.common.web.HttpHelper;
import com.online.college.common.web.JsonView;

/**
 * shiro 对用户是否登录的 filter
 */
public class AuthFilter extends FormAuthenticationFilter {
	private static final Integer SHIRO_TIME_OUT = 1001;
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		return super.isAccessAllowed(request, response, mappedValue);
	}
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,ServletResponse response) throws Exception {
		HttpServletRequest httpservletrequest = (HttpServletRequest) request;
		// 获取请求路径
		String login = httpservletrequest.getServletPath();
		// 判断请求路径是否为登录页 如果为登录页放行
		if (login.equals("/index.html")) {
			return true;
		}
		// 获取当前登录用户
		Subject subject = getSubject(request, response);
		// 判断是否授权
		if (subject.isAuthenticated()) {
			return true;
		}
		
		// 判断是否为ajax请求
		if (HttpHelper.isAjaxRequest(httpservletrequest)) {
			JsonView jv = new JsonView();
			jv.setMessage("SHIRO登录超时");
			jv.setErrcode(SHIRO_TIME_OUT);
			HttpServletResponse _response = (HttpServletResponse) response;
			PrintWriter pw = _response.getWriter();
			_response.setContentType("application/json");
			pw.write(JsonUtil.toJson(jv));
			pw.flush();
			pw.close();
		} else {
			saveRequestAndRedirectToLogin(request, response);
		}
		
		// 如果没有授权则跳转到登录页面
		return false;
	}
	
}
