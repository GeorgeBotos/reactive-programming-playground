package org.botos.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class Lec12Then {

	public static void main(String[] args) {

		var records = List.of("a", "b", "c");

		saveRecords(records).then(sendNotification(records))
		                    .subscribe(subscriber());

		sleep(5);
	}

	private static Flux<String> saveRecords(List<String> records) {
		return Flux.fromIterable(records)
		           .map(record -> "saved " + record)
		           .delayElements(Duration.ofMillis(500));
	}

	private static Mono<Void> sendNotification(List<String> records) {
		return Mono.fromRunnable(() -> log.info("all these {} records saved successfully", records));
	}
}


