package org.botos.sec07;

import lombok.extern.slf4j.Slf4j;
import org.botos.sec07.client.ExternalServiceClient;
import reactor.core.scheduler.Schedulers;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class EventLoopIssueFix {

	public static void main(String[] args) {

		var client = new ExternalServiceClient();

		log.info("starting");

		for (int i = 1; i <= 5; i++) {
			client.getProductName(i)
//			      .publishOn(Schedulers.parallel())
                  .map(EventLoopIssueFix::process)
                  .subscribe(subscriber());
		}

		sleep(10);
	}

	private static String process(String input) {
		sleep(1);
		return input + "-processed";
	}
}
