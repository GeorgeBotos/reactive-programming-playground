package org.botos.sec03;

import reactor.core.publisher.Flux;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.subscriber;

public class Log {

	public static void main(String[] args) {

		Flux.range(1, 5)
		    .log("range-map")
		    .map(index -> faker().name()
		                         .firstName())
		    .log("map-subscriber")
		    .subscribe(subscriber());
	}
}
