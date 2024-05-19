package org.botos.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.botos.common.Util.subscriber;

@Slf4j
public class DefaultIfEmpty {

	public static void main(String[] args) {

//		defaultValue();
//		defaultValueNotUsed();
		emptyFromFlux();
	}

	private static void defaultValue() {
		Mono.empty()
		    .defaultIfEmpty("fallback value")
		    .subscribe(subscriber());
	}

	private static void defaultValueNotUsed() {
		Mono.just("actual value")
		    .defaultIfEmpty("fallback value")
		    .subscribe(subscriber());
	}

	private static void emptyFromFlux() {
		Flux.range(1, 12)
		    .filter(value -> value > 11)
		    .defaultIfEmpty(-2)
		    .subscribe(subscriber());
	}
}
