package org.botos.sec04.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@Slf4j
public class FileReaderServiceImpl implements FileReaderService {

	@Override
	public Flux<String> read(Path path) {
		return Flux.generate(() -> openFile(path), this::readFile, this::closeFile);
	}

	private BufferedReader openFile(Path path) throws IOException {
		log.info("opening the file");
		return Files.newBufferedReader(path);
	}

	private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {
		try {
			var line = reader.readLine();
			log.info("reading line: {}", line);
			if (Objects.isNull(line)) {
				sink.complete();
			} else {
				sink.next(line);
			}
		} catch (IOException exception) {
			sink.error(exception);
		}
		return reader;
	}

	private void closeFile(BufferedReader reader) {
		try {
			reader.close();
			log.info("closed file");
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
