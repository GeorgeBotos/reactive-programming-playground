package org.botos.sec02.client;

import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {

	public Mono<String> getProductName(int productId) {
		return httpClient.get()
		                 .uri("/demo01/product/" + productId)
		                 .responseContent()
		                 .asString()
		                 .next();
	}
}
