package org.botos.sec14;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;
import reactor.util.context.Context;

class ContextProducerTest {

	private final ContextProducer service = new ContextProducer();

	@Test
	void contextTest_1() {
		var options = StepVerifierOptions.create()
		                                 .withInitialContext(Context.of("user", "sam"));
		StepVerifier.create(service.getWelcomeMessage(), options)
		            .expectNext("Welcome sam!")
		            .verifyComplete();
	}

	@Test
	public void contextTest_2() {
		var options = StepVerifierOptions.create()
		                                 .withInitialContext(Context.empty());
		StepVerifier.create(service.getWelcomeMessage(), options)
		            .expectErrorMessage("unauthenticated")
		            .verify();

	}
}