package GUI;

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

public class CashBoxPane {
	private Button addBillButton;
	private Button payButton;
	private ListView<Bill> listView;
	private Apartment apartment;
	private GridPane balancePane;

	public CashBoxPane(Apartment apartment, User currentUser) {
		this.apartment = apartment;
		Stage stage = new Stage();

		balancePane = new GridPane();
		setBalanceView();

		GridPane billsPane = new GridPane();
		billsPane.add(addBillButton = new Button("Add Bill"), 0, 1);
		billsPane.add(payButton = new Button("Pay Bill"), 0, 2);
		billsPane.setVisible(true);
		TextField jtfBillName = new TextField();
		TextField jtfBillCost = new TextField();
		TextField jtfBillDueDate = new TextField();

		listView = new ListView<Bill>();

		billsPane.setPadding(new Insets(15));
		billsPane.setHgap(10);
		billsPane.setVgap(10);
		billsPane.setAlignment(Pos.CENTER_LEFT);
		billsPane.add(new Label("Bill's Name"), 1, 0);
		billsPane.add(new Label("Cost"), 2, 0);
		billsPane.add(new Label("Due Date"), 3, 0);
		billsPane.add(new Label("Select a Bill and click to pay"), 1, 2);

		billsPane.add(jtfBillName, 1, 1);
		billsPane.add(jtfBillCost, 2, 1);
		billsPane.add(jtfBillDueDate, 3, 1);

		jtfBillName.setPrefWidth(100);
		jtfBillCost.setPrefWidth(40);
		jtfBillDueDate.setPrefWidth(80);

		setListView();
		listView.setPrefWidth(100);
		listView.setPrefHeight(200);
		VBox box = new VBox();
		Label weNeedToPayLabel = new Label("Bills we need to pay");
		VBox.setMargin(weNeedToPayLabel, new Insets(20, 20, 20, 20));
		stage.setTitle("ListViewSample");
		box.getChildren().addAll(listView);
		box.setPrefHeight(500);
		VBox.setVgrow(listView, Priority.ALWAYS);
		
		GridPane unitPane = new GridPane();
		unitPane.add(balancePane, 0, 0);
		unitPane.add(billsPane, 0, 1);
		unitPane.add(box, 0, 2);
		unitPane.setPadding(new Insets(2));
		unitPane.setAlignment(Pos.TOP_CENTER);
		

		Scene scene = new Scene(unitPane, 420, 800);
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.setX(200);
		stage.setY(200);
		stage.show();
		stage.setTitle("Cash-Box");

		addBillButton.setOnMouseClicked(e -> {
			double amount = 0;
			try {
				amount = Double.parseDouble(jtfBillCost.getText());
			} catch (NumberFormatException e1) {
				String message = "Amount must be numeral!";
				JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
			}
			if (jtfBillName.getText().length() != 0 && amount > 0) {
				apartment.getCashBox().addBillToList(new Bill(jtfBillName.getText(), amount, jtfBillDueDate.getText()));
			}
			setListView();
			setBalanceView();
		});
		payButton.setOnMouseClicked(e -> {
			apartment.getCashBox().payBill(listView.getSelectionModel().getSelectedItem(), currentUser);
			setListView();
			setBalanceView();
		});
	}

	public void setListView() {
		ObservableList<Bill> items = FXCollections.observableArrayList();
		items.addAll(apartment.getCashBox().getBillList());
		listView.setItems(items);
		listView.refresh();
	}

	public void setBalanceView() {
		int balanceIndex = 1;
		balancePane.getChildren().clear();
		balancePane.add(new Label("Balance Status: "), 0, 0);
		balancePane.add(new Label("Total Balance Status: "), 2, 0);
		balancePane.add(new Label(String.valueOf(apartment.getCashBox().getTotalCashBalance())), 3, 0);
		balancePane.setPadding(new Insets(3));
		balancePane.setHgap(10);
		balancePane.setVgap(10);
		balancePane.setMaxHeight(20);
		balancePane.setAlignment(Pos.CENTER_LEFT);
		for (User user : apartment.getCashBox().getUsersCashBalance().keySet()) {
			balancePane.add(new Label(user.getName()), 0, balanceIndex);
			balancePane.add(new Label(String.valueOf(apartment.getCashBox().getUsersCashBalance().get(user))), 1,
					balanceIndex);
			balanceIndex++;
		}
	}

	
}