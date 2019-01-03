package GUI;

import java.io.IOException;

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
	private GridPane balancePane = new GridPane();;
	private BorderPane unitPane = new BorderPane();
	private GridPane billsPane = new GridPane();

	public CashBoxPane(Apartment apartment, User currentUser) throws IOException {
		this.apartment = apartment;
		this.currentUser = currentUser;
 
		setBalanceView();

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

		listView.setPrefWidth(100);
		listView.setPrefHeight(200);

		VBox box = new VBox();
		Label weNeedToPayLabel = new Label("Bills we need to pay");
		VBox.setMargin(weNeedToPayLabel, new Insets(20, 20, 20, 20));
		box.getChildren().addAll(listView);
		box.setPrefHeight(500);
		VBox.setVgrow(listView, Priority.ALWAYS);
		
		unitPane.setTop(billsPane);
		unitPane.setCenter(box);
		unitPane.setBottom(balancePane);
		
		Stage stage = new Stage();
		Scene scene = new Scene(unitPane, 500, 800);

		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.setX(200);
		stage.setY(200);
		stage.show();

		stage.setTitle(CASH_BOX);

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
			setBalanceView();
		});
		
		payButton.setOnMouseClicked(e -> {
			apartment.getCashBox().payBill(listView.getSelectionModel().getSelectedItem(), this.currentUser);
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
			balancePane.add(new Label(String.format("%.2f",apartment.getCashBox().getUsersCashBalance().get(user))), 1,
					balanceIndex);
			balanceIndex++;
		}
	}

}
