package org.botos.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.subscriber;

@Slf4j
public class ErrorHandling {

	public static void main(String[] args) {

//		onErrorReturn();
//		onErrorResume();
//		onErrorResume();
		onErrorContinue();
	}

	private static void onErrorContinue() {
		Flux.range(1, 10)
		    .map(item -> item == 5 ? 5 / 0 : item)
		    .onErrorContinue((throwable, object) -> log.error("==> {}", object, throwable))
		    .subscribe(subscriber());
	}

	private static void onErrorComplete() {
//		Mono.error(new RuntimeException("oops"))
		Mono.just(1)
		    .onErrorComplete()
		    .subscribe(subscriber());
	}

	// When yu want to return a hard coded value or simple computation
	private static void onErrorReturn() {
		Flux.range(1, 10)
		    .map(item -> item == 5 ? 5 / 0 : item)
		    .onErrorReturn(IllegalArgumentException.class, -1)
		    .onErrorReturn(ArithmeticException.class, -2)
		    .onErrorReturn(-3)
		    .subscribe(subscriber());
	}

	private static void onErrorResume() {
		Flux.range(1, 10)
		    .map(item -> item == 5 ? 5 / 0 : item)
		    .onErrorResume(ArithmeticException.class, exception -> fallback1())
		    .onErrorResume(throwable -> fallback2())
		    .onErrorReturn(-5)
		    .subscribe(subscriber());
	}

	private static Mono<Integer> fallback1() {
		return Mono.fromSupplier(() -> faker().random()
		                                      .nextInt(10, 100));
	}

	private static Mono<Integer> fallback2() {
		return Mono.fromSupplier(() -> faker().random()
		                                      .nextInt(100, 1_000));
	}
}
