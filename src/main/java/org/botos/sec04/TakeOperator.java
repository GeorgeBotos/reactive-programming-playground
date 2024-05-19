package org.botos.sec04;

import reactor.core.publisher.Flux;

import java.util.stream.IntStream;

import static org.botos.common.Util.subscriber;

public class TakeOperator {

	public static void main(String[] args) {

//		IntStream.rangeClosed(1, 10)
//		         .limit(3)
//		         .forEach(System.out::println);

//		takeWhile();
		takeUntil();
	}

	private static void take() {
		Flux.range(1, 10)
		    .log("take log")
		    .take(3)
		    .log("subscriber log")
		    .subscribe(subscriber());
	}

	private static void takeWhile() {
		Flux.range(1, 10)
		    .log("take log")
		    .takeWhile(index -> index < 5) // Stop when the condition is not met
		    .log("subscriber log")
		    .subscribe(subscriber());
	}

	private static void takeUntil() {
		Flux.range(1, 10)
		    .log("take log")
		    .takeUntil(index -> index < 5) // Stop when the condition is met + takes the last item, that met the condition
		    .log("subscriber log")
		    .subscribe(subscriber());
	}


}
