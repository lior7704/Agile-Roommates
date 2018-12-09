package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

	private String content;
	private String date;
	private User sender;

	public Message(String content, User sender) {
		super();
		this.content = content;
		this.date = new Date().toString();
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public String getDate() {
		return date;
	}

	public User getSender() {
		return sender;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String timeStamp = simpleDateFormat.format(date);
		return "\"" + content + "\"" + "\n" + timeStamp + "\nBy: " + sender;
	}
}
