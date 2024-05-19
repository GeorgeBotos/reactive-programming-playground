package org.botos.sec06.assignment;

import reactor.core.publisher.Flux;

public interface OrderProcess {

	void consume(Order order);

	Flux<String> stream();
}
