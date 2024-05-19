package org.botos.sec04.helper;

import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

import static org.botos.common.Util.faker;

public class NameGenerator implements Consumer<FluxSink<String>> {

	private FluxSink fluxSink;

	@Override
	public void accept(FluxSink<String> stringFluxSink) {
		fluxSink = stringFluxSink;
	}

	public void generate() {
		fluxSink.next(faker().name()
		                     .firstName());
	}
}
