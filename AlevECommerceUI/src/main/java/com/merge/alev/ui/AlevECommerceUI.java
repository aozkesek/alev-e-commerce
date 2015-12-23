package com.merge.alev.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AlevECommerceUI {
	
	public static ApplicationContext AppCtx;
	
	public static void main(String[] args) {
		AppCtx = SpringApplication.run(AlevECommerceUI.class, args);
	}

		
}
