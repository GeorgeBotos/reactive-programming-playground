package org.botos.sec14;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class SlowProducer {

	public Flux<Integer> getItemsSlow() {
		return Flux.range(1, 5)
		           .delayElements(Duration.ofMillis(200));
	}
}
