package Entities;

import java.util.ArrayList;
import java.util.List;

public class Apartment {
	private List<User> residents;
	private ShoppingList shoppingList;
	private MessageList messages;
	private CashBox cashBox;
	
	public Apartment() {
		residents = new ArrayList<User>();
		shoppingList = new ShoppingList();
		messages = new MessageList();
		cashBox = new CashBox();
	}
	
	public Apartment(List<User> residents) {
		super();
		this.residents = residents;
		this.shoppingList = new ShoppingList();
		this.messages = new MessageList();
		this.cashBox = new CashBox();
	}

	public List<User> getResidents() {
		return residents;
	}

	public ShoppingList getShoppingList() {
		return shoppingList;
	}
	
	public MessageList getMessages() {
		return messages;
	}

	public CashBox getCashBox() {
		return cashBox;
	}

	public void setCashBox(CashBox cashBox) {
		this.cashBox = cashBox;
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

	private boolean alreadyResident(int id) {
		for (User user : residents) {
			if(user.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
