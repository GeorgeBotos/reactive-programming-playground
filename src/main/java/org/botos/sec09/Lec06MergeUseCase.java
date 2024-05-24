package org.botos.sec09;

import org.botos.sec09.helper.Kayak;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class Lec06MergeUseCase {

	public static void main(String[] args) {
		Kayak.getFlights()
		     .subscribe(subscriber());

		sleep(4);
	}
}
