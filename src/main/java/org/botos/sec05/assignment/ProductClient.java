package org.botos.sec05.assignment;

import lombok.extern.slf4j.Slf4j;
import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
public class ProductClient extends AbstractHttpClient {

	private static final String BASE_URL = "localhost:7070/demo03";
	private static final String END_PATH = "/product/";

	private int productId;

	public Mono<String> getProduct(int productId) {
		this.productId = productId;
		return getProduct(Endpoint.PRODUCT).timeout(Duration.ofSeconds(2), getProduct(Endpoint.TIMEOUT_FALLBACK))
		                                   .switchIfEmpty(getProduct(Endpoint.EMPTY_FALLBACK));
	}

	private Mono<String> getProduct(Endpoint endpoint) {
		return httpClient.get()
		                 .uri(getUri(endpoint))
		                 .responseContent()
		                 .asString()
		                 .next();
	}

	private String getUri(Endpoint requestedEndpoint) {
		var middle_path = switch (requestedEndpoint) {
			case PRODUCT -> "";
			case EMPTY_FALLBACK -> "/empty-fallback";
			case TIMEOUT_FALLBACK -> "/timeout-fallback";
		};
		var uri = BASE_URL + middle_path + END_PATH + productId;
		log.info("endpoint uri: {}", uri);
		return uri;
	}
}
