package org.botos.sec06;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

import static org.botos.common.Util.subscriber;

@Slf4j
public class ColdPublisher {

	public static void main(String[] args) {

		var atomicInteger = new AtomicInteger(0);
		var flux = Flux.create(sink -> {
			log.info("invoked");
			for (int i = 0; i < 3; i++) {
				sink.next(atomicInteger.incrementAndGet());
			}
			sink.complete();
		});

		flux.subscribe(subscriber("sub1"));
		flux.subscribe(subscriber("sub2"));
	}
}
