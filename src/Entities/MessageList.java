package Entities;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
	private List<Message> messages = null;
	
	public MessageList() {
		this.messages = new ArrayList<Message>();
	}
	
	public void addMessage(Message m) {
		this.messages.add(m);
	}

	public List<Message> getMessages() {
		return messages;
	}
}
