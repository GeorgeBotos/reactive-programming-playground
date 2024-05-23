package org.botos.sec09.assignment;

import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Mono;

public class ProductReviewClient extends AbstractHttpClient {

	public static final String PRICE_PATH = "/demo05/price/";
	public static final String NAME_PATH = "/demo05/product/";
	public static final String REVIEW_PATH = "/demo05/review/";

	public Mono<Product> getProduct(int productId) {
		return Mono.zip(getName(productId),
		                getReview(productId),
		                getPrice(productId))
		           .map(tuple -> new Product(tuple.getT1(), tuple.getT2(), tuple.getT3()));
	}

	private Mono<String> getPrice(int productId) {
		return getInfo(PRICE_PATH, productId);
	}

	private Mono<String> getName(int productId) {
		return getInfo(NAME_PATH, productId);
	}

	private Mono<String> getReview(int productId) {
		return getInfo(REVIEW_PATH, productId);
	}

	private Mono<String> getInfo(String path, int productId) {
		return httpClient.get()
		                 .uri(path + productId)
		                 .responseContent()
		                 .asString()
		                 .next();
	}
}
