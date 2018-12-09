package Entities;

import java.util.ArrayList;
import java.util.List;

public class MessagesList {
	private ArrayList<Message> messages = null;
	
	public MessagesList() {
		this.messages = new ArrayList<Message>();
	}
	
	public void addMessage(Message m) {
		this.messages.add(m);
	}

	public List<Message> getMessages() {
		return messages;
	}
}
