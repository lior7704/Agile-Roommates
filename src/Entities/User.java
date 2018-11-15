package Entities;


public class User {
	
	private int id=0;
	private String name;
	private String phoneNumber;
	private String email;
	private Apartment theApartment;


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
	
	public Apartment getTheApartment() {
		return theApartment;
	}

	public void setTheApartment(Apartment theApartment) {
		this.theApartment = theApartment;
	}
	
	public void checkName(String name) {
	    char[] chars = name.toCharArray();
	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	        	System.err.println("Invalid name");
	        }
	    }
	}
	
		
}