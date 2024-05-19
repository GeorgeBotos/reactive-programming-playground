package org.botos.sec06;

import lombok.extern.slf4j.Slf4j;
import org.botos.sec06.util.Movie;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

/*
	HotPublisher ==> The same data stream for all subscribers.
	share() ==> same as publish.refCount(1), i.e. it needs at least one
				subscriber in order to emit the data, and stops producing
				if there is less than 1 subscriber.
 */
@Slf4j
public class HotPublisher {

	public static void main(String[] args) {

		var movieFlux = Movie.stream(log).share();
//		var movieFlux = movieStream().publish()
//		                             .refCount(2);

		sleep(2);

		movieFlux.take(4)
		         .subscribe(subscriber("sam"));

		sleep(3);

		movieFlux.take(3)
		         .subscribe(subscriber("mike"));

		sleep(15);
	}

}
