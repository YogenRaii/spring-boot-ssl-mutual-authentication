package com.codependent.mutualauth;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLSocketFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SecureClientApplication {

	@PostConstruct
	public void initSsl(){
		System.setProperty("javax.net.ssl.keyStore", Thread.currentThread().getContextClassLoader().getResource("client-keystore.jks").getPath());
		System.setProperty("javax.net.ssl.keyStorePassword", "secret");
		System.setProperty("javax.net.ssl.trustStore", Thread.currentThread().getContextClassLoader().getResource("client-truststore.jks").getPath());
		System.setProperty("javax.net.ssl.trustStorePassword", "secret");
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
			(hostname,sslSession) -> {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			});
	}
	
	@Bean
	public RestTemplate template() throws Exception{

		RestTemplate template = new RestTemplate();
		return template;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SecureClientApplication.class, args);
	}
}
