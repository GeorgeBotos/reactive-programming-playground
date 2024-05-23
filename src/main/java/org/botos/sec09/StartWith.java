package org.botos.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class StartWith {

	public static void main(String[] args) {

		demo4();

		sleep(3);

	}

	private static void demo4() {
		producer1().startWith(producer2())
		           .startWith(1000)
		           .subscribe(subscriber());
	}

	private static void demo3() {
		producer1().startWith(producer2())
		           .subscribe(subscriber());
	}

	private static void demo2() {
		producer1().startWith(List.of(-2, -1, 0))
		           .subscribe(subscriber());
	}

	private static void demo1() {
		producer1().startWith(-1, 0)
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
