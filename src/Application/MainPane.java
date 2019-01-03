package Application;

import java.io.FileNotFoundException;

import Entities.Apartment;
import Entities.User;
import GUI.AgileRoommatesFinals;
import GUI.AgileRoommatesPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPane extends Application implements AgileRoommatesFinals {
	private Apartment apartment;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		initApartment();
		AgileRoommatesPane pane =new  AgileRoommatesPane(apartment);
		Scene scene = new Scene(pane);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			try {
				apartment.saveToFile();
			} catch (FileNotFoundException e1) {
				System.out.println("Write apartment from file failed");
				e1.printStackTrace();
			}
		});
	}
	
	public void initApartment() {
		apartment = new Apartment();
		
		//apartment.addResident(new User(111, "eee", "0544556677", "elad.madar@gmail.com"));
		apartment.addResident(new User(1111, "Elad", "0544556677", "elad.madar@gmail.com"));
		apartment.addResident(new User(2222, "Giron", "0544556678", "giron.aptik@gmail.com"));
		apartment.addResident(new User(3333, "Nofar", "0544556679", "nofar.alfasi@gmail.com"));
		apartment.addResident(new User(4444, "Lior", "0544556680", "lior.gal@gmail.com"));
		apartment.addResident(new User(5555, "Chen", "0544556681", "chen.turgeman@gmail.com"));
	}
	
	public Apartment getApartment() {
		return apartment;
	}
	
}







