package org.botos.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

/*
	Reactor supports virtual threads
	System.setProperty("reactor.scheduler.defaultBoundedElasticOnVirtualThreads", "true");

	Scheduler       |  # of Threads     |  Usage
	-----------------------------------------------------------------------------------------
	boundedElastic  |  10 * No. of CPU  |   Network / time-consuming / blocking operations
	parallel        |  No. of CPU       |   CPU intensive tasks
	single          |  1                |   A single dedicated thread for one-off tasks
	immediate       |  n/a              |   Current thread
 */
@Slf4j
public class VirtualThreads {

	public static void main(String[] args) {

		var flux = Flux.create(sink -> {
			               for (int i = 1; i < 3; i++) {
				               log.info("generating: {}", i);
				               sink.next(i);
			               }
			               sink.complete();
		               })
		               .subscribeOn(Schedulers.newParallel("george"))
		               .doOnNext(value -> log.info("value: {}", value))
		               .doFirst(() -> log.info("first 1-{}",
		                                       Thread.currentThread()
		                                             .isVirtual()))
		               .subscribeOn(Schedulers.boundedElastic())
		               .doFirst(() -> log.info("first 2"));

		Runnable runnable1 = () -> flux.subscribe(subscriber("sub1"));

		Thread.ofPlatform()
		      .start(runnable1);

		sleep(2);
	}
}
