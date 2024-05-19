package org.botos.sec06;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class HotPublisherCache {

	public static void main(String[] args) {

		var stockStream = getStockStream().replay(1)
		                                  .autoConnect(0);

		sleep(4);
		log.info("sam joined");
		stockStream.take(4)
		           .subscribe(subscriber("sam"));

		sleep(4);
		log.info("mike joined");
		stockStream.take(3)
		           .subscribe(subscriber("mike"));

		sleep(15);
	}

	private static Flux<Integer> getStockStream() {
		return Flux.generate(sink -> sink.next(faker().random()
		                                              .nextInt(10, 100)))
		           .take(10)
		           .delayElements(Duration.ofSeconds(3))
		           .doOnNext(price -> log.info("emitting price: {}", price))
		           .cast(Integer.class);
	}
}
