package org.botos.sec01;

import org.botos.sec01.publisher.PublisherImpl;
import org.botos.sec01.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {

	public static void main(String[] args) throws InterruptedException {
		runDemo4();
	}

	private static void runDemo1() {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
	}

	private static void runDemo2() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription()
		          .request(3);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription()
		          .request(3);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription()
		          .request(3);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription()
		          .request(3);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription()
		          .request(3);
		Thread.sleep(Duration.ofSeconds(2));
	}

	private static void runDemo3() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription()
		          .request(3);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription()
		          .cancel();
		subscriber.getSubscription()
		          .request(3);
	}

	private static void runDemo4() throws InterruptedException {
		var publisher = new PublisherImpl();
		var subscriber = new SubscriberImpl();
		publisher.subscribe(subscriber);
		subscriber.getSubscription()
		          .request(3);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription()
		          .request(11);
		Thread.sleep(Duration.ofSeconds(2));
		subscriber.getSubscription()
		          .request(3);
	}
}
