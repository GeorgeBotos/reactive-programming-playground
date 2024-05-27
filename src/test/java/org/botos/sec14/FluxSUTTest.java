package org.botos.sec14;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FluxSUTTest {

	private FluxSUT service = new FluxSUT();

	@Test
	void fluxTest1() {
		StepVerifier.create(service.getItems(), 1)
		            .expectNext(1)
		            .thenCancel()
		            .verify();
	}

	@Test
	void fluxTest2() {
		StepVerifier.create(service.getItems())
		            .expectNext(1)
		            .expectNext(2)
		            .expectNext(3)
		            .expectComplete()
		            .verify();
	}

	@Test
	void fluxTest3() {
		StepVerifier.create(service.getItems())
		            .expectNext(1, 2, 3)
		            .expectComplete()
		            .verify();
	}

	@Test
	void fluxTest4() {
		StepVerifier.create(service.getManyItems())
		            .expectNext(1, 2, 3)
		            .expectNextCount(47)
		            .verifyComplete();
	}

	@Test
	void fluxTest5() {
		StepVerifier.create(service.getManyItems())
		            .expectNext(1, 2, 3)
		            .expectNextCount(22)
		            .expectNext(26, 27, 28)
		            .expectNextCount(22)
		            .verifyComplete();
	}

	@Test
	void fluxTest6() {
		StepVerifier.create(service.getRandomItems())
		            .expectNextMatches(integer -> integer >= 1 && integer <= 100)
		            .expectNextCount(49)
		            .expectComplete()
		            .verify();

	}

	@Test
	void fluxTest7() {
		StepVerifier.create(service.getRandomItems())
		            .thenConsumeWhile(integer -> integer >= 1 && integer <= 100)
		            .expectComplete()
		            .verify();

	}

	@Test
	void fluxTest8() {
		StepVerifier.create(service.getBooks())
		            .consumeNextWith(book -> assertEquals(1, book.id()))
		            .thenConsumeWhile(book -> Objects.nonNull(book.title()))
		            .verifyComplete();

	}

	@Test
	void fluxTest9() {
		StepVerifier.create(service.getBooks()
		                           .collectList())
		            .assertNext(books -> assertEquals(3, books.size()))
		            .verifyComplete();

	}

	@Test
	void fluxTest10() {
		StepVerifier.withVirtualTime(service::getItemsDelayed)
		            .thenAwait(Duration.ofSeconds(51))   // what we expect after 51 seconds
		            .expectNext(1, 2, 3, 4, 5)
		            .verifyComplete();

	}

	@Test
	void fluxTest11() {
		StepVerifier.withVirtualTime(service::getItemsDelayed)
		            .expectSubscription()   // This is implicitly checked if we expect a value back first => here we need to state it
		            .expectNoEvent(Duration.ofSeconds(9))
		            .thenAwait(Duration.ofSeconds(1))
		            .expectNext(1)
		            .thenAwait(Duration.ofSeconds(40))   // what we expect after 51 seconds
		            .expectNext(2, 3, 4, 5)
		            .verifyComplete();
	}

	@Test
	void fluxTest12() {
		var options = StepVerifierOptions.create()
		                                 .scenarioName("1 to 3 items test");
		StepVerifier.create(service.getItems(), options)
		            .expectNext(1)
		            .as("first item should be 1")
		            .expectNext(2, 3)
		            .as("then we expect 2 and 3")
		            .verifyComplete();

	}
}