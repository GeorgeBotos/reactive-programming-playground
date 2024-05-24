package org.botos.sec11;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Lec01Repeat {

	public static void main(String[] args) {

		demo5();

		sleep(10);
	}

	private static void demo1() {
		getCountryName().repeat(2)
		                .subscribe(subscriber());
	}

	private static void demo2() {
		getCountryName().repeat()
		                .takeUntil("canada"::equalsIgnoreCase)
		                .subscribe(subscriber());
	}

	private static void demo3() {
		var atomiInt = new AtomicInteger(0);
		getCountryName().repeat(() -> atomiInt.getAndIncrement() < 3)
		                .subscribe(subscriber());
	}

	private static void demo4() {
		getCountryName().repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(2))
		                                        .take(2))
		                .subscribe(subscriber());
	}

	private static void demo5() {
		Flux.just(1, 2, 3)
		    .repeat(3)
		    .subscribe(subscriber());
	}

	private static Mono<String> getCountryName() {
		return Mono.fromSupplier(() -> faker().country()
		                                      .name());
	}
}
