package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

	private String content;
	private Date date;
	private User sender;

	public Message(String content, User sender) {
		super();
		this.content = content;
		this.date = new Date();
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public Date getDate() {
		return date;
	}

	public User getSender() {
		return sender;
	}

	@Override
	public String toString() {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String timeStamp = simpleDateFormat.format(date);
		return "\"" + content + "\"" + "\n" + timeStamp + "\nBy: " + sender;
	}
}
