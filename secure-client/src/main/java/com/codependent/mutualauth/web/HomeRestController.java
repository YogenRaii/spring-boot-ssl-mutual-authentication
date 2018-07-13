package com.codependent.mutualauth.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class HomeRestController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/")
	public String home() throws RestClientException, URISyntaxException{
		return restTemplate.getForObject(new URI("https://localhost:8443"), String.class);
	}

	@GetMapping("/post")
	public String post() throws RestClientException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String data = "Test payload";

		HttpEntity<String> request = new HttpEntity<>(data, headers);

		return restTemplate.postForEntity("https://localhost:8443", request , String.class ).getBody();
	}
}
