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
	private TextField jtfApartmentID = new TextField();
	private TextField jtfUserID = new TextField();
	private ShoppingListButton jbtShoppingList;
	private CashBoxButton jbtCashBox;
	private MessagesButton jbtMessages;
	private long state;
	private EventHandler<ActionEvent> myEvent = e -> ((Command) e.getSource()).OpenNewPane();

	public AgileRoommatesPane(Apartment apartment) {
		jbtShoppingList = new ShoppingListButton(apartment);
		jbtCashBox = new CashBoxButton(apartment);
		jbtMessages = new MessagesButton(apartment);
		Label apartID = new Label(APARTMENT_ID);
		Label userID = new Label(USER_ID);
		GridPane p1 = new GridPane();
		p1.add(apartID, 0, 0);
		p1.add(userID, 0, 1);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(apartID, Priority.ALWAYS);
		GridPane.setVgrow(userID, Priority.ALWAYS);
		GridPane adP = new GridPane();
		GridPane p4 = new GridPane();
		p4.add(jtfApartmentID, 0, 0);
		p4.add(jtfUserID, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);
		GridPane.setHgrow(jtfApartmentID, Priority.ALWAYS);
		GridPane.setHgrow(jtfUserID, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfApartmentID, Priority.ALWAYS);
		GridPane.setVgrow(jtfUserID, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);
		jpAddress.setStyle(STYLE_COMMAND);
		FlowPane jpButton = new FlowPane();
		jpButton.setHgap(5);
		if (eventType.SHOPPING_LIST.getDoEvent())
			jpButton.getChildren().add(jbtShoppingList);
		if (eventType.CASH_BOX.getDoEvent())
			jpButton.getChildren().add(jbtCashBox);
		if (eventType.MESSAGES.getDoEvent())
			jpButton.getChildren().add(jbtMessages);
		jpButton.setAlignment(Pos.CENTER);
		GridPane.setVgrow(jpButton, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(jpButton, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);
		GridPane myGridPane = new GridPane();
		myGridPane.setStyle(STYLE_COMMAND);
		myGridPane.setAlignment(Pos.CENTER);
		GridPane.setHgrow(myGridPane, Priority.ALWAYS);
		myGridPane.setPrefWidth(100);
		myGridPane.setHgap(20);
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(jpButton, 0, 1);
		this.add(myGridPane, 0, 2);
		jbtShoppingList.setOnAction(myEvent);
		jbtCashBox.setOnAction(myEvent);
		jbtMessages.setOnAction(myEvent);
		//jbtShoppingList.Execute();
	}

	public void SetApartmentID(String text) {
		jtfApartmentID.setText(text);
	}

	public void SetuserID(String text) {
		jtfUserID.setText(text);
	}

	public String GetApartmentID() {
		return jtfApartmentID.getText();
	}

	public String GetuserID() {
		return jtfUserID.getText();
	}

	public void clearTextFields() {
		jtfApartmentID.setText("");
		jtfUserID.setText("");
	}

	/******* Memento_Methods ********/
	public void setState(long state) {
		this.state = state;
	}

	public long getState() {
		return state;
	}

	public Memento saveStateToMemento() {
		return new Memento(state);
	}

	public void getStateFromMemento(Memento memento) {
		this.state = memento.getState();
	}

	/******* Memento_Class ********/
	class Memento {
		private long pos;

		public Memento(long pos) {
			this.pos = pos;
		}

		private long getState() {
			return pos;
		}
	}
}

