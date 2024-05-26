package org.botos.sec13.client;

import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

public class UserService {

	public static final Map<String, String> USER_CATEGORY = Map.ofEntries(Map.entry("sam", "standard"), Map.entry("mike", "prime"));

	static Function<Context, Context> userCategoryContext() {
		return context -> context.<String>getOrEmpty("user")
		                         .filter(USER_CATEGORY::containsKey)
		                         .map(USER_CATEGORY::get)
		                         .map(category -> context.put("category", category))
		                         .orElse(Context.empty());
	}
}
