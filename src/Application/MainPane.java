package Application;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import Entities.Apartment;
import Entities.Product;
import Entities.User;
import GUI.AgileRoommatesFinals;
import GUI.AgileRoommatesPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPane extends Application implements AgileRoommatesFinals {
	
	private Apartment apartment;
	
	public Apartment getApartment() {
		return apartment;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initApartment();
		AgileRoommatesPane pane =new  AgileRoommatesPane(apartment);
		Scene scene = new Scene(pane);
		// scene.getStylesheets().add(STYLES_CSS);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setOnCloseRequest(e -> {
			RandomAccessFile f;
			try {
				f = new RandomAccessFile("count.dat", FILE_MODE);
				int n = f.readInt();
				f.setLength(0);
				f.writeInt(n - 1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
	
	private void initApartment() {
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
		apartment = new Apartment(roommates);
		
		apartment.getShoppingList().addProduct(new Product("apple", 7));
		apartment.getShoppingList().addProduct(new Product("lemon", 3));
		apartment.getShoppingList().addProduct(new Product("orange", 10));
		apartment.getShoppingList().addProduct(new Product("milk", 5));
		apartment.getShoppingList().addProduct(new Product("bread", 2));
	}
}







