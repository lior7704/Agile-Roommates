package GUI;

import Entities.Apartment;
import Entities.User;

public class MessagesButton extends CommandButton {

	public MessagesButton(Apartment apartment, User currentUser) {
		super(apartment, currentUser);
		this.setText(MESSAGES);
	}

	@Override
	public void OpenNewPane(Apartment apartment, User currentUser) throws Exception {
		new MessageListPane(apartment, currentUser);
	}
}
