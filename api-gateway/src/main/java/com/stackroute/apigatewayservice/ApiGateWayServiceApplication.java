package com.stackroute.apigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
//this forwards local calls to the appropriate service
@EnableZuulProxy
@SpringBootApplication
public class ApiGateWayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGateWayServiceApplication.class, args);
	}

}
