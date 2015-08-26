package com.netas.avaya.ipps;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

import com.netas.avaya.AbstractBaseServlet;

@Component("/subscribePhone.wml")
public class SubscribeServlet extends AbstractBaseServlet {

	@Override
	public String getServletInfo() {
		return "</subscribePhone.wml>servlet";
	}
	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String phoneIp = request.getParameter("ip");
		String phoneMac = request.getParameter("MAC");
		String phoneModel = request.getParameter("SetID");
		String phoneExtn = request.getParameter("Extn");
		
		System.out.println(String.format("phone > ip=%1$s, mac=%2$s, model=%3$s, extn=%4$s", phoneIp, phoneMac, phoneModel, phoneExtn));
		//phone > ip=47.168.249.6, mac=24:D9:21:3D:BA:13, model=9608D01A, extn=2003
		/*
		MAC == 24:D9:21:3D:BA:7
		Extn == 2001
		ip == 47.168.249.5
		SetID == 9608D01A		
		*/
		/*
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String keyName = paramNames.nextElement();
			String keyValue = request.getParameter(keyName);
			System.out.println(keyName +" == "+keyValue);
		}
		*/
	}

}
