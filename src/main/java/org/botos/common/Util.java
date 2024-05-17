package org.botos.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;

import java.time.Duration;

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
}
