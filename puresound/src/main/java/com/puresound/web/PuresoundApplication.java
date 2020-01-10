package com.puresound.web;

import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PuresoundApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(PuresoundApplication.class, args);
		TestData d = new TestData();
		QueryService queryService = ctx.getBean(QueryService.class);
		queryService.queryOxfordDict();
	}
	
	@Bean
	public RestTemplate restTemplate() throws GeneralSecurityException, NoSuchAlgorithmException, KeyStoreException {
		TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
		SSLContext sslContext = SSLContextBuilder.create()
								.loadTrustMaterial(null, acceptingTrustStrategy)
								.build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
		
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
																.register("https", sslsf)
																.register("http", new PlainConnectionSocketFactory())
																.build();
																
		
		
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(20);
		connManager.setDefaultMaxPerRoute(5);
		
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000)
															.setConnectTimeout(5000)
															.setConnectionRequestTimeout(3000)
															.build();
		CloseableHttpClient httpClient = HttpClientBuilder.create()
									.setDefaultRequestConfig(requestConfig)
									.setConnectionManager(connManager)
									.build();
		
//		ClosableHttpClient httpClient = HttpClients.custom()
//											.setDefaultRequestConfig(RequestConfig.custom().setStaleConnectionCheckEnabled(true))
//											.setConnectionManager(connManager).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		requestFactory.setConnectionRequestTimeout(3000); //5s
		requestFactory.setConnectTimeout(60000);  //60s
		System.out.println(connManager.getTotalStats().getLeased());
		
		return new RestTemplate(requestFactory);
	}

}
