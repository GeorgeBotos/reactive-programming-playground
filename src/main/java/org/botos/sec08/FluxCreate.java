package org.botos.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

import static org.botos.common.Util.sleep;

@Slf4j
public class FluxCreate {

	public static void main(String[] args) {

		var producer = Flux.create(sink -> {
			                   for (int i = 0; i <= 500 && !sink.isCancelled(); i++) {
				                   log.info("generating {}", i);
								   sink.next(i);
								   sleep(Duration.ofMillis(50));
			                   }
							   sink.complete();
		                   })
		                   .cast(Integer.class)
		                   .subscribeOn(Schedulers.parallel());

		producer.publishOn(Schedulers.boundedElastic())
		        .map(FluxCreate::timeConsumingTask)
		        .subscribe();

		sleep(60);

	}

	private static int timeConsumingTask(int item) {
		log.info("received: {}", item);
		sleep(1);
		return item;
	}
}
