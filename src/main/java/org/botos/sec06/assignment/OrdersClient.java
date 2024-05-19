package org.botos.sec06.assignment;

import lombok.extern.slf4j.Slf4j;
import org.botos.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Slf4j
public class OrdersClient extends AbstractHttpClient {

	private Flux<Order> orderFlux;

	public Flux<Order> orderStream() {
		if(Objects.isNull(orderFlux)) {
			orderFlux = getOrderStream();
		}
		return orderFlux;
	}

	private Flux<Order> getOrderStream() {
		return httpClient.get()
		                 .uri("/demo04/orders/stream")
		                 .responseContent()
		                 .asString()
		                 .map(this::parseOrder)
		                 .doOnNext(order -> log.info("received order: {}", order))
		                 .publish()
		                 .refCount(2);
	}

	private Order parseOrder(String message) {
		var array = message.split(":");
		return Order.builder()
		            .name(array[0])
		            .category(array[1])
		            .price(Integer.parseInt(array[2]))
		            .quantity(Integer.parseInt(array[3]))
		            .build();
	}
}
