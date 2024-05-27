package org.botos.sec14;

import reactor.core.publisher.Mono;

public class ContextProducer {

	public Mono<String> getWelcomeMessage() {
		return Mono.deferContextual(contextView -> {
			if (contextView.hasKey("user")) {
				return Mono.just("Welcome %s!".formatted(contextView.get("user")
				                                                    .toString()));
			}
			return Mono.error(new RuntimeException("unauthenticated"));
		});
	}
}
