package Entities;

public class User {
	
	private int id=0;
	private String name;
	private String phoneNumber;
	private String email;

	//const':
	public User(int id, String name, String phoneNumber, String email) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	//getters & setters:
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
	
		
}