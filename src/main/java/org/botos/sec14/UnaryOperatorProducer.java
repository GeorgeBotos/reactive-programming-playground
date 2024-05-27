package org.botos.sec14;

import reactor.core.publisher.Flux;

import java.util.function.UnaryOperator;

public class UnaryOperatorProducer {

	public UnaryOperator<Flux<String>> process() {
		return stringFlux -> stringFlux.filter(string -> string.length() > 1)
		                               .map(String::toUpperCase)
		                               .map(string -> string + ":" + string.length());
	}
}
