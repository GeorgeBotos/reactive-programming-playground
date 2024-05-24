package org.botos.sec09;

import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

public class Lec11CollectList {

	public static void main(String[] args) {

		Flux.range(1, 10)
//		    .concatWith(Mono.error(new RuntimeException("oops")))
		    .collectList()
		    .subscribe(subscriber());
	}
}
