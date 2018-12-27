package Entities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import GUI.AgileRoommatesFinals;

public class Apartment implements AgileRoommatesFinals{
	private List<User> residents;
	private ShoppingList shoppingList;
	private MessagesList messages;
	private CashBox cashBox;
	
	public Apartment() {
		residents = new ArrayList<User>();
		shoppingList = new ShoppingList();
		messages = new MessagesList();
		cashBox = new CashBox(residents);

	}
	
	public Apartment(List<User> residents) {
		this.residents = residents;
		this.shoppingList = new ShoppingList();
		this.messages = new MessagesList();
		this.cashBox = new CashBox(residents);

	}

	public List<User> getResidents() {
		return residents;
	}

	public ShoppingList getShoppingList() {
		return shoppingList;
	}
	
	public MessagesList getMessagesList() {
		return messages;
	}

	public CashBox getCashBox() {
		return cashBox;
	}

	public void setCashBox(CashBox cashBox) {
		this.cashBox = cashBox;
	}

	public void setResidents(List<User> residents) {
		this.residents = residents;
	}

	public void setShoppingList(ShoppingList shoppingList) {
		this.shoppingList = shoppingList;
	}

	public void setMessages(MessagesList messages) {
		this.messages = messages;
	}

	public boolean addResident(User newGuy) {
		if(alreadyResident(newGuy.getId())) {
			return false;
		}
		else {
			residents.add(newGuy);
			return true;
		}
	}

	public boolean alreadyResident(int id) {
		for (User user : residents) {
			if(user.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public User getUserOnResidentsListByID(int id) {
		for (User user : residents) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public void saveToFile() throws FileNotFoundException {
		RandomAccessFile rfShoppingList = new RandomAccessFile(SHOPPING_LIST_FILE, FILE_MODE);
		shoppingList.writeShoppingListToFile(rfShoppingList);
		
		RandomAccessFile rfCashBox = new RandomAccessFile(CASHBOX_FILE, FILE_MODE);
		cashBox.writeCashBoxToFile(rfCashBox);
		
		RandomAccessFile rfMessagesList = new RandomAccessFile(MESSAGES_LIST_FILE, FILE_MODE);
		messages.writeMessagesListToFile(rfMessagesList);
	}

	public void readApartmentFromFile() throws IOException {
		this.shoppingList = new ShoppingList();
		RandomAccessFile rfShoppingList = new RandomAccessFile(SHOPPING_LIST_FILE, FILE_MODE);
		if(rfShoppingList.length() > 1)
			shoppingList.readShoppingListFromFile(rfShoppingList);
		
		this.cashBox = new CashBox(residents);
		RandomAccessFile rfCashBox = new RandomAccessFile(CASHBOX_FILE, FILE_MODE);
		if(rfCashBox.length() > 1)
			cashBox.readCashBoxFromFile(rfCashBox, residents);
		
		RandomAccessFile rfMessagesList = new RandomAccessFile(MESSAGES_LIST_FILE, FILE_MODE);
		messages.readMessagesListFromFile(rfMessagesList);
		
	}
}
