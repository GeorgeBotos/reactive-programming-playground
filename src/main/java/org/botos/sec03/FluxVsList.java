package org.botos.sec03;

import org.botos.sec01.subscriber.SubscriberImpl;
import org.botos.sec03.helper.NameGenerator;

public class FluxVsList {

	public static void main(String[] args) {

//		var names = NameGenerator.generateNames(10);
//		log.info(names.toString());

		var subscriber = new SubscriberImpl();
		NameGenerator.generateNameFlux(10)
		             .subscribe(subscriber);

		subscriber.getSubscription()
		          .request(3);
		subscriber.getSubscription()
		          .cancel(); // I can stop early if one of the item in the flux is what I need.
	}
}
