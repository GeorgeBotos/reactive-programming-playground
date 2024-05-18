package org.botos.sec03;

import org.botos.sec03.client.ExternalServiceClient;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class NonBlockingStreamingMessages {

	public static void main(String[] args) {

		var client = new ExternalServiceClient();

		client.getNames()
				.subscribe(subscriber("sub1"));

		client.getNames()
		      .subscribe(subscriber("sub2"));

		sleep(6);
	}
}
