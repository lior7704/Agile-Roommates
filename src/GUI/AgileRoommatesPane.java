package GUI;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entities.Apartment;
import Entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class AgileRoommatesPane extends GridPane implements AgileRoommatesFinals, AgileRoommatesEvent {
	private TextField userNameTextField = new TextField();
	private TextField userIDTextField = new TextField();
	private Label userNameLabel = new Label(USER_NAME);
	private Label userIDLabel = new Label(USER_ID);
	private ShoppingListButton shoppingListButton;
	private CashBoxButton cashBoxButton;
	private MessagesButton messagesButton;
	private Button loginButton;
	private GridPane loginPane = new GridPane();
	private GridPane welcomePane = new GridPane();
	private FlowPane buttonsPane = new FlowPane();
	private User currentUser = null;
	private Apartment apartment = null;
	private EventHandler<ActionEvent> myEvent = e -> {
		try {
			((Command) e.getSource()).OpenNewPane(apartment, currentUser);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	};

	public AgileRoommatesPane(Apartment a) {
		this.apartment = a;
		shoppingListButton = new ShoppingListButton(apartment, currentUser);
		cashBoxButton = new CashBoxButton(apartment, currentUser);
		messagesButton = new MessagesButton(apartment, currentUser);
		loginButton = new Button(LOGIN);
		//loginButton.setDefaultButton(true);
		//this.setPadding(new Insets(500, 10, 5, 10));
		//this.setBackground(new Background(new BackgroundFill(new Color(0.44, 0.62, 0.74, 0.99), CornerRadii.EMPTY, Insets.EMPTY)));
		this.setVgap(10);
		this.setHgap(10);
		shoppingListButton.setOnAction(myEvent);
		cashBoxButton.setOnAction(myEvent);
		messagesButton.setOnAction(myEvent);

		this.setStyle(MY_STYLE);
		this.setMinSize(600, 300);
		userIDLabel.setStyle("-fx-font-size: 1.5em;"+"-fx-text-fill: LAVENDER;" + "-fx-font-weight: bolder;");
		userNameLabel.setStyle("-fx-font-size: 1.5em;"+"-fx-text-fill: LAVENDER;" + "-fx-font-weight: bolder;");


		Text text = new Text("WELCOME");
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
		text.setFill(Color.ALICEBLUE);
		text.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
				new Stop[] { new Stop(0, Color.LAVENDER), new Stop(1, Color.WHITE) }));
		text.setStrokeWidth(3);
		this.add(text, 0, 0);

		GridPane.setHgrow(this, Priority.ALWAYS);
		GridPane.setVgrow(this, Priority.ALWAYS);

		this.setWidth(700);
		setLoginPane();

		loginButton.setOnMouseClicked(e -> {
			int id = 0;
			if (userNameTextField.getText().length() != 0 && userIDTextField.getText().length() != 0) {
				try {
					id = Integer.parseInt(userIDTextField.getText());
					setCurrentUser(userNameTextField.getText(), id);
					if (currentUser != null) {
						this.getChildren().clear();						
						setWelcomePane();
						setButtonsPane();
						try {
							apartment.readApartmentFromFile();
						} catch (IOException e1) {
							System.out.println("Read apartment from file failed");
							e1.printStackTrace();
						}
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "ID must be numeral!", NUMBER_EXCEPTION_TITLE,
							JOptionPane.ERROR_MESSAGE);
				}
			} else
				JOptionPane.showMessageDialog(new JFrame(), "You Must Enter Valid Name & ID!", "Invalid Text Exception",
						JOptionPane.ERROR_MESSAGE);
		});
	}

	public void setWelcomePane() {
		this.setStyle(MY_STYLE);
		Text text = new Text(
				"Welcome " + userNameTextField.getText() + "!\nTo Perform An Action\nClick The Buttons Below");
		text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 38));
		text.setFill(Color.ALICEBLUE);
		text.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
				new Stop[] { new Stop(0, Color.LIGHTSKYBLUE), new Stop(1, Color.GHOSTWHITE) }));
		text.setStrokeWidth(3);
		text.setStroke(Color.BLUE);
		welcomePane.setAlignment(Pos.TOP_LEFT);

		GridPane.setHgrow(welcomePane, Priority.ALWAYS);
		GridPane.setVgrow(welcomePane, Priority.ALWAYS);		

		welcomePane.setVgap(10);
		welcomePane.setHgap(10);
		welcomePane.add(text, 0, 0);
	
		this.add(welcomePane, 0, 0);
	}

	public void setButtonsPane() {
		buttonsPane.setVgap(8);
		buttonsPane.setHgap(8);

		buttonsPane.getChildren().add(shoppingListButton);
		buttonsPane.getChildren().add(cashBoxButton);
		buttonsPane.getChildren().add(messagesButton);
		buttonsPane.setAlignment(Pos.CENTER_LEFT);
		
		
		shoppingListButton.setDefaultButton(true);
		cashBoxButton.setDefaultButton(true);
		messagesButton.setDefaultButton(true);
		
		shoppingListButton.setMinSize(50, 50);
		cashBoxButton.setMinSize(50, 50);
		messagesButton.setMinSize(50, 50);
		
		
		//shoppingListButton.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
		shoppingListButton.setStyle("-fx-font-size: 1.5em;"+"-fx-text-fill: BLACK;" + "-fx-font-weight: bolder;");
		//cashBoxButton.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
		cashBoxButton.setStyle("-fx-font-size: 1.5em;"+"-fx-text-fill: BLACK;" + "-fx-font-weight: bolder;");
		//messagesButton.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
		messagesButton.setStyle("-fx-font-size: 1.5em;"+"-fx-text-fill: BLACK;" + "-fx-font-weight: bolder;");


		this.add(buttonsPane, 0, 2);
	}

	public void setLoginPane() {
		loginPane.add(userNameLabel, 0, 0);
		loginPane.add(userIDLabel, 0, 1);
		loginPane.add(userNameTextField, 1, 0);
		loginPane.add(userIDTextField, 1, 1);
		loginPane.add(loginButton, 1, 2);;
		//loginButton.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
		loginButton.setStyle("-fx-font-size: 1.5em;"+"-fx-text-fill: black;" + "-fx-font-weight: bolder;");
		loginButton.setDefaultButton(true);
	
		loginPane.setAlignment(Pos.TOP_LEFT);
		loginPane.setVgap(20);
		loginPane.setHgap(20);
		loginPane.setPadding(new Insets(10, 10, 10, 10));
		loginButton.setPadding(new Insets(5, 10, 5, 10));
		
		GridPane.setVgrow(userNameLabel, Priority.ALWAYS);
		GridPane.setVgrow(userIDLabel, Priority.ALWAYS);
		GridPane.setHgrow(userNameTextField, Priority.ALWAYS);
		GridPane.setHgrow(userIDTextField, Priority.ALWAYS);
		GridPane.setHgrow(loginPane, Priority.ALWAYS);
		GridPane.setVgrow(loginPane, Priority.ALWAYS);
		
		this.add(loginPane, 0, 1);		
	}

	public void setCurrentUser(String name, int id) {
		if (apartment.alreadyResident(id))
			this.currentUser = apartment.getUserOnResidentsListByID(id);
		else
			JOptionPane.showMessageDialog(new JFrame(), "You Are Not Register In The Apartment!", "Unregister User",
					JOptionPane.ERROR_MESSAGE);
	}
}
