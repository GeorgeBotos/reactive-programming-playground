package org.botos.sec09.applications;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.fluxLogger;

public class OrderService {

	private static final Map<Integer, List<Order>> orderTable = Map.ofEntries(Map.entry(1,
	                                                                                    List.of(Order.builder()
	                                                                                                 .userId(1)
	                                                                                                 .productName(faker().commerce()
	                                                                                                                     .productName())
	                                                                                                 .price(faker().random()
	                                                                                                               .nextInt(10, 100))
	                                                                                                 .build(),
	                                                                                            Order.builder()
	                                                                                                 .userId(1)
	                                                                                                 .productName(faker().commerce()
	                                                                                                                     .productName())
	                                                                                                 .price(faker().random()
	                                                                                                               .nextInt(10, 100))
	                                                                                                 .build())),
	                                                                          Map.entry(2,
	                                                                                    List.of(Order.builder()
	                                                                                                 .userId(2)
	                                                                                                 .productName(faker().commerce()
	                                                                                                                     .productName())
	                                                                                                 .price(faker().random()
	                                                                                                               .nextInt(10, 100))
	                                                                                                 .build(),
	                                                                                            Order.builder()
	                                                                                                 .userId(2)
	                                                                                                 .productName(faker().commerce()
	                                                                                                                     .productName())
	                                                                                                 .price(faker().random()
	                                                                                                               .nextInt(10, 100))
	                                                                                                 .build(),
	                                                                                            Order.builder()
	                                                                                                 .userId(2)
	                                                                                                 .productName(faker().commerce()
	                                                                                                                     .productName())
	                                                                                                 .price(faker().random()
	                                                                                                               .nextInt(10, 100))
	                                                                                                 .build())),
	                                                                          Map.entry(3, List.of()));

	public static Flux<Order> getUserOrders(Integer userId) {
		return Flux.fromIterable(orderTable.get(userId))
		           .delayElements(Duration.ofMillis(500))
		           .transform(fluxLogger("user" + userId));
	}
}
