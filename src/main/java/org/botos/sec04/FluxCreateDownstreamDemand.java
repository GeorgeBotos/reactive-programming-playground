package org.botos.sec04;

import lombok.extern.slf4j.Slf4j;
import org.botos.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;

@Slf4j
public class FluxCreateDownstreamDemand {

	public static void main(String[] args) {
		produceEarly();
		produceOnDemand();
	}

	private static void produceEarly() {
		var subscriber = new SubscriberImpl();

		Flux.<String>create(fluxSink -> {    //Flux.create is eagerly created
			    for (int i = 0; i < 10; i++) {
				    var name = faker().name()
				                      .firstName();
				    log.info("created: {}", name);
				    fluxSink.next(name);
			    }
			    fluxSink.complete();
		    })
		    .subscribe(subscriber);

		sleep(2);
		subscriber.getSubscription()
		          .request(2);
		sleep(2);
		subscriber.getSubscription()
		          .request(2);
	}

	private static void produceOnDemand() {
		var subscriber = new SubscriberImpl();

		Flux.<String>create(fluxSink -> {    //Flux.create is eagerly created
			    fluxSink.onRequest(request -> {
				    for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
					    var name = faker().name()
					                      .firstName();
					    log.info("generated: {}", name);
					    fluxSink.next(name);
				    }

			    });
		    })
		    .subscribe(subscriber);

		sleep(2);
		subscriber.getSubscription()
		          .request(2);
		sleep(2);
		subscriber.getSubscription()
		          .request(2);
		sleep(2);
		subscriber.getSubscription()
		          .cancel();
		sleep(2);
		subscriber.getSubscription()
		          .request(2);
	}
}
