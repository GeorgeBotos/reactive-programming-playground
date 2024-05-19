package org.botos.sec06.util;

import org.slf4j.Logger;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class Movie {

	public static Flux<String> stream(Logger log) {
		return Flux.generate(() -> {
			           log.info("received request");
			           return 1;
		           }, (state, sink) -> {
			           var scene = "movie scene " + state;
			           log.info("playing: {}", scene);
			           sink.next(scene);
			           return ++state;
		           })
		           .take(10)
		           .delayElements(Duration.ofSeconds(1))
		           .cast(String.class);
	}
}
