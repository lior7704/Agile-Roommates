package GUI;

import Entities.Apartment;
import Entities.User;

public class ShoppingListButton extends CommandButton {
	
	public ShoppingListButton(Apartment apartment, User currentUser) {
		super(apartment, currentUser);
		this.setText(SHOPPING_LIST);
	}

	@Override
	public void OpenNewPane(Apartment a, User user) throws Exception {	
			new ShoppingListPane(a);
		
	}

}
