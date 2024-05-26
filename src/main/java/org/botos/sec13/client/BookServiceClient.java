package org.botos.sec13.client;

import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class BookServiceClient extends AbstractHttpClient {

	public Mono<String> getBook() {
		return httpClient.get()
		                 .uri("/demo07/book")
		                 .responseContent()
		                 .asString()
		                 .startWith(RateLimiter.limitCalls())
		                 .contextWrite(UserService.userCategoryContext())
		                 .next();
	}
}
