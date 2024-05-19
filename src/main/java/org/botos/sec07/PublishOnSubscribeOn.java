package org.botos.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class PublishOnSubscribeOn {

	public static void main(String[] args) {

		var flux = Flux.create(sink -> {
			               for (int i = 1; i < 3; i++) {
				               log.info("generating: {}", i);
				               sink.next(i);
			               }
			               sink.complete();
		               })
		               .publishOn(Schedulers.parallel())
		               .doOnNext(value -> log.info("value: {}", value))
		               .doFirst(() -> log.info("first-1"))
		               .subscribeOn(Schedulers.boundedElastic())
		               .doFirst(() -> log.info("first-2"));

		Runnable runnable = () -> flux.subscribe(subscriber());

		Thread.ofPlatform().start(runnable);

		sleep(2);
	}
}
