package org.botos.sec01.publisher;

import lombok.NoArgsConstructor;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

@NoArgsConstructor
public class PublisherImpl implements Publisher<String> {

	private Subscriber<? super String> subscriber;

	@Override
	public void subscribe(Subscriber<? super String> subscriber) {
		var subscription = new SubscriptionImpl(subscriber);
		subscriber.onSubscribe(subscription);
	}
}
