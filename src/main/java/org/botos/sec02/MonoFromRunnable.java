package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.subscriber;

/*
   Runnable Interface:
   The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread. The class must define a method with no argument that is called run.
   An advantage of implementing Runnable interface is that we can create an object of runnable interface and pass this object as an argument while creating a thread object using Thread class.

   public interface Runnable

   Method: void run()
 */
@Slf4j
public class MonoFromRunnable {

	public static void main(String[] args) {

		getProductName(1).subscribe(subscriber()); // Prints "received: product" because the Publisher is returning a value after calling supplier
		getProductName(2).subscribe(subscriber()); // Will NOT print "received: product" because the Runnable is returning empty() after calling runnable
	}

	private static Mono<String> getProductName(int productId) {
		return productId == 1 ? Mono.fromSupplier(() -> faker().commerce()
		                                                       .productName())
		                      : Mono.fromRunnable(() -> notifyBusiness(productId)); // The return type fo Runnable is void, it returns after running Runnable an empty()
	}

	/**
	 * Compiler complains because it returns int instead of void (Runnable returns void).
	 * Even though this method returns an int, the fromRunnable() ignores it,
	 * because it expects void, and therefore it returns an empty stream.
	 */
	private static int notifyBusiness(int productId) {
		log.info("notifying business on unavailable product {}", productId);
		return productId;
	}
}
