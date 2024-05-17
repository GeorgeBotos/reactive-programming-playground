package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.botos.common.Util.subscriber;

/*
   Callable Functional Interface:
   A method that returns a result and may throw an exception. Implementors define a single method with no arguments called call.

   @FunctionalInterface
   public interface Callable<V>

   Method: V call() throws Exception
 */
@Slf4j
public class MonoFromCallable {

	public static void main(String[] args) {

		var ints = List.of(1, 2, 3);
		Mono.fromCallable(() -> sum(ints))  // Callable can throw checked exception, while Supplier can throw only RuntimeException
		    .subscribe(subscriber());

//		Mono.fromSupplier(() -> sum(ints))  // Supplier can throw only RuntimeException, but not checked exception
//		    .subscribe(subscriber());
	}

	private static int sum(List<Integer> ints) throws Exception {
		log.info("finding the sum of {}", ints);
		return ints.stream()
		           .mapToInt(number -> number)
		           .sum();
	}
}
