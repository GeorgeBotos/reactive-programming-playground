package org.botos.sec14;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.Duration;

class SlowProducerTest {

	private final SlowProducer service = new SlowProducer();

	@Test
	void timeoutTest_1() {
		StepVerifier.create(service.getItemsSlow())
		            .expectNext(1, 2, 3, 4, 5)
		            .expectComplete()
		            .verify(Duration.ofMillis(1200));
	}
}