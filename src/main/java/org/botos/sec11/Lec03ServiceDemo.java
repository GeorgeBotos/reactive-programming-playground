package org.botos.sec11;

import lombok.extern.slf4j.Slf4j;
import org.botos.sec11.client.CountryAndProductClient;
import org.botos.sec11.client.ServerError;
import reactor.util.retry.Retry;

import java.time.Duration;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class Lec03ServiceDemo {

	public static void main(String[] args) {
		retry();

		sleep(10);
	}

	private static void repeat() {
		var client = new CountryAndProductClient();
		client.getCountry()
		      .repeat()
		      .takeUntil("canada"::equalsIgnoreCase)
		      .subscribe(subscriber());
	}

	private static void retry() {
		var client = new CountryAndProductClient();
		client.getProductName(2)
		      .retryWhen(retryOnServerError())
		      .subscribe(subscriber());
	}

	private static Retry retryOnServerError() {
		return Retry.fixedDelay(20, Duration.ofSeconds(1))
		            .filter(throwable -> ServerError.class.equals(throwable.getClass()))
		            .doBeforeRetry(retrySignal -> log.info("retrying {}",
		                                                   retrySignal.failure()
		                                                              .getMessage()));
	}
}
