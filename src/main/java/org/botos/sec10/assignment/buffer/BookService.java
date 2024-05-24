package org.botos.sec10.assignment.buffer;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

	public static Flux<BookOrder> getBookOrders() {
		return Flux.interval(Duration.ofMillis(200))
		           .map(i -> BookOrder.generate());
	}

	public static RevenueReport createReport(List<BookOrder> orders) {
		var revenue = orders.stream()
		                    .collect(Collectors.groupingBy(BookOrder::genre,
		                                                   Collectors.summingInt(BookOrder::price)));
		return RevenueReport.builder()
		                    .time(LocalTime.now())
		                    .revenue(revenue)
		                    .build();
	}
}
