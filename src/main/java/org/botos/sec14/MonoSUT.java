package org.botos.sec14;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoSUT {

	public Mono<String> getProduct(int productId) {
		return Mono.fromSupplier(() -> "product-" + productId)
		           .doFirst(() -> log.info("invoked"));
	}

	public Mono<String> getUsername(int userId) {
		return switch (userId) {
			case 1 -> Mono.just("sam");
			case 2 -> Mono.empty();
			default -> Mono.error(new RuntimeException("invalid input"));
		};
	}
}
