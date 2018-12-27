package Entities;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import GUI.AgileRoommatesFinals;
import GUI.FixedLengthStringIO;

public class MessagesList implements AgileRoommatesFinals {
	private ArrayList<Message> messages = null;

	public MessagesList() {
		this.messages = new ArrayList<Message>();
	}

	public void addMessage(int index, Message m) {
		this.messages.add(index, m);
		;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void writeMessagesListToFile(RandomAccessFile rf) {
		try {
			rf.setLength(0);
			rf.seek(0);
			rf.writeInt(messages.size());
			for (int i = 0; i < messages.size(); i++) {
				FixedLengthStringIO.writeFixedLengthString(messages.get(i).getContent(), LONG_STRING_SIZE, rf);
				FixedLengthStringIO.writeFixedLengthString(messages.get(i).getDate(), LONG_STRING_SIZE, rf);
				FixedLengthStringIO.writeFixedLengthString(messages.get(i).getSender(), LONG_STRING_SIZE, rf);
			}
			rf.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readMessagesListFromFile(RandomAccessFile rf) throws IOException {
		rf.seek(0);
		int size = rf.readInt();
		for (int i = 0; i < size; i++) {
			String content = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, rf);
			String date = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, rf);
			String name = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, rf);
			addMessage(messages.size(), new Message(content.trim(), name));
			getMessages().get(i).setDate(date);
		}
	}
}
