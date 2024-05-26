package org.botos.sec13;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class Lec02ContextAppendUpdate {

	public static void main(String[] args) {
		demo2();
		sleep(2);
	}

	private static void demo1() {
//		getWelcomeMessage().contextWrite(context -> Context.empty())
//		getWelcomeMessage().contextWrite(context -> Context.of("user", "mike"))
//		getWelcomeMessage().contextWrite(context -> context.put("user", "mike"))
		getWelcomeMessage().contextWrite(context -> context.put("user",
		                                                        context.get("user")
		                                                               .toString()
		                                                               .toUpperCase()))
		                   .contextWrite(Context.of("a", "b")
		                                        .put("c", "d")
		                                        .put("e", "f"))
		                   .contextWrite(Context.of("user", "sam"))
		                   .subscribe(subscriber());
	}

	private static void demo2() {
		getWelcomeMessage().concatWith(Flux.merge(producer1(), producer2().contextWrite(context -> Context.empty())))
		                   .contextWrite(Context.of("user", "sam"))
		                   .subscribe(subscriber());
	}

	private static Mono<String> getWelcomeMessage() {
		return Mono.deferContextual(contextView -> {
			log.info("{}", contextView);
			if (contextView.hasKey("user")) {
				return Mono.just("Welcome %s!".formatted(contextView.get("user")
				                                                    .toString()));
			}
			return Mono.error(new RuntimeException("unauthenticated"));
		});
	}

	private static Mono<String> producer1() {
		return Mono.<String>deferContextual(contextView -> {
			           log.info("producer1: {}", contextView);
			           return Mono.empty();
		           })
		           .subscribeOn(Schedulers.boundedElastic());
	}

	private static Mono<String> producer2() {
		return Mono.<String>deferContextual(contextView -> {
			           log.info("producer2: {}", contextView);
			           return Mono.empty();
		           })
		           .subscribeOn(Schedulers.parallel());
	}
}
