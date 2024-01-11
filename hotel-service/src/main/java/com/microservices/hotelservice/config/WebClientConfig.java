package com.microservices.hotelservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.microservices.hotelservice.client.CustomerClient;

@Configuration
public class WebClientConfig {
	
	@Autowired
	private LoadBalancedExchangeFilterFunction filterExchange;
	
	@Bean
	public WebClient customerWebClient(){
		return WebClient.builder()
				.baseUrl("http://customer-service")
				.filter(filterExchange)
				.build();
	}
	@Bean
	public CustomerClient customerClient() {
		HttpServiceProxyFactory httpServiceProxyFactory 
		= HttpServiceProxyFactory.builder(WebClientAdapter.forClient(customerWebClient()))
		.build();
		
		 return httpServiceProxyFactory.createClient(CustomerClient.class);
	}
}
