package org.botos.sec05;

import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

/*
	Handle behaves like filter + map

	1 => -2
	4 => do not send
	7 => error
	everything else => send as it is
 */
public class Handle {

	public static void main(String[] args) {

		Flux<Integer> flux = Flux.range(1, 10);

		Flux<Integer> flux1 = flux.handle((index, sink) -> {
			sink.error(new RuntimeException("oops"));
		});

//		flux.subscribe(subscriber());
//		flux1.subscribe(subscriber());

		flux.filter(index -> index != 7)
		    .handle((index, sink) -> {
			    switch (index) {
				    case 1 -> sink.next(-2);
				    case 4 -> {}
				    case 7 -> sink.error(new RuntimeException("oops"));
				    default -> sink.next(index);
			    }
		    })
		    .cast(Integer.class)
		    .subscribe(subscriber());
	}
}
