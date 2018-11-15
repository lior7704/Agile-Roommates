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
		for (Product product : products) {
			System.out.println(i+++". "+product);
		}
		return null;
	}
	
	public void add(Product p) {
		this.products.add(p);
	}
	
	public void removeProduct(int i) {
		this.products.remove(i-1);
		}
	
}
