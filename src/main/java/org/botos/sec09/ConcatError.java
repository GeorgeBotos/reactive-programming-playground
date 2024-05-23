package org.botos.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class ConcatError {

	public static void main(String[] args) {

		demo2();

		sleep(3);
	}

	private static void demo1() {
		producer1().concatWith(producer3())
		           .concatWith(producer2())
		           .subscribe(subscriber());
	}

	private static void demo2() {
		Flux.concatDelayError(producer1(), producer3(), producer2())
		    .subscribe(subscriber());
	}

	private static Flux<Integer> producer1() {
		return Flux.just(1, 2, 3)
		           .doOnSubscribe(item -> log.info("subscribing to producer 1"))
		           .delayElements(Duration.ofMillis(10));
	}

	private static Flux<Integer> producer2() {
		return Flux.just(51, 52, 53)
		           .doOnSubscribe(item -> log.info("subscribing to producer 2"))
		           .delayElements(Duration.ofMillis(10));
	}

	private static Flux<Integer> producer3() {
		return Flux.error(new RuntimeException("oops"));
	}
}
