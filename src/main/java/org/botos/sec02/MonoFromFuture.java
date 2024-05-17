package org.botos.sec02;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

import static org.botos.common.Util.faker;
import static org.botos.common.Util.sleep;
import static org.botos.common.Util.subscriber;

@Slf4j
public class MonoFromFuture {

	public static void main(String[] args) throws InterruptedException {

		Mono.fromFuture(getName())
		    .subscribe(subscriber());

		sleep(1);

		Mono.fromFuture(getName()); // This will print the name, even though no subscriber added. In reactive everything should be lazy.
		Mono.fromFuture(() -> getName()); // This will not print the name or "generating name" message, because it's only a method reference.
	}

	// CompletableFuture is not lazy, it's eager, hence the name is printed, even though no subscriber was added.
	private static CompletableFuture<String> getName() {
		return CompletableFuture.supplyAsync(() -> {
			log.info("generating name");
			return faker().name()
			              .firstName();
		});
	}
}
