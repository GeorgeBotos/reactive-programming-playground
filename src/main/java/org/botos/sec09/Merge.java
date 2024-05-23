package org.botos.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.fluxLogger;
import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class Merge {

	public static void main(String[] args) {

		demo2();
		sleep(3);
	}

	private static void demo1() {
		Flux.merge(producer1(), producer2(), producer3())
		    .take(2)
		    .subscribe(subscriber());
	}

	private static void demo2() {
		producer1().mergeWith(producer2())
		           .mergeWith(producer3())
		           .subscribe(subscriber());
	}

	private static Flux<Integer> producer1() {
		return Flux.just(1, 2, 3)
		           .transform(fluxLogger("producer 1"))
		           .delayElements(Duration.ofMillis(10));
	}

	private static Flux<Integer> producer2() {
		return Flux.just(51, 52, 53)
		           .transform(fluxLogger("producer 2"))
		           .delayElements(Duration.ofMillis(10));
	}

	private static Flux<Integer> producer3() {
		return Flux.just(11, 12, 13)
		           .transform(fluxLogger("producer 3"))
		           .delayElements(Duration.ofMillis(10));
	}
}
