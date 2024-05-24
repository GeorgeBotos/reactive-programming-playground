package org.botos.sec09;

import org.botos.sec09.applications.OrderService;
import org.botos.sec09.applications.User;
import org.botos.sec09.applications.UserService;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Lec10FluxFlatMap {

	public static void main(String[] args) {

		UserService.getAllUsers()
		           .map(User::id)
		           .flatMap(OrderService::getUserOrders)
		           .subscribe(subscriber());

		sleep(2);
	}
}
