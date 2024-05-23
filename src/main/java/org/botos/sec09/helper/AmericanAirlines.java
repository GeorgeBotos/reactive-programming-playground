package org.botos.sec09.helper;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.fluxLogger;

public class AmericanAirlines {

	private static final String AIRLINE = "American Airlines";

	public static Flux<Flight> getFlights() {
		return Flux.range(1, faker().random().nextInt(5, 10))
		           .delayElements(Duration.ofMillis(faker().random().nextInt(200, 900)))
		           .map(integer -> new Flight(AIRLINE, faker().random().nextInt(150, 900)))
		           .transform(fluxLogger(AIRLINE));
	}
}
