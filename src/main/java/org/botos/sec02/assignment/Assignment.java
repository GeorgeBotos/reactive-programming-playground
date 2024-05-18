package org.botos.sec02.assignment;

import static org.botos.common.Util.subscriber;

public class Assignment {

	public static void main(String[] args) {

		var fileService = new FileServiceImpl();
		var fileName = "file.txt";

		fileService.write(fileName, "This is the content of the file.")
		           .subscribe(subscriber());

		fileService.read(fileName)
		           .subscribe(subscriber());

		fileService.delete(fileName)
		           .subscribe(subscriber());
	}
}
