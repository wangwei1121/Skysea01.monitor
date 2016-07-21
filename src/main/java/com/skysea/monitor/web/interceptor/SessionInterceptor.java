package com.skysea.monitor.web.interceptor;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.skysea.monitor.domain.UserInfo;
import com.skysea.monitor.service.UserInfoService;
import com.skysea.monitor.web.Keys;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if("false".equals(request.getSession().getAttribute(Keys.SESSION_FILTER))){
			return true;
		}
		if(!isAuthenticateSession(request) && !isAuthenticateCookie(request)){
			String contextPath = request.getContextPath();
			if (contextPath.endsWith("/")) {
				contextPath = contextPath.substring(0, contextPath.length() - 1);
			}
			response.sendRedirect(contextPath + "/loginError");
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {
		
	}

	private boolean isAuthenticateSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (null == session) {
			return false;
		}
		if (null == session.getAttribute(Keys.SESSION_USERINFO_KEY)) {
			return false;
		}
		return true;
	}

	private boolean isAuthenticateCookie(HttpServletRequest request){
		Cookie loginNameCookie = WebUtils.getCookie(request, Keys.LOGIN_NAME);
		if(null == loginNameCookie){
			return false;
		}
		Cookie passwordCookie = WebUtils.getCookie(request, Keys.PASSWORD);
		if(null == passwordCookie){
			return false;
		}
		UserInfoService userInfoService = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext()).getBean(UserInfoService.class);
		if(null == userInfoService){
			return false;
		}
		
		UserInfo user = new UserInfo();
		user.setUserName(loginNameCookie.getValue());
		user.setPassword(passwordCookie.getValue());
		List<UserInfo> list = userInfoService.getList(user);
		if (null == list || list.size() != 1) {
			return false;
		}
		request.getSession().setAttribute(Keys.SESSION_USERINFO_KEY, list.get(0));
		return true;
	}
}
