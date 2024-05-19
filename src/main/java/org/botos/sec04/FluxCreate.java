package org.botos.sec04;

import reactor.core.publisher.Flux;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.subscriber;

/*
	To create a flux and emit items programmatically.
 */
public class FluxCreate {

	public static void main(String[] args) {

		Flux.create(fluxSink -> {
			    fluxSink.next(1);
			    fluxSink.next(2);
			    fluxSink.complete();
		    })
		    .subscribe(subscriber());

		Flux.create(fluxSink -> {
			    for (int i = 0; i < 10; i++) {
				    fluxSink.next(faker().country()
				                         .name());
			    }
			    fluxSink.complete();
		    })
		    .subscribe(subscriber());

		Flux.create(fluxSink -> {
			    String country;
			    do {
				    country = faker().country()
				                     .name();
				    fluxSink.next(country);
			    } while (!"canada".equalsIgnoreCase(country));
			    fluxSink.complete();
		    })
		    .subscribe(subscriber());
	}
}
