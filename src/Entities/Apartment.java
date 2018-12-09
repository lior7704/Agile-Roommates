package Entities;

import java.util.ArrayList;
import java.util.List;

public class Apartment {
	private List<User> residents;
	private ShoppingList shoppingList;
	private MessagesList messages;
	private CashBox cashBox;
	
	public Apartment() {
		residents = new ArrayList<User>();
		shoppingList = new ShoppingList();
		messages = new MessagesList();
		cashBox = new CashBox();
	}
	
	public Apartment(List<User> residents) {
		this.residents = residents;
		this.shoppingList = new ShoppingList();
		this.messages = new MessagesList();
		this.cashBox = new CashBox();
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
}
