package org.botos.sec03;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class FluxInternal {

	public static void main(String[] args) {

		Flux.interval(Duration.ofMillis(500))
		    .map(index -> faker().name()
		                         .firstName())
		    .subscribe(subscriber());

		sleep(5);
	}
}
