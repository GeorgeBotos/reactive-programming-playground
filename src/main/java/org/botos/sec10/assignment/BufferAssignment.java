package org.botos.sec10.assignment;

import org.botos.sec10.assignment.buffer.BookService;

import java.time.Duration;
import java.util.Set;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class BufferAssignment {

	public static void main(String[] args) {
		var allowedCategories = Set.of("Science fiction", "Fantasy", "Suspense/Thriller");

		BookService.getBookOrders()
		           .filter(order -> allowedCategories.contains(order.genre()))
		           .buffer(Duration.ofSeconds(5))
		           .map(BookService::createReport)
		           .subscribe(subscriber());
		sleep(20);
	}

}
