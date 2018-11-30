package Application;

public interface AgileRoommatesFinals {
	final static String TITLE = "Agile-Roommates";
	final static String STYLES_CSS = "styles.css";
	final static String FILE_NAME = "roomates.dat";
	final static String FILE_MODE = "rw";
	final static String SHOPPING_LIST = "Shopping List"; // FIRST 
	final static String CASH_BOX = "Cash Box"; // NEXT
	final static String MESSAGES = "Messages"; // PREVIOUS 
	final static String APARTMENT_ID = "Apartment ID"; // NAME 
	final static String USER_ID = "User ID"; // STREET 
	final static int NUMBER_OF_PANES = 2;
	final static int APARTMENT_ID_SIZE = 32; // NAME_SIZE 
	final static int USER_ID_SIZE = 32;
	final static int RECORD_SIZE = (APARTMENT_ID_SIZE + USER_ID_SIZE); 
	final static String STYLE_COMMAND = "-fx-border-color: grey;" + " -fx-border-width: 1;"
			+ " -fx-border-style: solid outside ;";
}