package org.botos.sec09.assignment;

import reactor.core.publisher.Flux;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class FlatMapAssignment {

	public static void main(String[] args) {

		var client = new ProductReviewClient();

		Flux.range(1, 10)
		    .flatMap(client::getProduct)
		    .subscribe(subscriber());

		sleep(2);
	}
}
