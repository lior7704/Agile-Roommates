package GUI;

import java.io.FileNotFoundException;
import java.io.IOException;

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
		/*
		try {
			new MessageListPane(apartment, currentUser);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
}
