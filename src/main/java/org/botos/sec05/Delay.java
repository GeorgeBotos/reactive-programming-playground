package org.botos.sec05;

import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Delay {

	public static void main(String[] args) {

		Flux.range(1, 10)
		    .log()
		    .delayElements(Duration.ofSeconds(1))
            .subscribe(subscriber());

		sleep(12);
	}
}
