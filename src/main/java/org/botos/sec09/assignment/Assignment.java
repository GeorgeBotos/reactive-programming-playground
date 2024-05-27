package org.botos.sec09.assignment;

import org.botos.sec09.applications.OrderService;
import org.botos.sec09.applications.PaymentService;
import org.botos.sec09.applications.User;
import org.botos.sec09.applications.UserInformation;
import org.botos.sec09.applications.UserService;
import reactor.core.publisher.Mono;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Assignment {


	public static void main(String[] args) {


		UserService.getAllUsers()
		           .flatMap(Assignment::getUserInformation)
		           .subscribe(subscriber());

		sleep(2);
	}

	private static Mono<UserInformation> getUserInformation(User user) {
		return Mono.zip(PaymentService.getUserBalance(user.id()),
		                OrderService.getUserOrders(user.id())
		                            .collectList())
		           .map(t -> new UserInformation(user.id(), user.username(), t.getT1(), t.getT2()));
	}

}
