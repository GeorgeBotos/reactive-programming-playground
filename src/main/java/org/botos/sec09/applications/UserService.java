package org.botos.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {

	private static final Map<String, Integer> userTable = Map.ofEntries(Map.entry("sam", 1),
	                                                                    Map.entry("mike", 2),
	                                                                    Map.entry("jake", 3));

	public static Flux<User> getAllUsers() {
		return Flux.fromIterable(userTable.entrySet())
		           .map(entry -> new User(entry.getValue(), entry.getKey()));
	}

	public static Mono<Integer> getUserId(String username) {
		return Mono.fromSupplier(() ->userTable.get(username));
	}
}
