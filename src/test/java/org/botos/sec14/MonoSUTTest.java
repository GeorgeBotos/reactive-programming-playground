package org.botos.sec14;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonoSUTTest {

	MonoSUT service = new MonoSUT();

	@Test
	public void productTest() {
		StepVerifier.create(service.getProduct(1))
		            .expectNext("product-1")
		            .expectComplete()
		            .verify();
	}

	@Test
	public void emptyTest() {
		StepVerifier.create(service.getUsername(2))
		            .expectComplete()
		            .verify();
	}

	@Test
	public void errorTest1() {
		StepVerifier.create(service.getUsername(3))
		            .expectError()
		            .verify();
	}

	@Test
	public void errorTest1_alt() {
		StepVerifier.create(service.getUsername(3))
		            .verifyError();
	}

	@Test
	public void errorTest2() {
		StepVerifier.create(service.getUsername(3))
		            .expectError(RuntimeException.class)
		            .verify();
	}

	@Test
	public void errorTest2_alt() {
		StepVerifier.create(service.getUsername(3))
		            .verifyError(RuntimeException.class);
	}

	@Test
	public void errorTest3() {
		StepVerifier.create(service.getUsername(3))
		            .expectErrorMessage("invalid input")
		            .verify();
	}

	@Test
	public void errorTest3_alt() {
		StepVerifier.create(service.getUsername(3))
		            .verifyErrorMessage("invalid input");
	}

	@Test
	public void errorTest4() {
		StepVerifier.create(service.getUsername(3))
		            .consumeErrorWith(throwable -> {
			            assertEquals(RuntimeException.class, throwable.getClass());
			            assertEquals("invalid input", throwable.getMessage());
		            })
		            .verify();
	}

	@Test
	public void errorTest4_alt() {
		StepVerifier.create(service.getUsername(3))
		            .verifyErrorSatisfies(throwable -> {
			            assertEquals(RuntimeException.class, throwable.getClass());
			            assertEquals("invalid input", throwable.getMessage());
		            });
	}
}