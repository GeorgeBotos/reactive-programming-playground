package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static org.botos.common.Util.subscriber;

@Slf4j
public class MonoEmptyError {

	public static void main(String[] args) {
		getUsername(1).subscribe(subscriber("id=1"));
		getUsername(2).subscribe(subscriber("id=2"));
		getUsername(3).subscribe(subscriber("id=3"));
		getUsername(3).subscribe(log::info,
		                                error -> log.error(error.toString()));

	}

	private static Mono<String> getUsername(int userId) {
		return switch (userId) {
			case 1 -> Mono.just("Sam");
			case 2 -> Mono.empty(); // null
			default -> Mono.error(new RuntimeException("invalid input"));
		};
	}
}
