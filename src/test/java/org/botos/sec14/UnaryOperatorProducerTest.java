package org.botos.sec14;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

class UnaryOperatorProducerTest {

	private final UnaryOperatorProducer service = new UnaryOperatorProducer();

	@Test
	void producerTest_1() {
		var publisher = TestPublisher.<String>create();
		var flux = publisher.flux();

		StepVerifier.create(flux.transform(service.process()))
		            .then(() -> publisher.emit("hi", "hello"))
		            .expectNext("HI:2")
		            .expectNext("HELLO:5")
		            .verifyComplete();
	}

	@Test
	void producerTest_2() {
		var publisher = TestPublisher.<String>create();
		var flux = publisher.flux();

		StepVerifier.create(flux.transform(service.process()))
		            .then(() -> publisher.emit("a", "b", "c"))
		            .verifyComplete();
	}
}