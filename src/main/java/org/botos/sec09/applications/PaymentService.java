package org.botos.sec09.applications;

import reactor.core.publisher.Mono;

import java.util.Map;

public class PaymentService {

	private static final Map<Integer, Integer> userBalanceTable = Map.ofEntries(Map.entry(1, 100),
	                                                                            Map.entry(2, 200),
	                                                                            Map.entry(3, 300));

	public static Mono<Integer> getUserBalance(Integer userId) {
		return Mono.fromSupplier(() -> userBalanceTable.get(userId));
	}
}
