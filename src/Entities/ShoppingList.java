package Entities;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
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
				System.out.println(product.getAmount());
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
}
