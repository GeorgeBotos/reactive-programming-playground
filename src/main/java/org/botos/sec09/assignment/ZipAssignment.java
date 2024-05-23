package org.botos.sec09.assignment;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class ZipAssignment {

	public static void main(String[] args) {
		var client = new ProductReviewClient();
		for (int i = 1; i < 10; i++) {
			client.getProduct(i)
			      .subscribe(subscriber());
		}
		sleep(3);
	}
}
