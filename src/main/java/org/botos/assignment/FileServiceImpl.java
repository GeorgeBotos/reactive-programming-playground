package org.botos.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileServiceImpl implements FileService{

	private static final Path PATH = Path.of("src/main/resources/sec02");

	@Override
	public Mono<String> read(String fileName) {
		return Mono.fromCallable(() -> Files.readString(PATH.resolve(fileName)));
	}

	@Override
	public Mono<Void> write(String fileName, String content) {
		return Mono.fromRunnable(() -> writeFile(fileName, content));
	}

	@Override
	public Mono<Void> delete(String fileName) {
		return Mono.fromRunnable(() -> deleteFile(fileName));
	}

	private void writeFile(String fileName, String content) {
		try {
			Files.writeString(PATH.resolve(fileName), content);
			log.info("created {}", fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void deleteFile(String fileName) {
		try {
			Files.delete(PATH.resolve(fileName));
			log.info("deleted {}", fileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
