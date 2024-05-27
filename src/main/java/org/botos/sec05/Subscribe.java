package org.botos.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class Subscribe {

	public static void main(String[] args) {

		Flux.range(1, 10)
		    .doOnNext(item -> log.info("received: {}", item))
		    .doOnComplete(() -> log.info("completed"))
		    .doOnError(error -> log.error("error", error))
		    .subscribe();
	}
}
