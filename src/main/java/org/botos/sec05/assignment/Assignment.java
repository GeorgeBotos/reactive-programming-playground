package org.botos.sec05.assignment;

import lombok.extern.slf4j.Slf4j;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class Assignment {

	public static void main(String[] args) {

		var productClient = new ProductClient();

		for (int productId = 1; productId < 5; productId++) {
			productClient.getProduct(productId)
			             .subscribe(subscriber());
		}

		sleep(5);
	}
}
