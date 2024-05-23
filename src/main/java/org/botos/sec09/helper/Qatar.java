package org.botos.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.fluxLogger;

public class Qatar {

	private static final String AIRLINE = "Qatar";

	public static Flux<Flight> getFlights() {
		return Flux.range(1, faker().random().nextInt(3, 5))
		           .delayElements(Duration.ofMillis(faker().random().nextInt(200, 800)))
		           .map(integer -> new Flight(AIRLINE, faker().random().nextInt(400, 1100)))
		           .transform(fluxLogger(AIRLINE));
	}
}
