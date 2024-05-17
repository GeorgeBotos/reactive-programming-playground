package org.botos.sec02;

import org.botos.sec01.subscriber.SubscriberImpl;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

public class MonoJust {

	public static void main(String[] args) {

		Publisher<String> mono = Mono.just("george");
		System.out.println(mono);

		var subscriber = new SubscriberImpl();

		mono.subscribe(subscriber);

		subscriber.getSubscription()
		          .request(10);
		// adding these will have no effect as producer already sent complete!
		subscriber.getSubscription()
		          .request(10);
		subscriber.getSubscription()
		          .cancel();

	}
}
