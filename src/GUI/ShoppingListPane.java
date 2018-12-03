package GUI;

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

public class ShoppingListPane {

	public ShoppingListPane() {
		Stage stage = new Stage();
		GridPane addProductPane = new GridPane();
		
		addProductPane.setVisible(true);
		TextField jtfProduct = new TextField();
		TextField jtfAmount = new TextField();

		addProductPane.setPadding(new Insets(15));
		addProductPane.setHgap(10);
		addProductPane.setVgap(10);
		addProductPane.setAlignment(Pos.CENTER_LEFT);
		addProductPane.add(new Button("Add Product"),0, 1);
		addProductPane.add(new Button("Remove Product"),0, 2);
		addProductPane.add(new Label("Product"), 1, 0);
		addProductPane.add(new Label("Amount"), 2, 0);
		addProductPane.add(jtfProduct, 1, 1);
		addProductPane.add(jtfAmount, 2, 1);

		jtfProduct.setPrefWidth(100);
		jtfAmount.setPrefWidth(10);

		
		
		ListView<String> list = new ListView<String>();
		ObservableList<String> items =FXCollections.observableArrayList (
		    "Single", "Double", "Suite", "Family App");
		list.setItems(items);
		list.setPrefWidth(100);
		list.setPrefHeight(70);
		VBox box = new VBox();
		Label weNeedToBuyLabel=new Label("Things we need to buy");
		VBox.setMargin(weNeedToBuyLabel, new Insets(20, 20, 20, 20)); 
        stage.setTitle("ListViewSample");
        box.getChildren().addAll(list);
        VBox.setVgrow(list, Priority.ALWAYS);
        
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
		stage.setTitle("Shopping List");
	}
}
