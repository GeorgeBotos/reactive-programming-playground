package org.botos.sec10.assignment.window;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RequiredArgsConstructor
public class FileWriter {

	private final Path path;
	private BufferedWriter writer;

	private void createFile() {
		try {
			writer = Files.newBufferedWriter(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void closeFile() {
		try {
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void write(String content) {
		try {
			writer.write(content);
			writer.newLine();
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Mono<Void> create(Flux<String> flux, Path path) {
		var writer = new FileWriter(path);
		return flux.doOnNext(writer::write)
		           .doFirst(writer::createFile)
		           .doFinally(string -> writer.closeFile())
		           .then();

	}
}
