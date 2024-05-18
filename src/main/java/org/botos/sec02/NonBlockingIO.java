package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import org.botos.sec02.client.ExternalServiceClient;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class NonBlockingIO {

	public static void main(String[] args) {

		var client = new ExternalServiceClient();

		log.info("starting");

		for (int i = 1; i <= 100; i++) {
			client.getProductName(i)
			      .subscribe(subscriber());
		}
		sleep(2);
	}
}
