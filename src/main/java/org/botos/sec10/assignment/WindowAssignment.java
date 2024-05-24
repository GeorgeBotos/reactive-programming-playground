package org.botos.sec10.assignment;

import org.botos.sec10.assignment.window.FileWriter;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static org.botos.common.Util.sleep;

public class WindowAssignment {

	public static void main(String[] args) {

		var counter = new AtomicInteger(0);
		var fileNameFormat = "src/main/resources/sec10/file%d.txt";

		eventStream().window(Duration.ofMillis(1800))
		             .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
		             .subscribe();

		sleep(60);
	}


	private static Flux<String> eventStream() {
		return Flux.interval(Duration.ofMillis(500))
		           .map("event-%s"::formatted);
	}
}

