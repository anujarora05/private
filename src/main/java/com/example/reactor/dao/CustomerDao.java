package com.example.reactor.dao;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.example.reactor.dto.Customer;

import reactor.core.publisher.Flux;

@Component
public class CustomerDao {

	private static void sleepExecution(int i) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//this will act as a publisher and browser will be subscriber
	public List<Customer> getCustomers() {
		return IntStream.rangeClosed(1, 10)
				.peek(i -> System.out.println("processing count : " + i))
				.peek(CustomerDao :: sleepExecution)
				.mapToObj(i -> new Customer(i, "Customer" + i))
				.collect(Collectors.toList());

	}
	public Flux<Customer> getCustomersStream() {
		// TODO Auto-generated method stub
		return Flux.range(1, 10)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> System.out.println("processing count in stream flow: " + i))
				.map(i -> new Customer(i, "Customer" + i)).log();
	}
	
	public Flux<Customer> getCustomersRouter() {
		// TODO Auto-generated method stub
		return Flux.range(1, 50)
				.doOnNext(i -> System.out.println("processing count in stream flow: " + i))
				.map(i -> new Customer(i, "Customer" + i)).log();
	}

}
