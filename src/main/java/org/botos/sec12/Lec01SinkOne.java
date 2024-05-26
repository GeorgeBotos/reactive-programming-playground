package org.botos.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

import static org.botos.common.Util.subscriber;

@Slf4j
public class Lec01SinkOne {

	public static void main(String[] args) {

		demo4();
	}

	private static void demo1() {
		var sink = Sinks.one();
		var mono = sink.asMono();
		mono.subscribe(subscriber());
		sink.tryEmitValue("hi");
	}

	private static void demo2() {
		var sink = Sinks.one();
		var mono = sink.asMono();
		mono.subscribe(subscriber());
		sink.tryEmitError(new RuntimeException("oops"));
	}

	private static void demo3() {
		var sink = Sinks.one();
		var mono = sink.asMono();
		mono.subscribe(subscriber("sam"));
		mono.subscribe(subscriber("mike"));
		sink.tryEmitValue("hi");
	}

	private static void demo4() {
		var sink = Sinks.one();
		var mono = sink.asMono();
		mono.subscribe(subscriber("sam"));

		sink.emitValue("hi", ((signalType, emitResult) -> {
			log.info("hi error handler");
			log.info(signalType.name());
			log.info(emitResult.name());
			return false;
		}));
		sink.emitValue("hello", ((signalType, emitResult) -> {
			log.info("hello error handler");
			log.info(signalType.name());
			log.info(emitResult.name());
			return false;  // false -> do not retry, true -> retry
		}));
	}
}
