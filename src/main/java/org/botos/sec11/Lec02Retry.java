package org.botos.sec11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class Lec02Retry {

	public static void main(String[] args) {

		demo3();

		sleep(4);
	}

	private static void demo1() {
		getCountryName().retry(2)
		                .subscribe(subscriber());
	}

	private static void demo2() {
		getCountryName().retryWhen(Retry.max(2))
		                .subscribe(subscriber());
	}

	private static void demo3() {
		getCountryName().retryWhen(Retry.fixedDelay(2, Duration.ofSeconds(1))
		                                .doBeforeRetry(retrySignal -> log.info("retrying, signal: {}", retrySignal.toString()))
		                                .filter(throwable -> RuntimeException.class.equals(throwable.getClass()))
		                                .onRetryExhaustedThrow((spec, signal) -> signal.failure()))
		                .subscribe(subscriber());
	}

	private static Mono<String> getCountryName() {
		var atomicInt = new AtomicInteger(0);
		return Mono.fromSupplier(() -> {
			           if (atomicInt.incrementAndGet() < 3) {
				           throw new RuntimeException("oops");
			           }
			           return faker().country()
			                         .name();
		           })
		           .doOnError(err -> log.error("ERROR: {}", err.getMessage(), err))
		           .doOnSubscribe(subscription -> log.info("subscribing"));
	}

}
