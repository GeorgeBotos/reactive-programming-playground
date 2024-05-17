package org.botos.common;

import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.LoopResources;

public abstract class AbstractHttpClient {

	private static final String BASE_URL = "http://localhost:7070";

	protected final HttpClient httpClient;

	public AbstractHttpClient() {
		// It creates a single event resource loop, rather then the number of core resource loops.
		var loopResources = LoopResources.create("george", 1, true);
		this.httpClient = HttpClient.create()
		                            .runOn(loopResources)
		                            .baseUrl(BASE_URL);
	}
}
