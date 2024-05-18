package org.botos.sec03.assignment;

import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class StockClient extends AbstractHttpClient {

	private static final String URL = "localhost:7070/demo02/stock/stream";

	public Flux<Integer> getPrices() {
		return httpClient.get()
		                 .uri(URL)
		                 .responseContent()
		                 .asString()
		                 .map(Integer::valueOf);
	}
}
