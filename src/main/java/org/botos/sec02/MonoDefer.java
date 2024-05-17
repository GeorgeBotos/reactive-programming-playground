package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class MonoDefer {

	public static void main(String[] args) {
//		createPublisher(); // Creating the publisher is expensive (time-consuming)
//		createPublisher().subscribe(subscriber());

		Mono.defer(MonoDefer::createPublisher); // The actual Publisher creation is deferred, until it's needed.
		Mono.defer(MonoDefer::createPublisher)
		    .subscribe(subscriber()); // As the stream is consumed by the Subscriber, the creation is not deferred here.
	}

	private static Mono<Integer> createPublisher() {
		log.info("creating publisher");
		var ints = List.of(1, 2, 3);
		sleep(3);
		return Mono.fromSupplier(() -> sum(ints));
	}

	// time-consuming business logic
	private static int sum(List<Integer> ints) {
		log.info("finding the sum of {}", ints);
		sleep(3);
		return ints.stream()
		           .mapToInt(number -> number)
		           .sum();
	}
}
