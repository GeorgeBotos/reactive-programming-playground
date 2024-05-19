package org.botos.sec04;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

@Slf4j
public class FluxGenerate {

	public static void main(String[] args) {

		Flux.generate(synchronousSink -> {   // synchronousSink allows only one value defined
			    log.info("invoked");
			    synchronousSink.next(1);
//			    synchronousSink.next(2);
//			    synchronousSink.complete(); // If no onComplete() or onError() is set, Flux.generate() will call the defined lambda in a loop
		    })
		    .take(4)
		    .subscribe(subscriber());
	}
}
