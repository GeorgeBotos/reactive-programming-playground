package org.botos.sec09;

import org.botos.sec09.helper.NameGenerator;

import static org.botos.common.Util.subscriber;

public class StartWithUseCase {

	public static void main(String[] args) {

		var nameGenerator = new NameGenerator();

		nameGenerator.generateNames()
		             .take(2)
		             .subscribe(subscriber("sam"));

		nameGenerator.generateNames()
		             .take(2)
		             .subscribe(subscriber("mike"));

		nameGenerator.generateNames()
		             .take(3)
		             .subscribe(subscriber("jake"));
	}
}
