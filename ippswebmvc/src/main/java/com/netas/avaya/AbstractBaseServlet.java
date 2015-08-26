package com.netas.avaya;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class AbstractBaseServlet implements Servlet {

	ServletConfig servletConfig;
	
	protected static String readAllText(String name) {
		InputStream fis = null;
		
		try {
			fis = IppsWebApplicationInitializer.getIppsServletContext().getResourceAsStream(name);
			byte[] bs = new byte[fis.available()];
			fis.read(bs);
			return new String(bs);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return "";
	}

	@Override
	public void destroy() {
	}

	@Override
	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	@Override
	public String getServletInfo() {
		return "BaseServlet";
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		servletConfig = config;
	}

	@Override
	public abstract void service(ServletRequest request, ServletResponse response) throws ServletException, IOException;
	
}
