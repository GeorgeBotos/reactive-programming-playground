package org.botos.sec13;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import static org.botos.common.Util.subscriber;

@Slf4j
public class Lec01Context {

	public static void main(String[] args) {
		getWelcomeMessage().contextWrite(Context.of("user", "sam"))
		                   .subscribe(subscriber());
	}

	private static Mono<String> getWelcomeMessage() {
		return Mono.deferContextual(contextView -> {
			log.info("{}", contextView);
			if (contextView.hasKey("user")) {
				return Mono.just("Welcome %s!".formatted(contextView.get("user")
				                                                    .toString()));
			}
			return Mono.error(new RuntimeException("unauthenticated"));
		});

	}
}
