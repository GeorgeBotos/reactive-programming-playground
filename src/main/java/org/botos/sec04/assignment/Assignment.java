package org.botos.sec04.assignment;

import java.nio.file.Path;

import static org.botos.common.Util.subscriber;

public class Assignment {

	public static void main(String[] args) {

		var path = Path.of("src/main/resources/sec04/file.txt");
		var fileReaderService = new FileReaderServiceImpl();
		fileReaderService.read(path)
		                 .take(6)
		                 .subscribe(subscriber());
	}
}
