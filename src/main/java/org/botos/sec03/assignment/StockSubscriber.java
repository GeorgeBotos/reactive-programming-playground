package org.botos.sec03.assignment;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class StockSubscriber implements Subscriber<Integer> {

	private Subscription subscription;
	private int balance = 1_000;
	private int stock;

	@Override
	public void onSubscribe(Subscription subscription) {
		log.info("subscribed for stock service");
		this.subscription = subscription;
		this.subscription.request(Long.MAX_VALUE);
	}

	@Override
	public void onNext(Integer price) {
		if(price < 90 && balance > price) {
			buyStock(price);
		} else if(price > 110) {
			sellStock(price);
			subscription.cancel();
		}
	}

	@Override
	public void onError(Throwable throwable) {
		log.error("an error occurred: {}", throwable.getMessage());
	}

	@Override
	public void onComplete() {
		log.info("Completed with stock: {} and balance: {}", stock, balance);
	}

	private void buyStock(int price) {
		balance -= price;
		stock++;
		log.info("purchase of new stock at price {}-> balance: {}, stock: {}", price, balance, stock);
	}

	private void sellStock(int price) {
		log.info("selling stock: {} at price: {}", stock, price);
		var income = price * stock;
		stock = 0;
		balance += income;
		var profit = balance - 1_000;
		log.info("profit achieved: {}", profit);
	}
}
