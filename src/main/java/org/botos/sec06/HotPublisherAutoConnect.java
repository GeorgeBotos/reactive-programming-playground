package org.botos.sec06;

import lombok.extern.slf4j.Slf4j;
import org.botos.sec06.util.Movie;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class HotPublisherAutoConnect {

	public static void main(String[] args) {

		var movieFlux = Movie.stream(log)
		                     .publish()
		                     .autoConnect(0);

		sleep(2);

		movieFlux.take(4)
		         .subscribe(subscriber("sam"));

		sleep(3);

		movieFlux.take(3)
		         .subscribe(subscriber("mike"));

		sleep(15);
	}
}
