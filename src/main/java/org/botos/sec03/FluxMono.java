package org.botos.sec03;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.botos.common.Util.subscriber;

public class FluxMono {

	/*
		To convert a FLux <--> Mono
	 */
	public static void main(String[] args) {

		var flux = Flux.range(1, 10);
		flux.next()
		    .subscribe(subscriber());
		Mono.from(flux)
		    .subscribe(subscriber());
	}

	private static void monoToFlux() {
		var mono = getUserName(1);
		save(Flux.from(mono));
	}

	private static Mono<String> getUserName(int userId) {
		return switch (userId) {
			case 1 -> Mono.just("sam");
			case 2 -> Mono.empty();
			default -> Mono.error(new RuntimeException("invalid input"));
		};
	}

	private static void save(Flux<String> flux) {
		flux.subscribe(subscriber());
	}
}
