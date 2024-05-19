package org.botos.sec04;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.subscriber;

@Slf4j
public class FluxGenerateAssignment {

	public static void main(String[] args) {

//		option1();
		option2();
	}

	public static void option1() {
		Flux.generate(synchronousSink -> {
			    var country = faker().country()
			                         .name();
			    synchronousSink.next(country);
			    if ("canada".equalsIgnoreCase(country)) {
				    synchronousSink.complete();
			    }
		    })
		    .subscribe(subscriber());
	}

	public static void option2() {
		Flux.<String>generate(synchronousSink -> synchronousSink.next(faker().country()
		                                                                     .name()))
		    .takeUntil("canada"::equalsIgnoreCase)
		    .subscribe(subscriber());
	}
}
