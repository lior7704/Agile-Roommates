package GUI;

import Entities.Apartment;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class MessagesButton extends CommandButton {
	private Apartment apartment;
	
	public MessagesButton(Apartment apartment) {
		super(apartment);
		this.setText(MESSAGES);
		this.apartment = apartment;
	}

	@Override
	public void OpenNewPane() {
		new MessageListPane(apartment);
	}
}
