package org.botos.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.botos.common.Util.faker;

@Slf4j
public class Transfom {

	record Customer(int id, String name) {
	}

	record PurchaseOrder(String productName, int price, int quantity) {
	}

	public static void main(String[] args) {

		var isDebugEnabled = faker().bool()
		                            .bool();

		log.info("debug enabled: {}", isDebugEnabled);

		getCustomers().transform(isDebugEnabled ? addDebugger() : Function.identity())
		              .subscribe();

		getPurchaseOrders().transform(addDebugger())
		                   .subscribe();
	}

	private static Flux<Customer> getCustomers() {
		return Flux.range(1, 5)
		           .map(index -> new Customer(index,
		                                      faker().name()
		                                             .firstName()));
	}

	private static Flux<PurchaseOrder> getPurchaseOrders() {
		return Flux.range(1, 5)
		           .map(index -> new PurchaseOrder(faker().commerce()
		                                                  .productName(), index, index * 10));
	}

	private static <T> UnaryOperator<Flux<T>> addDebugger() {
		return flux -> flux.doOnNext(item -> log.info("received: {}", item))
		                   .doOnComplete(() -> log.info("completed"))
		                   .doOnError(throwable -> log.error("errror", throwable));
	}
}
