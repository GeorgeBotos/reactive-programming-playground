package org.botos.sec04;

import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.subscriber;

public class FluxGenerateWithState {

	public static void main(String[] args) {

//		option1();
		stateOption();
	}

	private static void option1() {
		AtomicInteger atomicInteger = new AtomicInteger(0);
		Flux.generate(synchronousSink -> {
			    var country = faker().country()
			                         .name();
			    synchronousSink.next(country);
			    if (atomicInteger.incrementAndGet() == 10 || "canada".equalsIgnoreCase(country)) {
				    synchronousSink.complete();
			    }
		    })
		    .subscribe(subscriber());
	}

	private static void stateOption() {
		Flux.generate(() -> 0, (counter, sink) -> {
			    var country = faker().country()
			                         .name();
			    sink.next(country);
			    counter++;
			    if (counter == 10 || "canada".equalsIgnoreCase(country)) {
				    sink.complete();
			    }
			    return counter;
		    })
		    .subscribe(subscriber());
	}
}
