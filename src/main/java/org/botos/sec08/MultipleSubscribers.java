package org.botos.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class MultipleSubscribers {

	public static void main(String[] args) {

		var producer = Flux.generate(() -> 1,
		                             (state, sink) -> {
			                             log.info("generating {}", state);
			                             sink.next(state);
			                             return ++state;
		                             })
		                   .cast(Integer.class)
		                   .subscribeOn(Schedulers.parallel());

		producer.limitRate(5)
		        .publishOn(Schedulers.boundedElastic())
		        .map(MultipleSubscribers::timeConsumingTask)
		        .subscribe(subscriber("sub1"));

		producer.limitRate(100)
		        .publishOn(Schedulers.boundedElastic())
		        .subscribe(subscriber("sub2"));

		sleep(1);

	}

	private static int timeConsumingTask(int item) {
		sleep(1);
		return item;
	}
}
