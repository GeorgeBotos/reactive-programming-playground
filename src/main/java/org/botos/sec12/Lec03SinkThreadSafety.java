package org.botos.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import static org.botos.common.Util.sleep;

@Slf4j
public class Lec03SinkThreadSafety {

	public static void main(String[] args) {
		demo2();
	}

	private static void demo1() {
		var sink = Sinks.many()
		                .unicast()
		                .onBackpressureBuffer();
		var flux = sink.asFlux();

		var list = new ArrayList<>();
		flux.subscribe(list::add);

		for (int i = 0; i < 1_000; i++) {
			var j = i;
			CompletableFuture.runAsync(() -> {
				sink.tryEmitNext(j);
			});
		}
		sleep(2);
		log.info("list size: {}", list.size());
	}

	private static void demo2() {
		var sink = Sinks.many()
		                .unicast()
		                .onBackpressureBuffer();
		var flux = sink.asFlux();

		var list = new ArrayList<>();
		flux.subscribe(list::add);

		for (int i = 0; i < 1_000; i++) {
			var j = i;
			CompletableFuture.runAsync(() -> {
				sink.emitNext(j, (signalType, emitResult) -> {
					return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult);
				});
			});
		}
		sleep(2);
		log.info("list size: {}", list.size());
	}
}
