package org.botos.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.fluxLogger;

public class Emirates {

	private static final String AIRLINE = "Emirates";

	public static Flux<Flight> getFlights() {
		return Flux.range(1, faker().random().nextInt(2, 10))
		           .delayElements(Duration.ofMillis(faker().random().nextInt(200, 1000)))
		           .map(integer -> new Flight(AIRLINE, faker().random().nextInt(300, 1000)))
		           .transform(fluxLogger(AIRLINE));
	}
}
