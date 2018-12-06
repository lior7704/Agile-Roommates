package GUI;

import Entities.Apartment;
import javafx.scene.control.Button;

class CommandButton extends Button implements Command, AgileRoommatesFinals {
	private Apartment apartment;

	public CommandButton(Apartment apartment) {
		super();
		this.apartment = apartment;
	}

	@Override
	public void OpenNewPane() {
		// TODO Auto-generated method stub
		
	}
}