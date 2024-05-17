package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.botos.common.Util.subscriber;

@Slf4j
public class MonoFromSupplier {

	public static void main(String[] args) {

		var ints = List.of(1, 2, 3);
		Mono.just(sum(ints))
		    .subscribe(subscriber());

		Mono.just(sum(ints)); // Eager execution
		Mono.fromSupplier(() -> sum(ints)); // Lazy execution

		Mono.fromSupplier(() -> sum(ints))
		    .subscribe(subscriber());
	}

	private static int sum(List<Integer> ints) {
		log.info("finding the sum of {}", ints);
		return ints.stream()
		           .mapToInt(number -> number)
		           .sum();
	}
}
