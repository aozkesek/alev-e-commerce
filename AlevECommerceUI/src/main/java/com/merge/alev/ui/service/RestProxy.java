package com.merge.alev.ui.service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestProxy {
	
	public static <T, M> T postForObject(String e, M m, Class<T> tClass) {
		RestTemplate restTemp = new RestTemplate();
		return (T) restTemp.postForObject(e, m, tClass);
	}
	
}
