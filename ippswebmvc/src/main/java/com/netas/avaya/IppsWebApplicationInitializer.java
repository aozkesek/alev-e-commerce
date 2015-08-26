package com.netas.avaya;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

public class IppsWebApplicationInitializer implements WebApplicationInitializer {

	protected static ServletContext ippsServletContext;
	protected static WebApplicationContext ippsWebApplicationContext;

	public static WebApplicationContext getIppsWebApplicationContext() {
		return ippsWebApplicationContext;
	}

	public static void setIppsWebApplicationContext(WebApplicationContext ippsWebApplicationContext) {
		IppsWebApplicationInitializer.ippsWebApplicationContext = ippsWebApplicationContext;
	}

	public static ServletContext getIppsServletContext() {
		return ippsServletContext;
	}

	public static void setIppsServletContext(ServletContext servletContext) {
		IppsWebApplicationInitializer.ippsServletContext = servletContext;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		setIppsServletContext(servletContext);
	}

}
