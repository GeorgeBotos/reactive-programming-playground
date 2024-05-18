package org.botos.sec03;

import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

public class FluxEmptyError {

	public static void main(String[] args) {

		Flux.empty()
		    .subscribe(subscriber("empty flux"));

		Flux.error(new RuntimeException("oops"))
		    .subscribe(subscriber("error flux"));
	}
}
