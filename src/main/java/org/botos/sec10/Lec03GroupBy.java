package org.botos.sec10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.botos.common.Util.sleep;

@Slf4j
public class Lec03GroupBy {

	public static void main(String[] args) {

		Flux.range(1, 30)
		    .delayElements(Duration.ofMillis(1))
		    .groupBy(index -> index % 2)
		    .flatMap(Lec03GroupBy::processEvents)
		    .subscribe();

		sleep(6);
	}

	private static Mono<Void> processEvents(GroupedFlux<Integer, Integer> groupedFlux) {
		log.info("received flux for {}", groupedFlux.key());

		return groupedFlux.doOnNext(index -> log.info("key: {}, value: {}", groupedFlux.key(), index))
		                  .doOnComplete(() -> log.info("{} completed", groupedFlux.key()))
		                  .then();
	}
}
