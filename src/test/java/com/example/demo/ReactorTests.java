package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.reactor.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
/*
 * Reactive Streams
 * 1. Asynchronous
 * 2. Non blocking
 * 3. Handle backpressure
 * Publisher(cold type : unless someone subscribes to them nothing happens) <- (subscribe)Subscriber
 * Subscription is created
 * Publisher will call onSubscribe with the subscription from the Subscriber
 * Subscription can be used to handle the back pressure <- (request N) Subscriber
 * Publisher -> (onNext) Subscriber
 * until:
 * 1. Publisher sends all the objects requested 
 * 2. Publisher sends all the objects it has. (onComplete) subscriber and subscription will be cancelled
 * 3. There is an error. (onError) -> subscriber and subscription will be cancelled
 */
public class ReactorTests {
	@Test
	public void monoTest() {
		Mono<String> monoString= Mono.just("Anuj Arora").log(); // it can handle only one object, also mono is a publisher
		//2. subscriber need to call subscribe method of publisher.
		monoString.subscribe(System.out :: println);
		
	}
	@Test
	public void monoErrorTest() {
		Mono<?> monoString= Mono.just("Anuj Arora")
				.then(Mono.error(new RuntimeException("Forceful exception")))
				.log(); // it can handle only one object, also mono is a publisher
		//2. subscriber need to call subscribe method of publisher.
		monoString.subscribe(System.out :: println, (e)-> System.out.println(e.getMessage()));
		
	}
	@Test
	public void fluxTest() {
		Flux<String> fluxString= Flux.just("List","of", "Strings")
				.concatWithValues("Added","More")
				.log();
		fluxString.subscribe(System.out :: println);
	}
	@Test
	public void fluxErrorTest() {
		Flux<String> fluxString= Flux.just("List","of", "Strings")
				.concatWithValues("Added","More")
				.concatWith(Flux.error(new RuntimeException("Exception occured in flux")))
				.concatWithValues("After error")
				.log();
		fluxString.subscribe(System.out :: println, e -> System.out.println(e.getMessage()));
		// prints 5 elements but not "After error"
	}

}
