package Application;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Entities.Apartment;
import Entities.Product;
import Entities.User;

public class MainMenu {

	public static void main(String[] args) {
		int choice;
		Scanner scan = new Scanner(System.in);
		User elad = new User(1111, "Elad", "0544556677", "elad.madar@gmail.com");
		System.out.println(elad.getId());
		System.out.println("Populating Apartment...");
		Apartment a = initApartment();
		a.addResident(elad);
		for (int i = 0; i < a.getResidents().size(); i++) {
			System.out.println(a.getResidents().get(i).toString());
		}
		
		System.out.println("Adding groceries...");
		a.getShoppingList().addProduct(new Product("apple", 7));
		a.getShoppingList().addProduct(new Product("lemon", 3));
		a.getShoppingList().addProduct(new Product("orange", 10));
		a.getShoppingList().addProduct(new Product("milk", 5));
		a.getShoppingList().addProduct(new Product("bread", 2));
		
		do{
			choice = optionSelector(scan);
			switch (choice){
			case 1:
				//a.showShoppingList();
				break;
			
			case 2:
				Product p = getProductToAddFromUser(scan);
				if(p == null) 
					break;
				a.getShoppingList().addProduct(p);
				break;
			
			case 3:
				int num = getIdToRemoveFromUser(scan, a);
				if(checkExit(num)) {
					System.out.println("Removal canceled");
					break;
				}
				else
					//a.getShoppingList().removeProductFromList(num);
				break;
				
			case 4:
				System.out.println("\t Bye Bye :) Hope To See You Again!\n");
				scan.close();
				break;
			}
		}while(choice != 4);
		
	}

	//This function will show the menu to the user and will Receive the user Option and return it	
	private static int optionSelector(Scanner scan) {
		int option;

		System.out.println("\n\t-->> Welcome to the Roommate Shopping List App <<--\n\n");
		System.out.println("Choose one of the following:\n");
		System.out.println("1)  Show the shopping list\n");
		System.out.println("2)  Add product to the list\n");
		System.out.println("3)  Remove product from the list\n");
		System.out.println("4)  Exit\n\n");

		do {
			option = scan.nextInt();
			scan.nextLine();
			if (option < 1 || option > 4)
				System.out.println("Invalid choice. Try again. \n");

		} while (option < 1 || option > 4); //'do-while' for the input testing
		
		return option;
	}
	
	private static Apartment initApartment() {
		User elad = new User(1111, "Elad", "0544556677", "elad.madar@gmail.com");
		User giron = new User(2222, "Giron", "0544556678", "giron.aptik@gmail.com");
		User nofar = new User(3333, "Nofar", "0544556679", "nofar.alfasi@gmail.com");
		User lior = new User(4444, "Lior", "0544556680", "lior.gal@gmail.com");
		User chen = new User(5555, "Chen", "0544556681", "chen.turgeman@gmail.com");
		

		List<User> roommates = new ArrayList<User>();
		
		roommates.add(elad);
		roommates.add(giron);
		roommates.add(nofar);
		roommates.add(lior);
		roommates.add(chen);
		return new Apartment(roommates);
	}
	
	private static Product getProductToAddFromUser(Scanner scan) {
		String nameOfProduct = null;
		int amount = 0;
		Product p;
		boolean validFlag = true;
		do {
			if(validFlag)
				System.out.println("Enter product name or 0 to exit:");
			else
				System.out.println("Invalid name, try again:");
			nameOfProduct = scan.nextLine();
			if(checkExit(nameOfProduct))
				return null;
			validFlag = false;
		} while(checkName(nameOfProduct));
		validFlag = true;
		
		do {
			if(validFlag)
				System.out.println("Enter the amount (a number only), 0 to exit:");
			else
				System.out.println("Invalid amount, try again:");
			amount = scan.nextInt();
			scan.nextLine();
			if(checkExit(amount))
				return null;
			validFlag = false;
		} while(amount <= 0);
		p = new Product(nameOfProduct, amount);
		return p;
	}
	
	private static boolean checkName(String name) {
		if(name == null)
			return true;
	    char[] chars = name.toCharArray();
	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	        	return true;
	        }
	    }
	    return false;
	}
	
	private static int getIdToRemoveFromUser(Scanner scan, Apartment a) {
		int serialNum = 0;
		boolean validFlag = true;
		do {
			if(validFlag)
				System.out.println("Enter the serial number of the product you wish to remove, 0 to exit:");
			else
				System.out.println("Invalid item number, try again:");
			serialNum = scan.nextInt();
			scan.nextLine();
			if(checkExit(serialNum))
				return 0;
			validFlag = false;
		} while(serialNum < 0 || serialNum > a.getShoppingList().getAmountOfItems());
		
		return serialNum;
	}
	
	private static boolean checkExit(int num) {
		return num == 0;
	}
	
	private static boolean checkExit(String s) {
		return s.equals("0");
	}
}