package org.botos.sec12.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
public class ChatRoom {

	private final String name;
	private final Sinks.Many<Message> sink;
	private final Flux<Message> flux;

	public ChatRoom(String name) {
		this.name = name;
		this.sink = Sinks.many()
		                 .replay()
		                 .all();
		this.flux = sink.asFlux();
	}

	public void addMember(Member member) {
		log.info("{} joined the room {}", member.getName(), name);
		subscribeToRoomMessages(member);
		member.setMessageConsumer(message -> postMessage(member.getName(), message));
	}

	private void subscribeToRoomMessages(Member member) {
		flux.filter(message -> !message.sender()
		                               .equals(member.getName()))
		    .map(message -> message.formatForDelivery(member.getName()))
		    .subscribe(member::receives);
	}

	private void postMessage(String sender, String message) {
		sink.tryEmitNext(new Message(sender, message));
	}
}
