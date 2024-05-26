package org.botos.sec12;

import reactor.core.publisher.Sinks;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Lec04Multicast {

	public static void main(String[] args) {
		demo2();
	}

	private static void demo1() {

		var sink = Sinks.many()
		                .multicast()
		                .onBackpressureBuffer();

		var flux = sink.asFlux();

		flux.subscribe(subscriber("sam"));
		flux.subscribe(subscriber("mike"));

		sink.tryEmitNext("hi");
		sink.tryEmitNext("how are you");
		sink.tryEmitNext("?");

		sleep(2);

		flux.subscribe(subscriber("jake"));
		sink.tryEmitNext("new message");
	}

	private static void demo2() {

		var sink = Sinks.many()
		                .multicast()
		                .onBackpressureBuffer();

		var flux = sink.asFlux();

		sink.tryEmitNext("hi");
		sink.tryEmitNext("how are you");
		sink.tryEmitNext("?");

		flux.subscribe(subscriber("sam"));
		flux.subscribe(subscriber("mike"));
		flux.subscribe(subscriber("jake"));

		sleep(2);

		sink.tryEmitNext("new message");
	}
}
