package org.botos.sec03.helper;

import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;

public class NameGenerator {

	public static List<String> generateNames(int count) {
		return IntStream.rangeClosed(1, count)
		                .mapToObj(index -> generateName())
		                .toList();
	}

	public static Flux<String> generateNameFlux(int count) {
		return Flux.range(1, count)
		           .map(index -> generateName());
	}

	private static String generateName() {
		sleep(1);
		return faker().name()
		              .firstName();
	}
}
