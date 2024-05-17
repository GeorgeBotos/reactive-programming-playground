package org.botos.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
@RequiredArgsConstructor
public class DefaultSubscriber<T> implements Subscriber<T> {

	private final String name;

	@Override
	public void onSubscribe(Subscription subscription) {
		subscription.request(Long.MAX_VALUE);
	}

	@Override
	public void onNext(T item) {
		log.info("{} received: {}", name, item);
	}

	@Override
	public void onError(Throwable throwable) {
		log.error("{} got error", name, throwable);
	}

	@Override
	public void onComplete() {
		log.info("{} completed!", name);
	}
}
