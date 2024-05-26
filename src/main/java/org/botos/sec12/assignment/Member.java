package org.botos.sec12.assignment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
public class Member {

	@Getter
	private final String name;

	@Setter
	private Consumer<String> messageConsumer;

	public void says(String message) {
		messageConsumer.accept(message);
	}

	public void receives(String message) {
		log.info(message);
	}
}
