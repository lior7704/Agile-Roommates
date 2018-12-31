package GUI;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Entities.Apartment;
import Entities.Product;
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

public class ShoppingListPane implements AgileRoommatesFinals {
	private Button addButton;
	private Button removeButton;
	private ListView<Product> listView;
	private Apartment apartment;

	public ShoppingListPane(Apartment apartment) throws IOException {
		this.apartment = apartment;

		Stage stage = new Stage();
		GridPane addProductPane = new GridPane();
		addProductPane.add(addButton = new Button("Add Product"), 0, 1);
		addProductPane.add(removeButton = new Button("Remove Product"), 0, 2);
		addProductPane.setVisible(true);
		TextField productTextField = new TextField();
		TextField amountTextField = new TextField();
		listView = new ListView<Product>();

		addProductPane.setPadding(new Insets(15));
		addProductPane.setHgap(10);
		addProductPane.setVgap(10);
		addProductPane.setAlignment(Pos.CENTER_LEFT);
		addProductPane.add(new Label("Product"), 1, 0);
		addProductPane.add(new Label("Amount"), 2, 0);
		addProductPane.add(productTextField, 1, 1);
		addProductPane.add(amountTextField, 2, 1);

		productTextField.setPrefWidth(100);
		amountTextField.setPrefWidth(20);

		setListView();
		listView.setPrefWidth(130);
		listView.setPrefHeight(70);
		VBox box = new VBox();
		Label weNeedToBuyLabel = new Label("Things we need to buy");
		VBox.setMargin(weNeedToBuyLabel, new Insets(20, 20, 20, 20));
		box.getChildren().addAll(listView);
		VBox.setVgrow(listView, Priority.ALWAYS);

		BorderPane unitPane = new BorderPane();

		unitPane.setTop(addProductPane);
		unitPane.setCenter(box);

		Scene scene = new Scene(unitPane, 400, 800);
		stage.setScene(scene);
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.setX(200);
		stage.setY(200);
		stage.show();
		stage.setTitle(SHOPPING_LIST);

		addButton.setOnMouseClicked(e -> {
			int amount = 0;
			try {
				amount = Integer.parseInt(amountTextField.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(new JFrame(), NUMBER_FORMAT_EXCEPTION, NUMBER_EXCEPTION_TITLE,
						JOptionPane.ERROR_MESSAGE);
			}
			if (productTextField.getText().length() != 0 && amount > 0) {
				apartment.getShoppingList().addProduct(new Product(productTextField.getText().trim().toLowerCase(), amount));
			}
			setListView();
		});
		removeButton.setOnMouseClicked(e -> {
			apartment.getShoppingList().remove(listView.getSelectionModel().getSelectedItem());
			setListView();
		});
	}

	public void setListView() {
		ObservableList<Product> items = FXCollections.observableArrayList();
		items.addAll(apartment.getShoppingList().getProducts());
		listView.setItems(items);
		listView.refresh();
	}
}
