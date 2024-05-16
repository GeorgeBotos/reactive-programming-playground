package org.botos.sec01.subscriber;

import lombok.Getter;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class SubscriberImpl implements Subscriber<String> {

	private final static Logger log = LoggerFactory.getLogger(SubscriberImpl.class);
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
	}

	@Override
	public void onNext(String email) {
		log.info("received: {}", email);
	}

	@Override
	public void onError(Throwable throwable) {
		log.error("error", throwable);
	}

	@Override
	public void onComplete() {
		log.info("completed!");
	}
}
