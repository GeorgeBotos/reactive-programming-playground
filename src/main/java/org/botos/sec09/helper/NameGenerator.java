package org.botos.sec09.helper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;

@Slf4j
public class NameGenerator {

	private final List<String> redis = new ArrayList<>();

	public Flux<String> generateNames() {
		return Flux.generate(sink -> {
			           log.info("generating name");
			           sleep(1);
			           var name = faker().name().firstName();
			           redis.add(name);
			           sink.next(name);
		           })
		           .startWith(redis)
		           .cast(String.class);
	}
}
