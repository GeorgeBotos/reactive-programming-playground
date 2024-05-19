package org.botos.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class Parallel {

	public static void main(String[] args) {

		Flux.range(1, 10)
		    .parallel(5)
		    .runOn(Schedulers.parallel())
		    .map(Parallel::process)
		    .sequential()
		    .map(value -> value + "a")
		    .subscribe(subscriber());

		sleep(12);
	}

	private static int process(int i) {
		log.info("time consuming task {}", i);
		sleep(2);
		return i * 2;
	}
}
