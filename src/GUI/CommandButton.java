package GUI;

import Entities.Apartment;
import Entities.User;
import javafx.scene.control.Button;

public class CommandButton extends Button implements Command, AgileRoommatesFinals {
	protected Apartment apartment;
	protected User currentUser;

	public CommandButton(Apartment apartment, User currentUser) {
		this.apartment = apartment;
		this.currentUser = currentUser;
	}

	public Apartment getApartment() {
		return apartment;
	}

	@Override
	public void OpenNewPane(Apartment apartment, User currentUser) throws Exception {
		// TODO Auto-generated method stub
		
	}

}