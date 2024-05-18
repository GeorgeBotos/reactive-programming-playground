package org.botos.sec03;

import reactor.core.publisher.Flux;

import java.util.List;

import static org.botos.common.Util.subscriber;

public class FluxFromStream {

	public static void main(String[] args) {

		var numbers = List.of(1, 2, 3, 4, 5, 6);
		var numberStream = numbers.stream();

		var flux = Flux.fromStream(numberStream);

		flux.subscribe(subscriber("sub1"));
		flux.subscribe(subscriber("sub2")); // Subscriber will receive error, because streams can be operated on only once

		var flux2 = Flux.fromStream(numbers::stream);

		flux2.subscribe(subscriber("sub3"));
		flux2.subscribe(subscriber("sub4"));
	}
}
