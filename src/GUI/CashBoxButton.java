package GUI;

import Entities.Apartment;

class CashBoxButton extends CommandButton {
	private Apartment apartment;

	public CashBoxButton(Apartment apartment) {
		super(apartment);
		this.setText(CASH_BOX);
		this.apartment = apartment;
	}

	@Override
	public void OpenNewPane() {
		new CashBoxPane(apartment, null);
	}
}

