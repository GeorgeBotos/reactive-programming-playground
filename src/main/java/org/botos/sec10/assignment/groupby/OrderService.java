package org.botos.sec10.assignment.groupby;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static org.botos.common.Util.faker;

public class OrderService {

	private static final Map<String, UnaryOperator<Flux<PurchaseOrder>>> PROCESSOR_MAP = Map.ofEntries(Map.entry("Kids", kidsProcessing()),
	                                                                                                   Map.entry("Automotive", automotiveProcessing()));

	public static Predicate<PurchaseOrder> canProcess() {
		return po -> PROCESSOR_MAP.containsKey(po.category());
	}

	public static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category) {
		return PROCESSOR_MAP.get(category);
	}

	private static UnaryOperator<Flux<PurchaseOrder>> automotiveProcessing() {
		return flux -> flux.map(po -> PurchaseOrder.builder()
		                                           .item(po.item())
		                                           .category(po.category())
		                                           .price(po.price() + 100)
		                                           .build());
	}

	private static UnaryOperator<Flux<PurchaseOrder>> kidsProcessing() {
		return flux -> flux.flatMap(po -> getFreeKidsOrder(po).flux()
		                                                      .startWith(po));
	}

	private static Mono<PurchaseOrder> getFreeKidsOrder(PurchaseOrder po) {
		return Mono.fromSupplier(() -> PurchaseOrder.builder()
		                                            .item(po.item() + "-FREE")
		                                            .category(po.category())
		                                            .price(0)
		                                            .build());
	}

	public static Flux<PurchaseOrder> gerOrders() {
		return Flux.interval(Duration.ofMillis(200))
		           .map(i -> PurchaseOrder.builder()
		                                  .item(faker().commerce()
		                                               .productName())
		                                  .category(faker().commerce()
		                                                   .department())
		                                  .price(faker().random()
		                                                .nextInt(10, 100))
		                                  .build());
	}
}
