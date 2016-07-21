package com.skysea.monitor.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.HtmlUtils;

import com.skysea.monitor.domain.UserInfo;
import com.skysea.monitor.web.Keys;

public class BaseController {
	
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	public void setSessionUser(HttpServletRequest request, UserInfo userInfo) {
		request.getSession().setAttribute(Keys.SESSION_USERINFO_KEY, userInfo);
	}
	
	protected  Object getSessionAttribute(HttpServletRequest request, String name) {  
	    if(null == request || StringUtils.isBlank(name)){
	    	return null;
	    }  
	    HttpSession session = request.getSession(false);  
	    return (session != null ? session.getAttribute(name) : null);  
	}
	
	protected String getMessage(HttpServletRequest request,String key) {
        String msg = RequestContextUtils.getWebApplicationContext(request).getMessage(key, null, RequestContextUtils.getLocale(request));
        return HtmlUtils.htmlEscape(msg);
	}
	
	protected void renderJavaScript(HttpServletResponse response,String text) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\">").append(text).append("</script>");
		render(response,sb.toString(), "text/html;charset=".concat("UTF-8"));
	}

	protected void renderJson(HttpServletResponse response,String text) {
		LOG.info(text);
		render(response,text, "application/json;charset=".concat("UTF-8"));
	}

	protected void renderText(HttpServletResponse response,String text) {
		render(response,text, "text/plain;charset=".concat("UTF-8"));
	}

	protected void renderXml(HttpServletResponse response,String text) {
		render(response,text, "text/xml;charset=".concat("UTF-8"));
	}
	
	protected void render(HttpServletResponse response,String text, String contentType) {
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
		}
	}

}
