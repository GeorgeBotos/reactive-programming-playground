package org.botos.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

import static org.botos.common.Util.sleep;

@Slf4j
public class BackPressureStrategies {
	public static void main(String[] args) {

		var producer = Flux.create(sink -> {
			                   for (int i = 1; i <= 500 && !sink.isCancelled(); i++) {
				                   log.info("generating {}", i);
				                   sink.next(i);
				                   sleep(Duration.ofMillis(50));
			                   }
			                   sink.complete();
		                   })
//		                   }, FluxSink.OverflowStrategy.BUFFER)
                           .cast(Integer.class)
                           .subscribeOn(Schedulers.parallel());

		producer.onBackpressureLatest()
//				.onBackpressureDrop()
                .log()
//				.onBackpressureBuffer(10) // Sending en error, if buffer is overflown
//				.onBackpressureError() // Sending en error if more items are produced, then a limitRate()
                .limitRate(1)
//				.onBackpressureBuffer() // Useful when the producer has spikes in producing data, sending no error signal
                .publishOn(Schedulers.boundedElastic())
                .map(BackPressureStrategies::timeConsumingTask)
                .subscribe();

		sleep(2);

	}

	private static int timeConsumingTask(int item) {
		log.info("received: {}", item);
		sleep(1);
		return item;
	}
}
