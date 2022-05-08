package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringbootApplication.class, args);
	}

	/*@Bean
	//@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate rt = new RestTemplate();

		rt.setRequestFactory( new HttpComponentsClientHttpRequestFactory());
		return rt;
	}*/

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
