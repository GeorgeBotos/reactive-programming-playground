package org.botos.sec10.assignment.buffer;

import lombok.Builder;

import static org.botos.common.Util.faker;

@Builder
public record BookOrder(String genre, String title, Integer price) {

	public static BookOrder generate() {
		var book = faker().book();
		return BookOrder.builder()
		                .genre(book.genre())
		                .title(book.title())
		                .price(faker().random().nextInt(10, 100))
		                .build();
	}
}
