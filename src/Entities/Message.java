package Entities;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

	private String content;
	private String date;
	private String sender;

	public Message(String content, String sender) {
		super();
		this.content = content;
		Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStamp = formatter.format(new Date());
		this.date = timeStamp;
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public String getDate() {
		return date;
	}

	public String getSender() {
		return sender;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "\"" + content + "\"" + "\n" + date + "\nBy: " + sender;
	}
}
