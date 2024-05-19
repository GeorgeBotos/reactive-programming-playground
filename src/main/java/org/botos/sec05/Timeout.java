package org.botos.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

/*
	timeout() will produce timeout error.
 */
@Slf4j
public class Timeout {

	public static void main(String[] args) {

		var mono = getProductName().timeout(Duration.ofSeconds(1), fallbackGetProductName());

		mono.timeout(Duration.ofMillis(200))
		    .subscribe(subscriber());

		sleep(5);

	}

	private static Mono<String> getProductName() {
		return Mono.fromSupplier(() -> "service-" + faker().commerce()
		                                                   .productName())
		           .delayElement(Duration.ofMillis(1_900));
	}

	private static Mono<String> fallbackGetProductName() {
		return Mono.fromSupplier(() -> "fallback-" + faker().commerce()
		                                                    .productName())
		           .delayElement(Duration.ofMillis(300))
		           .doFirst(() -> log.info("do first"));
	}
}
