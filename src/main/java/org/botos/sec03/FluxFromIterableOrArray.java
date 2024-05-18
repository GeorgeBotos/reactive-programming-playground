package org.botos.sec03;

import reactor.core.publisher.Flux;

import java.util.List;

import static org.botos.common.Util.subscriber;

public class FluxFromIterableOrArray {

	public static void main(String[] args) {

		var list = List.of("a", "b", "c");
		Flux.fromIterable(list)
		    .subscribe(subscriber());

		Integer[] array = {1, 2, 3, 4, 5, 6};
		Flux.fromArray(array)
		    .subscribe(subscriber());
	}
}
