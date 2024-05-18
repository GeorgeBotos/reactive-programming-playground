package org.botos.sec03.assignment;

import static org.botos.common.Util.sleep;

public class Assignment {

	public static void main(String[] args) {

		var stockClient = new StockClient();
		var stockSubscriber = new StockSubscriber();

		stockClient.getPrices()
		           .log()
		           .subscribe(stockSubscriber);

		sleep(22);
	}
}
