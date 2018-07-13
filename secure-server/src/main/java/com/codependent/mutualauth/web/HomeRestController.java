package com.codependent.mutualauth.web;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

	@GetMapping("/")
	public String home(Principal principal){
		return String.format("Hello %s!", principal.getName());
	}

	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String consumeData(@RequestBody String data, Principal principal){
		return String.format("Hello %s! You sent: %s", principal.getName(), data);
	}
	
}
