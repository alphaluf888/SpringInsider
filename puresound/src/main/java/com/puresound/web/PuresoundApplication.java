package com.puresound.web;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PuresoundApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuresoundApplication.class, args);
		TestData d = new TestData();
		
	}
	
	@Bean
	public RestTemplate restTemplate() {
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(20);
		connManager.setDefaultMaxPerRoute(5);
		
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000)
															.setConnectTimeout(5000)
															.setConnectionRequestTimeout(3000)
															.build();
		HttpClient httpClient = HttpClientBuilder.create()
									.setDefaultRequestConfig(requestConfig)
									.setConnectionManager(connManager)
									.build();
		
		ClosableHttpClient httpClient = HttpClients.custom()
											.setDefaultRequestConfig(RequestConfig.custom().setStaleConnectionCheckEnabled(true))
											.setConnectionManager(connManager).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		requestFactory.setConnectionRequestTimeout(3000); //5s
		requestFactory.setConnectTimeout(60000);  //60s
		System.out.println(connManager.getTotalStats.getLeased());
		
		return new RestTemplate(requestFactory);
	}

}
