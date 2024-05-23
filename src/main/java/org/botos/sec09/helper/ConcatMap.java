package org.botos.sec09.helper;

import org.botos.sec09.assignment.ProductReviewClient;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class ConcatMap {

	public static void main(String[] args) {

		var client = new ProductReviewClient();

		Flux.range(1, 10)
		    .concatMap(client::getProduct)
		    .subscribe(subscriber());

		sleep(12);
	}
}
