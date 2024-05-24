package org.botos.sec09;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Lec07Zip {

	private record Car(String body,
	                   String engine,
	                   String wheels) {
	}

	public static void main(String[] args) {
		Flux.zip(getBody(),
		         getEngine(),
		         getWheels())
		    .map(tuple -> new Car(tuple.getT1(), tuple.getT2(), tuple.getT3()))
		    .subscribe(subscriber());

		sleep(5);
	}

	private static Flux<String> getBody() {
		return Flux.range(1, 5)
		           .map(index -> "body-" + index)
		           .delayElements(Duration.ofMillis(100));
	}

	private static Flux<String> getEngine() {
		return Flux.range(1, 3)
		           .map(index -> "engine-" + index)
		           .delayElements(Duration.ofMillis(200));
	}

	private static Flux<String> getWheels() {
		return Flux.range(1, 10)
		           .map(index -> "wheels-" + index)
		           .delayElements(Duration.ofMillis(75));
	}
}
