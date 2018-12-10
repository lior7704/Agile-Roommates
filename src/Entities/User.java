package Entities;

import java.io.IOException;
import java.io.RandomAccessFile;

import GUI.AgileRoommatesFinals;
import GUI.FixedLengthStringIO;

public class User implements AgileRoommatesFinals{
	private int id=0;
	private String name;
	private String phoneNumber;
	private String email;

	public User(int id, String name, String phoneNumber, String email) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public User(RandomAccessFile rf){
		try {
			id = Integer.parseInt(FixedLengthStringIO.readFixedLengthString(SHORT_INT_SIZE, rf));
			name = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, rf);
			phoneNumber = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, rf);
			email = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, rf);
		} catch (NumberFormatException e) {
			System.out.println("Id conversion from string to int failed");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Reading of user failed");
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void checkName(String name) {
	    char[] chars = name.toCharArray();
	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	        	System.err.println("Invalid name");
	        }
	    }
	}
	
	
	public void checkPhoneNumber(String phoneNumber) {
		if (!phoneNumber.matches("[0-9]+") && phoneNumber.length() > 13) {
			System.err.println("Invalid phone number");
		}
	}
	
	public void checkEmail(String email) {
		if (email.contains("@") && !email.contains(".com")) {
			System.err.println("Invalid Email");
		}
	}

	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phoneNumber=" + phoneNumber + ", email=" + email
				 + "]";
	}

	public void writeUserToFile(RandomAccessFile rf) {
		try {
			FixedLengthStringIO.writeFixedLengthString(Integer.toString(id), SHORT_INT_SIZE, rf);
			FixedLengthStringIO.writeFixedLengthString(name, LONG_STRING_SIZE, rf);
			FixedLengthStringIO.writeFixedLengthString(phoneNumber, LONG_STRING_SIZE, rf);
			FixedLengthStringIO.writeFixedLengthString(email, LONG_STRING_SIZE, rf);
		} catch (IOException e) {
			System.out.println("Writing of this user failed: " + name);
			e.printStackTrace();
		}
		
	}
	
		
}