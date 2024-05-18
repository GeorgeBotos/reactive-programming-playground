package org.botos.sec03.client;

import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class ExternalServiceClient extends AbstractHttpClient {

	public Flux<String> getNames() {
		return httpClient.get()
		                 .uri("/demo02/name/stream")
		                 .responseContent()
		                 .asString();
	}
}
