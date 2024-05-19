package org.botos.sec06.assignment;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Assignment {

	public static void main(String[] args) {

		var client = new OrdersClient();
		var inventoryService = new InventoryService();
		var revenueService = new RevenueService();

		client.orderStream()
		      .subscribe(inventoryService::consume);
		client.orderStream()
		      .subscribe(revenueService::consume);

		inventoryService.stream()
		                .subscribe(subscriber("inventory"));
		revenueService.stream()
		              .subscribe(subscriber("revenue"));

		sleep(30);
	}
}
