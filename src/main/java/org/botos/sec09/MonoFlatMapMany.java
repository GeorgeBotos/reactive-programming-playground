package org.botos.sec09;

import org.botos.sec09.applications.OrderService;
import org.botos.sec09.applications.UserService;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class MonoFlatMapMany {

	public static void main(String[] args) {

		UserService.getUserId("mike")
		           .flatMapMany(OrderService::getUserOrders)
		           .subscribe(subscriber());

		sleep(2);
	}
}
