package org.botos.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

import java.time.Duration;

import static org.botos.common.Util.subscriber;

@Slf4j
public class Lec06MulticastDirectAllOrNothing {

	public static void main(String[] args) {
		demo1();
	}

	private static void demo1() {
		System.setProperty("reactor.bufferSize.small", "16");
		var sink = Sinks.many()
		                .multicast()
		                .directAllOrNothing();
		var flux = sink.asFlux();
		flux.subscribe(subscriber("sam"));
		flux.delayElements(Duration.ofMillis(200))
		    .subscribe(subscriber("mike"));
		for (int i = 1; i <= 100; i++) {
			var result = sink.tryEmitNext(i);
			log.info("item: {}, result: {}", i, result);
		}
	}
}
