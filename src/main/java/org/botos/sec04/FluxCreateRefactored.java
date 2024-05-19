package org.botos.sec04;

import org.botos.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

import static org.botos.common.Util.subscriber;

public class FluxCreateRefactored {

	public static void main(String[] args) {

		var generator = new NameGenerator();
		var flux = Flux.create(generator);
		flux.subscribe(subscriber());

		for (int i = 0; i < 10; i++) {
			generator.generate();
		}
	}
}
