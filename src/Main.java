
public class Main {

	public static void main(String[] args) {
		
		User elad = new User("Elad", "0544556677", "elad.madar@gmail.com");
		User giron = new User("Giron", "0544556678", "giron.aptik@gmail.com");
		User nofar = new User("Nofar", "0544556679", "nofar.alfasi@gmail.com");
		User lior = new User("Lior", "0544556680", "lior.gal@gmail.com");
		User chen = new User("Chen", "0544556681", "chen.turgeman@gmail.com");

		ShoppingList sl = new ShoppingList();
		sl.add(new Product("apple", 7));
		sl.add(new Product("lemon", 3));
		sl.add(new Product("orange", 10));
		sl.add(new Product("milk", 5));
		sl.add(new Product("bread", 2));
		
		sl.toString();
		
		sl.removeProduct(2);
		System.out.println("\nafter remove(2):\n");
		sl.toString();
		
	}

}