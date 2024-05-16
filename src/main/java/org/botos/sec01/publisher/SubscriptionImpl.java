package org.botos.sec01.publisher;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class SubscriptionImpl implements Subscription {

	private static final Logger log = LoggerFactory.getLogger(SubscriptionImpl.class);

	private static final int MAX_ITEMS = 10;

	private final Faker faker;

	private final Subscriber<? super String> subscriber;

	private boolean isCancelled;

	private int count = 0;

	public SubscriptionImpl(Subscriber<? super String> subscriber) {
		this.subscriber = subscriber;
		this.faker = Faker.instance();
	}

	@Override
	public void request(long requested) {
		if (isCancelled) {
			return;
		}
		log.info("subscriber requested {} items", requested);
		if(requested > MAX_ITEMS) {
			subscriber.onError(new RuntimeException("validation failed"));
			isCancelled = true;
			return;
		}
		for (int i = 0; i < requested && count < MAX_ITEMS; i++) {
			count++;
			subscriber.onNext(faker.internet()
			                       .emailAddress());
		}
		if(count == MAX_ITEMS) {
			log.info("no more data to produce");
			subscriber.onComplete();
			isCancelled = true;
		}
	}

	@Override
	public void cancel() {
		log.info("subscriber has cancelled");
		isCancelled = true;
	}
}
