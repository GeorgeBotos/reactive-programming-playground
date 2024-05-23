package org.botos.common;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.function.UnaryOperator;

@Slf4j
public class Util {

	private static final Faker faker = Faker.instance();

	public static <T> Subscriber<T> subscriber() {
		return new DefaultSubscriber<>("");
	}

	public static <T> Subscriber<T> subscriber(String name) {
		return new DefaultSubscriber<>(name);
	}

	public static Faker faker() {
		return faker;
	}

	public static void sleep(int second) {
		try {
			Thread.sleep(Duration.ofSeconds(second));
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void sleep(Duration duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> UnaryOperator<Flux<T>> fluxLogger(String name) {
		return flux -> flux.doOnSubscribe(subscription -> log.info("subscribing to {}", name))
		                   .doOnCancel(() -> log.info("cancelling {}", name))
		                   .doOnComplete(() -> log.info("{} completed", name));
	}
}
