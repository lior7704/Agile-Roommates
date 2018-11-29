package Entities;

import java.util.List;

public class Apartment {
	private List<User> residents;
	private ShoppingList shoppingList;
	
	
	public Apartment(List<User> residents, ShoppingList shoppingList) {
		super();
		this.residents = residents;
		this.shoppingList = shoppingList;
	}
	
	public Apartment(List<User> residents) {
		super();
		this.residents = residents;
		this.shoppingList = new ShoppingList();
	}

	public List<User> getResidents() {
		return residents;
	}

	public ShoppingList getShoppingList() {
		return shoppingList;
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
	
	public void showShoppingList() {
		shoppingList.show();
	}

	public void addProductToList(Product p) {
		shoppingList.addProduct(p);	
	}

	public void removeProductFromList(int i) {
		shoppingList.removeProduct(i);
	}

	public int getAmountOfItemsInShoppingList() {
		return shoppingList.getAmountOfItems();
	}
	
}
