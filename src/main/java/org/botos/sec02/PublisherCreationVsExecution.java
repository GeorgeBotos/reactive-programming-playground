package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

/*
	Creating Publisher is a lightweight operation.
	Executing time-consuming business logic can be delayed.
 */
@Slf4j
public class PublisherCreationVsExecution {

	public static void main(String[] args) {
		getName(); // Publisher is created, but not executed => only "entered method" is printed.
		getName().subscribe(subscriber()); // Now the Publisher sends data, hence inside logic is executed.
	}

	private static Mono<String> getName() {
		log.info("entered the method");
		return Mono.fromSupplier(() -> {
			log.info("generating name");
			sleep(3);
			return faker().name().firstName();
		});
	}
}
