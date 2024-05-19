package org.botos.sec04;

import lombok.extern.slf4j.Slf4j;
import org.botos.sec04.helper.NameGenerator;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

import static org.botos.common.Util.sleep;

@Slf4j
public class FluxSinkThreadSafety {

	public static void main(String[] args) {

		demo2();
	}

	private static void demo1() {
		var list = new ArrayList<Integer>();
		Runnable runnable = () -> {
			for (int i = 0; i < 1_000; i++) {
				list.add(i);
			}
		};

		for (int i = 0; i < 10; i++) {
			Thread.ofPlatform()
			      .start(runnable);
		}
		sleep(3);
		log.info("list size: {}", list.size());
	}

	private static void demo2() {
		var list = new ArrayList<String>();
		var generator = new NameGenerator();
		var flux = Flux.create(generator);
		flux.subscribe(list::add);

		Runnable runnable = () -> {
			for (int i = 0; i < 1_000; i++) {
				generator.generate();
			}
		};

		for (int i = 0; i < 10; i++) {
			Thread.ofPlatform()
			      .start(runnable);
		}

		sleep(3);
		log.info("list size: {}", list.size());


	}
}
