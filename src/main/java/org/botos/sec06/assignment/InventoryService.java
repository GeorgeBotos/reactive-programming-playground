package org.botos.sec06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InventoryService implements OrderProcess {

	private final Map<String, Integer> db = new HashMap<>();

	@Override
	public void consume(Order order) {
		var currentInventory = db.getOrDefault(order.category(), 500);
		var updatedInventory = currentInventory - order.quantity();
		db.put(order.category(), updatedInventory);
	}

	@Override
	public Flux<String> stream() {
		return Flux.interval(Duration.ofSeconds(2))
		           .map(index -> db.toString());
	}
}
