

public class User {
	
	private static int id=0;
	private String name;
	private String phoneNumber;
	private String email;
	//private Apartment theApartment
	
	//const':
	public User(String name, String phoneNumber, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	//setter+getter:
	public static int getId() {
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
	        	System.err.println("ahah");
	        }
	    }
	}
	
		
}