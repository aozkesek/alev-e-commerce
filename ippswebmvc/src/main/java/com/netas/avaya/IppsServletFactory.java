package com.netas.avaya;

import javax.servlet.Servlet;

public class IppsServletFactory {

	public static Servlet getIppsServlet(String name) {
		
		if (name == null)
			return null;
		else if (IppsWebApplicationInitializer.getIppsWebApplicationContext().containsBean(name))
			return (Servlet)IppsWebApplicationInitializer.getIppsWebApplicationContext().getBean(name);
		
		return null;
	}
}
