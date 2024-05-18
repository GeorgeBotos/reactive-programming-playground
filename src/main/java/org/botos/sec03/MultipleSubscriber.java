package org.botos.sec03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

@Slf4j
public class MultipleSubscriber {

	public static void main(String[] args) {

		var flux = Flux.just(1, 2, 3, 4, 5, 6);

		flux.subscribe(subscriber("sub1"));
		flux.filter(number -> number > 7)
		    .subscribe(subscriber("sub2"));
		flux.filter(number -> number % 2 == 0)
		    .map(number -> number + "a")
		    .subscribe(subscriber("sub3"));
	}
}
