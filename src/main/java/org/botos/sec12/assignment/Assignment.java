package org.botos.sec12.assignment;

import static org.botos.common.Util.sleep;

public class Assignment {

	public static void main(String[] args) {
		var room = new ChatRoom("Chatroom");
		var sam = new Member("sam");
		var jake = new Member("jake");
		var mike = new Member("mike");

		room.addMember(sam);
		room.addMember(jake);

		sam.says("Hi all..");
		sleep(4);

		jake.says("Hey!");
		sam.says("I simply wanted to say hi..");
		sleep(4);

		room.addMember(mike);
		mike.says("Hey guys.. glad to be here...");
	}
}
