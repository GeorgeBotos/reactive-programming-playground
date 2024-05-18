package org.botos.sec03;

import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;
import static org.botos.common.Util.faker;

public class FluxRange {

	public static void main(String[] args) {

		Flux.range(1, 10)
		    .subscribe(subscriber());

		// Assignment - generate 10 random first names

		Flux.range(1, 10)
		    .map(index -> index + ": " + faker().name()
		                                        .firstName())
		    .subscribe(subscriber());
	}
}
