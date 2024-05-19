package org.botos.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

@Slf4j
public class SwitchIfEmpty {

	public static void main(String[] args) {

		Flux.range(1, 10)
		    .filter(value -> value > 11)
		    .switchIfEmpty(fallback())
		    .subscribe(subscriber());
	}

	private static Flux<Integer> fallback() {
		return Flux.range(100, 3);
	}
}
