package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entities.Apartment;
import Entities.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessageListPane {
	private Button addButton;
	private ListView<Message> listView;
	private Apartment apartment;

	public MessageListPane(Apartment apartment) {
		this.apartment = apartment;
		Stage stage = new Stage();
		GridPane addMessagePane = new GridPane();
		addMessagePane.add(addButton = new Button("Send"), 0, 1);
		addMessagePane.setVisible(true);
		TextField jtfMessage = new TextField();
		listView = new ListView<Message>();

		addMessagePane.setPadding(new Insets(15));
		addMessagePane.setHgap(10);
		addMessagePane.setVgap(10);
		addMessagePane.setAlignment(Pos.CENTER_LEFT);
		addMessagePane.add(new Label("Message"), 1, 0);
		addMessagePane.add(jtfMessage, 1, 1);

		jtfMessage.setPrefWidth(300);

		setListView();
		listView.setPrefWidth(100);
		listView.setPrefHeight(70);
		VBox box = new VBox();
		Label messageLabel = new Label("Messages:");
		VBox.setMargin(messageLabel, new Insets(20, 20, 20, 20));
		box.getChildren().addAll(listView);
		VBox.setVgrow(listView, Priority.ALWAYS);

		BorderPane unitPane = new BorderPane();

		unitPane.setTop(addMessagePane);
		unitPane.setCenter(box);

		Scene scene = new Scene(unitPane, 500, 800);
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.setX(200);
		stage.setY(200);
		stage.show();
		stage.setTitle("Messages List");

		addButton.setOnMouseClicked(e -> {
			if (jtfMessage.getText().length() != 0) {
				apartment.getMessages().addMessage(new Message(jtfMessage.getText(), null));
			}
			else {
				String message = "Message cannot be empty";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
			}
			setListView();
		});
	}

	public void setListView() {
		ObservableList<Message> items = FXCollections.observableArrayList();
		items.addAll(apartment.getMessages().getMessages());
		listView.setItems(items);
		listView.refresh();
	}
}
