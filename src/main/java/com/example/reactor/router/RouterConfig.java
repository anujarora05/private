package com.example.reactor.router;

import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.reactor.handler.CustomerHandler;

@Configuration
public class RouterConfig {
	
	@Autowired
	private CustomerHandler handler;
	
	@RouterOperations({ @RouterOperation(path = "/router/customers", beanClass = CustomerHandler.class, beanMethod = "loadCustomers"),
	      @RouterOperation(path = "/router/customers/stream/", beanClass = CustomerHandler.class, beanMethod = "loadCustomerStream"),
	      @RouterOperation(path = "/router/customers/{input}", beanClass = CustomerHandler.class, beanMethod = "findCustomer"),
	      @RouterOperation(path = "/router/customer/save", beanClass = CustomerHandler.class, beanMethod = "saveCustomer") })
	@Bean
	public RouterFunction<ServerResponse> routerFunction(){
		return RouterFunctions
				.route()
				.GET("/router/customers",handler :: loadCustomers)
				.GET("/router/customers/stream/", handler :: loadCustomerStream)
				.GET("/router/customers/{input}", handler :: findCustomer)
				.POST("/router/customer/save", handler :: saveCustomer)
				.build();
		
	}

}
