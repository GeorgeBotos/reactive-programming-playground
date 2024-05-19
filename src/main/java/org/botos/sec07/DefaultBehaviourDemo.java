package org.botos.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

@Slf4j
public class DefaultBehaviourDemo {

	public static void main(String[] args) {

		var flux = Flux.create(sink -> {
			               for (int i = 1; i < 3; i++) {
				               log.info("generating: {}", i);
				               sink.next(i);
			               }
			               sink.complete();
		               })
		               .doOnNext(value -> log.info("value: {}", value));

		Runnable runnable = () -> flux.subscribe(subscriber("sub1"));

		Thread.ofPlatform()
		      .start(runnable);  // As a default the subscriber's thread is doing all the work
	}
}
