package GUI;

import Entities.Apartment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AgileRoommatesPane extends GridPane implements AgileRoommatesFinals, AgileRoommatesEvent {
	private TextField jtfUserName = new TextField();
	private TextField jtfUserID = new TextField();
	private Label userNameLabel = new Label(USER_NAME);
	private Label userIDLabel = new Label(USER_ID);
	
	private ShoppingListButton jbtShoppingList;
	private CashBoxButton jbtCashBox;
	private MessagesButton jbtMessages;
	
	private EventHandler<ActionEvent> myEvent = e -> ((Command) e.getSource()).OpenNewPane();

	public AgileRoommatesPane(Apartment apartment) {
		jbtShoppingList = new ShoppingListButton(apartment);
		jbtCashBox = new CashBoxButton(apartment);
		jbtMessages = new MessagesButton(apartment);
		GridPane loginPane = new GridPane();
		FlowPane buttonsPane = new FlowPane();
		
		loginPane.add(userNameLabel, 0, 0);
		loginPane.add(userIDLabel, 0, 1);
		loginPane.add(jtfUserName, 1, 0);
		loginPane.add(jtfUserID, 1, 1);
		
		loginPane.setAlignment(Pos.CENTER_LEFT);
		loginPane.setVgap(8);
		loginPane.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(userNameLabel, Priority.ALWAYS);
		GridPane.setVgrow(userIDLabel, Priority.ALWAYS);
		
		buttonsPane.setVgap(1);
		GridPane.setHgrow(jtfUserName, Priority.ALWAYS);
		GridPane.setHgrow(jtfUserID, Priority.ALWAYS);
	
		GridPane unitedPane = new GridPane();
		unitedPane.add(loginPane, 0, 0);
		unitedPane.add(buttonsPane, 1, 0);
		GridPane.setHgrow(loginPane, Priority.NEVER);
		GridPane.setHgrow(buttonsPane, Priority.ALWAYS);
		GridPane.setVgrow(loginPane, Priority.ALWAYS);
		GridPane.setVgrow(buttonsPane, Priority.ALWAYS);
		unitedPane.setStyle(STYLE_COMMAND);
		
		buttonsPane.setHgap(5);
		if (eventType.SHOPPING_LIST.getDoEvent())
			buttonsPane.getChildren().add(jbtShoppingList);
		if (eventType.CASH_BOX.getDoEvent())
			buttonsPane.getChildren().add(jbtCashBox);
		if (eventType.MESSAGES.getDoEvent())
			buttonsPane.getChildren().add(jbtMessages);
		buttonsPane.setAlignment(Pos.CENTER);
		
		GridPane.setVgrow(buttonsPane, Priority.NEVER);
		GridPane.setVgrow(unitedPane, Priority.ALWAYS);
		GridPane.setHgrow(buttonsPane, Priority.ALWAYS);
		GridPane.setHgrow(unitedPane, Priority.ALWAYS);
		
		this.setVgap(5);
		this.add(unitedPane, 0, 0);
		this.add(buttonsPane, 0, 1);

		jbtShoppingList.setOnAction(myEvent);
		jbtCashBox.setOnAction(myEvent);
		jbtMessages.setOnAction(myEvent);
	}

	public void SetApartmentID(String text) {
		jtfUserName.setText(text);
	}

	public void SetuserID(String text) {
		jtfUserID.setText(text);
	}

	public String GetApartmentID() {
		return jtfUserName.getText();
	}

	public String GetuserID() {
		return jtfUserID.getText();
	}

	public void clearTextFields() {
		jtfUserName.setText("");
		jtfUserID.setText("");
	}
}

