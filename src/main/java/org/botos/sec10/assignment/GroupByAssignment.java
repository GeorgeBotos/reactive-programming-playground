package org.botos.sec10.assignment;

import org.botos.sec10.assignment.groupby.OrderService;
import org.botos.sec10.assignment.groupby.PurchaseOrder;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

public class GroupByAssignment {

	public static void main(String[] args) {

		OrderService.gerOrders()
		            .filter(OrderService.canProcess())
		            .groupBy(PurchaseOrder::category)
		            .flatMap(groupedFlux -> groupedFlux.transform(OrderService.getProcessor(groupedFlux.key())))
		            .subscribe(subscriber());

		sleep(60);
	}
}
