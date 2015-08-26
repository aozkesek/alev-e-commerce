package com.netas.avaya;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.support.WebApplicationContextUtils;

public class WMLServlet implements Servlet {

	ServletConfig servletConfig;
	
	@Override
	public void destroy() {
		
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public String getServletInfo() {
		return "WML-Servlet";
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		this.servletConfig = servletConfig; 
		
		IppsWebApplicationInitializer.setIppsWebApplicationContext(
				WebApplicationContextUtils.getRequiredWebApplicationContext(
						servletConfig.getServletContext()));
		
		
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		HttpServletRequest hreq = (HttpServletRequest) request;
		String servletName = hreq.getServletPath();
		
		Servlet servlet = IppsServletFactory.getIppsServlet(servletName);
		if (servlet != null)
			servlet.service(request, response);
		else
			response.getOutputStream().println(servletName + " not found!");

	}

}
