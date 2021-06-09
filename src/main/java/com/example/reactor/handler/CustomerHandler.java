package com.example.reactor.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.reactor.dao.CustomerDao;
import com.example.reactor.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {
	@Autowired
	private CustomerDao dao;
	
	public Mono<ServerResponse> loadCustomers(ServerRequest request){
		Flux<Customer> customerList= dao.getCustomersRouter();
		return ServerResponse.ok().body(customerList, Customer.class);
	}
	public Mono<ServerResponse> loadCustomerStream(ServerRequest request){
		Flux<Customer> customerList= dao.getCustomersStream();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customerList,Customer.class);
	}
	public Mono<ServerResponse> findCustomer(ServerRequest request){
		int custId=Integer.valueOf(request.pathVariable("input"));
		Mono<Customer> cust=dao.getCustomersStream().filter(c-> c.getId()==custId).next();
		//dao.getCustomersStream().filter(c-> c.getId()==custId).take(1).single();
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
				.body(cust,Customer.class);
	}
	public Mono<ServerResponse> saveCustomer(ServerRequest request){
		// should add header content type as application/json
		Mono<Customer> custMono= request.bodyToMono(Customer.class);
		Mono<String> saveResponse= custMono.map(dto -> dto.getId() +":"+dto.getName());
		return ServerResponse.ok().body(saveResponse,String.class);	
	}

}
