package org.botos.sec09;

import org.botos.sec09.applications.PaymentService;
import org.botos.sec09.applications.UserService;

import static org.botos.common.Util.subscriber;

public class Lec08MonoFlatMap {

	public static void main(String[] args) {

		// We have username. => Get user account balance.
		UserService.getUserId("mike")
		           .flatMap(PaymentService::getUserBalance)
		           .subscribe(subscriber());
	}
}
