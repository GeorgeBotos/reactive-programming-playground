package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoSubscribe {

	public static void main(String[] args) {
		var mono = Mono.just(1)
		               .map(integer -> integer / 0);

		mono.subscribe(value -> log.info("received: {}", value),
		               error -> log.error("error", error),
		               () -> log.info("completed"),
		               subscription -> subscription.request(1));
	}
}
