package Entities;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import GUI.AgileRoommatesFinals;
import GUI.FixedLengthStringIO;

public class ShoppingList implements AgileRoommatesFinals{
	private List<Product> products = null;
	
	public ShoppingList() {
		this.products = new ArrayList<Product>();
	}
	
	@Override
	public String toString() {
		int i = 1;
		String s = "We need to buy:\n\n"; 
		for (Product product : products) {
			s = s + ((i++) + ". " + product +"\n");
		}
		return s;
	}
	
	public void addProduct(Product p) {
		for (Product product : products) {
			if(product.getNameOfProduct().equals(p.getNameOfProduct())) {
				product.setAmount(product.getAmount() + p.getAmount());
				return;
			}
		}
		this.products.add(p);
	}
	
	public void show() {
		System.out.println(toString());
	}

	public int getAmountOfItems() {
		return products.size();
	}

	public List<Product> getProducts() {
		return products;
	}

	public void remove(Product selectedItem) {
		products.remove(selectedItem);
		
	}
	
	public void writeShoppingListToFile(RandomAccessFile rf) {
		try {
			rf.setLength(0);
			rf.seek(0);
			rf.writeInt(products.size());
			for (int i = 0; i < products.size(); i++) {
				FixedLengthStringIO.writeFixedLengthString(products.get(i).getNameOfProduct(),
						SHORT_STRING_SIZE, rf);
				rf.writeInt(products.get(i).getAmount());
			}
			rf.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readShoppingListFromFile(RandomAccessFile rf) throws IOException {
		rf.seek(0);
		int size = rf.readInt();
		for (int i = 0; i < size; i++) {
			String name = FixedLengthStringIO.readFixedLengthString(SHORT_STRING_SIZE, rf);
			int amount = rf.readInt();
			addProduct(new Product(name.trim().toLowerCase(), amount));
		}
	}
}
