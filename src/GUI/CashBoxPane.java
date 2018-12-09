package GUI;

import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entities.Apartment;
import Entities.Bill;
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

public class CashBoxPane implements AgileRoommatesFinals {
	private Button addBillButton;
	private Button payButton;
	private ListView<Bill> listView;
	private Apartment apartment;
	private User currentUser;
	private RandomAccessFile rf;

	public CashBoxPane(Apartment apartment, User currentUser) throws IOException {
		this.rf = new RandomAccessFile(CASHBOX_FILE, FILE_MODE);
		this.apartment = apartment;
		this.currentUser = currentUser;
		if (getFile().length() > 1)
			readFromFile();

		Stage stage = new Stage();
		GridPane billsPane = new GridPane();
		billsPane.add(addBillButton = new Button("Add Bill"), 0, 1);
		billsPane.add(payButton = new Button("Pay Bill"), 0, 2);
		billsPane.setVisible(true);
		TextField billNameTextField = new TextField();
		TextField billCostTextField = new TextField();
		TextField billDueDateTextField = new TextField();
		listView = new ListView<Bill>();

		billsPane.setPadding(new Insets(15));
		billsPane.setHgap(10);
		billsPane.setVgap(10);
		billsPane.setAlignment(Pos.CENTER_LEFT);
		billsPane.add(new Label("Bill's Name"), 1, 0);
		billsPane.add(new Label("Cost"), 2, 0);
		billsPane.add(new Label("Due Date"), 3, 0);
		billsPane.add(new Label("Select a Bill and click to pay"), 1, 2);

		billsPane.add(billNameTextField, 1, 1);
		billsPane.add(billCostTextField, 2, 1);
		billsPane.add(billDueDateTextField, 3, 1);

		billNameTextField.setPrefWidth(120);
		billCostTextField.setPrefWidth(50);
		billDueDateTextField.setPrefWidth(100);

		setListView();
		listView.setPrefWidth(120);
		listView.setPrefHeight(70);
		VBox box = new VBox();
		Label weNeedToPayLabel = new Label("Bills we need to pay");
		VBox.setMargin(weNeedToPayLabel, new Insets(20, 20, 20, 20));
		box.getChildren().addAll(listView);
		VBox.setVgrow(listView, Priority.ALWAYS);

		BorderPane unitPane = new BorderPane();

		unitPane.setTop(billsPane);
		unitPane.setCenter(box);

		Scene scene = new Scene(unitPane, 500, 800);
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.setX(200);
		stage.setY(200);
		stage.show();
		stage.setTitle(CASH_BOX);
		stage.setOnCloseRequest(e -> writeCashBoxToFile());

		addBillButton.setOnMouseClicked(e -> {
			double amount = 0;
			try {
				amount = Double.parseDouble(billCostTextField.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(new JFrame(), NUMBER_FORMAT_EXCEPTION, NUMBER_EXCEPTION_TITLE,
						JOptionPane.ERROR_MESSAGE);
			}
			if (billNameTextField.getText().length() != 0 && amount > 0) {
				apartment.getCashBox().addBillToList(new Bill(billNameTextField.getText(), amount, billDueDateTextField.getText()));
			}
			setListView();
		});
		payButton.setOnMouseClicked(e -> {
			System.out.println(this.currentUser);
			apartment.getCashBox().payBill(listView.getSelectionModel().getSelectedItem(), this.currentUser);
			setListView();
		});
	}

	public void setListView() {
		ObservableList<Bill> items = FXCollections.observableArrayList();
		items.addAll(apartment.getCashBox().getBillList());
		listView.setItems(items);
		listView.refresh();
	}

	public void writeCashBoxToFile() {
		try {
			getFile().setLength(0);
			getFile().seek(0);
			getFile().writeInt(listView.getItems().size());
			for (int i = 0; i < listView.getItems().size(); i++) {
				FixedLengthStringIO.writeFixedLengthString(listView.getItems().get(i).getNameOfBill(), LONG_STRING_SIZE,
						getFile());
				FixedLengthStringIO.writeFixedLengthString(Double.toString(listView.getItems().get(i).getCost()),
						SHORT_INT_SIZE, getFile());
				FixedLengthStringIO.writeFixedLengthString(listView.getItems().get(i).getDatesOfBill(), LONG_INT_SIZE,
						getFile());
				if (listView.getItems().get(i).isPayed())
					getFile().writeInt(listView.getItems().get(i).getPayedBy().getId());
				else
					getFile().writeInt(0);
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
			String name = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, getFile());
			Double cost = Double.parseDouble(FixedLengthStringIO.readFixedLengthString(SHORT_INT_SIZE, getFile()));
			String date = FixedLengthStringIO.readFixedLengthString(LONG_INT_SIZE, getFile());
			apartment.getCashBox().addBillToList(new Bill(name.trim(), cost, date.trim()));
			int userID = getFile().readInt();
			if (userID != 0)
				apartment.getCashBox().getBillList().get(i).setPayedBy(apartment.getUserOnResidentsListByID(userID));
		}
	}
}
