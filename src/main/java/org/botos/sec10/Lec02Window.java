package org.botos.sec10;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.botos.common.Util.sleep;

public class Lec02Window {

	public static void main(String[] args) {

		eventStream().window(Duration.ofMillis(2200))
		             .flatMap(Lec02Window::processEvents)
		             .subscribe();
		sleep(60);
	}

	private static Flux<String> eventStream() {
		return Flux.interval(Duration.ofMillis(500))
		           .map(index -> "event-" + index);
	}

	private static Mono<Void> processEvents(Flux<String> flux) {
		return flux.doOnNext(event -> System.out.print("*"))
		           .doOnComplete(System.out::println)
		           .then();
	}
}
