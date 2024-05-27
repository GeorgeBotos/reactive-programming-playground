package org.botos.sec14;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.faker;

public class FluxSUT {

	public Flux<Integer> getItems() {
		return Flux.just(1, 2, 3);
	}

	public Flux<Integer> getManyItems() {
		return Flux.range(1, 50);
	}

	public Flux<Integer> getRandomItems() {
		return Flux.range(1, 50)
		           .map(integer -> faker().random()
		                                  .nextInt(1, 100));
	}

	public Flux<Book> getBooks() {
		return Flux.range(1, 3)
		           .map(index -> Book.builder()
		                             .id(index)
		                             .author(faker().book()
		                                            .author())
		                             .title(faker().book()
		                                           .title())
		                             .build());
	}

	public Flux<Integer> getItemsDelayed() {
		return Flux.range(1, 5)
		           .delayElements(Duration.ofSeconds(10));
	}
}
