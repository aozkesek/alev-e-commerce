package com.netas.avaya.ipps;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.stereotype.Component;

import com.netas.avaya.AbstractBaseServlet;

@Component("/pushTopline.wml")
public class PushToplineServlet extends AbstractBaseServlet {

	@Override
	public String getServletInfo() {
		return "</push.wml>servlet";
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("\n<Response>\n");
		sb.append(request.getParameter("notification"));
		sb.append("\n</Response>");
		
		response.setContentType("application/xml");
		response.setContentLength(sb.toString().getBytes().length);
		response.getOutputStream().println(sb.toString());	
	
	}

}
