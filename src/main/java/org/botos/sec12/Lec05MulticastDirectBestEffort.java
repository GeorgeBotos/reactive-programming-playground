package org.botos.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

import java.time.Duration;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class Lec05MulticastDirectBestEffort {

	public static void main(String[] args) {
		demo3();
	}

	private static void demo1() {
		System.setProperty("reactor.bufferSize.small", "16");

		var sink = Sinks.many()
		                .multicast()
		                .onBackpressureBuffer();

		var flux = sink.asFlux();

		flux.subscribe(subscriber("sam"));
		flux.subscribe(subscriber("jake"));
//		flux.delayElements(Duration.ofMillis(200))
//		    .subscribe(subscriber("mike"));


		for (int i = 1; i <= 100; i++) {
			var result = sink.tryEmitNext(i);
			log.info("item: {}, result: {}", i, result);
		}
	}

	private static void demo2() {
		System.setProperty("reactor.bufferSize.small", "16");

		var sink = Sinks.many()
		                .multicast()
		                .directBestEffort();

		var flux = sink.asFlux();

		flux.subscribe(subscriber("sam"));
		flux.subscribe(subscriber("jake"));
//		flux.delayElements(Duration.ofMillis(200))
//		    .subscribe(subscriber("mike"));


		for (int i = 1; i <= 100; i++) {
			var result = sink.tryEmitNext(i);
			log.info("item: {}, result: {}", i, result);
		}
	}

	private static void demo3() {
		System.setProperty("reactor.bufferSize.small", "16");

		var sink = Sinks.many()
		                .multicast()
		                .directBestEffort();

		var flux = sink.asFlux();

		flux.subscribe(subscriber("sam"));
		flux.onBackpressureBuffer()
		    .delayElements(Duration.ofMillis(200))
		    .subscribe(subscriber("jake"));
//		flux.delayElements(Duration.ofMillis(200))
//		    .subscribe(subscriber("mike"));


		for (int i = 1; i <= 100; i++) {
			var result = sink.tryEmitNext(i);
			log.info("item: {}, result: {}", i, result);
		}
		sleep(6);
	}
}
