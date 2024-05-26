package org.botos.sec13;

import org.botos.sec13.client.BookServiceClient;
import reactor.util.context.Context;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Lec03ContextRateLimiterDemo {

	public static void main(String[] args) {

		var client = new BookServiceClient();

		for (int i = 0; i < 20; i++) {
			client.getBook()
			      .contextWrite(Context.of("user", "mike"))
			      .subscribe(subscriber());
			sleep(1);
		}

		sleep(5);
	}
}
