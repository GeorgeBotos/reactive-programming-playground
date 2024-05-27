package org.botos.sec12;

import reactor.core.publisher.Sinks;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Lec07Replay {

	public static void main(String[] args) {
		demo1();
	}

	private static void demo1() {
		var sink = Sinks.many()
		                .replay()
//		                .all();
                        .limit(1);
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
}
