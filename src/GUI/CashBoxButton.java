package GUI;

import Entities.Apartment;
import Entities.User;

public class CashBoxButton extends CommandButton {

	public CashBoxButton(Apartment apartment, User currentUser) {
		super(apartment, currentUser);
		this.setText(CASH_BOX);
	}

	@Override
	public void OpenNewPane(Apartment apartment, User currentUser) throws Exception {
		new CashBoxPane(apartment, currentUser);
		/*try {
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}

