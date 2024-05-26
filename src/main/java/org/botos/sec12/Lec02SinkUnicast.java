package org.botos.sec12;

import reactor.core.publisher.Sinks;

import static org.botos.common.Util.subscriber;

public class Lec02SinkUnicast {

	public static void main(String[] args) {
		demo2();
	}

	private static void demo1() {

		var sink = Sinks.many()
		                .unicast()
		                .onBackpressureBuffer();

		var flux = sink.asFlux();

		sink.tryEmitNext("hi");
		sink.tryEmitNext("how are you");
		sink.tryEmitNext("?");

		flux.subscribe(subscriber());
	}

	private static void demo2() {

		var sink = Sinks.many()
		                .unicast()
		                .onBackpressureBuffer();

		var flux = sink.asFlux();

		sink.tryEmitNext("hi");
		sink.tryEmitNext("how are you");
		sink.tryEmitNext("?");

		flux.subscribe(subscriber("sam"));
		flux.subscribe(subscriber("mike"));
	}
}
