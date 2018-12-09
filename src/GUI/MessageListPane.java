package GUI;

import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entities.Apartment;
import Entities.Message;
import Entities.User;
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

public class MessageListPane implements AgileRoommatesFinals {
	private Button addButton;
	private ListView<Message> listView;
	private Apartment apartment;
	private RandomAccessFile rf;
	private User currentUser = null;

	public MessageListPane(Apartment apartment, User currentUser) throws IOException {
		this.rf = new RandomAccessFile(MESSAGES_LIST_FILE, FILE_MODE);
		this.apartment = apartment;
		this.currentUser = currentUser;
		if (getFile().length()>1)
			readFromFile();	
		
		Stage stage = new Stage();
		GridPane addMessagePane = new GridPane();
		addMessagePane.add(addButton = new Button(SEND), 0, 1);
		addMessagePane.setVisible(true);
		TextField messageTextField = new TextField();
		listView = new ListView<Message>();

		addMessagePane.setPadding(new Insets(15));
		addMessagePane.setHgap(10);
		addMessagePane.setVgap(10);
		addMessagePane.setAlignment(Pos.CENTER_LEFT);
		addMessagePane.add(new Label(MESSAGES), 1, 0);
		addMessagePane.add(messageTextField, 1, 1);

		messageTextField.setPrefWidth(300);

		setListView();
		listView.setPrefWidth(300);
		listView.setPrefHeight(100);
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
		stage.setX(400);
		stage.setY(200);
		stage.show();
		stage.setTitle(MESSAGE_LIST_TITLE);
		stage.setOnCloseRequest(e -> writeMessagesListToFile());

		addButton.setOnMouseClicked(e -> {
			if (messageTextField.getText().length() != 0) {
				apartment.getMessagesList().addMessage(new Message(messageTextField.getText(), this.currentUser));
			} else {
				JOptionPane.showMessageDialog(new JFrame(), EMPTY_TEXT_EXCEPTION, EMPTY_EXCEPTION_TITLE, JOptionPane.ERROR_MESSAGE);
			}
			setListView();
		});
	}

	public void setListView() {
		ObservableList<Message> items = FXCollections.observableArrayList();
		items.addAll(apartment.getMessagesList().getMessages());
		listView.setItems(items);
		listView.refresh();
	}

	public void writeMessagesListToFile() {
		try {
			getFile().setLength(0);
			getFile().seek(0);
			getFile().writeInt(listView.getItems().size());
			for (int i = 0; i < listView.getItems().size(); i++) {
				FixedLengthStringIO.writeFixedLengthString(listView.getItems().get(i).getContent(), LONG_STRING_SIZE,
						getFile());
				FixedLengthStringIO.writeFixedLengthString(listView.getItems().get(i).getDate().toString(),
						LONG_STRING_SIZE, getFile());
				getFile().writeInt(listView.getItems().get(i).getSender().getId());
			}
			getFile().close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public RandomAccessFile getFile() {
		return rf;
	}

	public void readFromFile() throws IOException {
		getFile().seek(0);
		int size = getFile().readInt();
		for (int i = 0; i < size; i++) {
			String content = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, getFile());
			int userID = getFile().readInt();
			apartment.getMessagesList().addMessage(new Message(content, apartment.getUserOnResidentsListByID(userID)));
			String date = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, getFile());
			apartment.getMessagesList().getMessages().get(i).setDate(date);
		}
	}
}
