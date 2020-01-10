package com.puresound.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class QueryService {
	@Autowired
	private RestTemplate restTemplate;
	
	public void queryOxfordDict() {
		String result = restTemplate.getForObject("http://www.google.com", String.class);
		System.out.println(result);
	}
}
