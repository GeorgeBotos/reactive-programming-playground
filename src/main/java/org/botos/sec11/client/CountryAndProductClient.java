package org.botos.sec11.client;

import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class CountryAndProductClient extends AbstractHttpClient {

	public static final String COUNTRY_PATH = "/demo06/country";
	public static final String PRODUCT_NAME_PATH = "/demo06/product/";

	public Mono<String> getProductName(int productId) {
		return getInfo(PRODUCT_NAME_PATH + productId);
	}

	public Mono<String> getCountry() {
		return getInfo(COUNTRY_PATH);
	}

	private Mono<String> getInfo(String path) {
		return httpClient.get()
		                 .uri(path)
		                 .response(this::toResponse)
		                 .next();
	}

	private Flux<String> toResponse(HttpClientResponse response, ByteBufFlux byteBufFlux) {
		return switch (response.status().code()) {
			case 200 -> byteBufFlux.asString();
			case 400 -> Flux.error(new ClientError());
			default -> Flux.error(new ServerError());
		};
	}
}
