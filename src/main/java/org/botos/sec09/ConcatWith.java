package org.botos.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class ConcatWith {

	public static void main(String[] args) {

//		demo1();
//		demo2();
		demo3();

		sleep(3);
	}

	private static void demo1() {
		producer1().concatWithValues(-1, 0)
		           .subscribe(subscriber());
	}

	private static void demo2() {
		producer1().concatWith(producer2())
		           .subscribe(subscriber());
	}

	private static void demo3() {
		Flux.concat(producer1(), producer2())
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
}
