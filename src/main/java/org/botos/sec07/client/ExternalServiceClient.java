package org.botos.sec07.client;

import lombok.extern.slf4j.Slf4j;
import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ExternalServiceClient extends AbstractHttpClient {

	public Mono<String> getProductName(int productId) {
		return httpClient.get()
		                 .uri("/demo01/product/" + productId)
		                 .responseContent()
		                 .asString()
		                 .doOnNext(message -> log.info("next: {}", message))
		                 .next()
		                 .publishOn(Schedulers.boundedElastic());
	}
}
