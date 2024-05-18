package org.botos.sec03;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

@Slf4j
public class FLuxJust {

	public static void main(String[] args) {

		Flux.just(1, 2, 3, "sam")
		    .subscribe(subscriber());
	}
}
