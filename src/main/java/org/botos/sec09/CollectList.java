package org.botos.sec09;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.botos.common.Util.subscriber;

public class CollectList {

	public static void main(String[] args) {

		Flux.range(1, 10)
//		    .concatWith(Mono.error(new RuntimeException("oops")))
		    .collectList()
		    .subscribe(subscriber());
	}
}
