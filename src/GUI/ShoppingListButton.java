package GUI;

import Entities.Apartment;

class ShoppingListButton extends CommandButton {
	private Apartment apartment;
	
	public ShoppingListButton(Apartment apartment) {
		super(apartment);
		this.apartment = apartment;
		this.setText(SHOPPING_LIST);
	}

	@Override
	public void OpenNewPane() {
		new ShoppingListPane(apartment);
	}

}
