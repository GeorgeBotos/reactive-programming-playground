package org.botos.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

/*
	Scheduler types:
	(1) boundedElastic ==> used for network, time-consuming or blocking operations
	(2) parallel       ==> used for CPU intensive tasks
	(3) single         ==> used for one-off tasks by a single dedicated thread (safe)
	(4) immediate      ==> using the curren thread
 */
@Slf4j
public class SubscribeOn {

	public static void main(String[] args) {

		var flux = Flux.create(sink -> {
			               for (int i = 1; i < 3; i++) {
				               log.info("generating: {}", i);
				               sink.next(i);
			               }
			               sink.complete();
		               })
		               .doOnNext(value -> log.info("value: {}", value))
		               .doFirst(() -> log.info("first 1"))
		               .subscribeOn(Schedulers.boundedElastic())
		               .doFirst(() -> log.info("first 2"));

		Runnable runnable1 = () -> flux.subscribe(subscriber("sub1"));
		Runnable runnable2 = () -> flux.subscribe(subscriber("sub2"));

//		flux.subscribe(subscriber("sub1"));
//		flux.subscribe(subscriber("sub2"));

		Thread.ofPlatform()
		      .start(runnable1);
		Thread.ofPlatform()
		      .start(runnable2);

		sleep(2);
	}
}
