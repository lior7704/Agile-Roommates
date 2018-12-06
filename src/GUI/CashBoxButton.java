package GUI;

import Entities.Apartment;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class CashBoxButton extends CommandButton {
	private Apartment apartment;

	public CashBoxButton(Apartment apartment) {
		super(apartment);
		this.setText(CASH_BOX);
		this.apartment = apartment;
	}

	@Override
	public void OpenNewPane() {
		super.OpenNewPane();
		Stage stage = new Stage();
		GridPane gp = new GridPane();
		Scene scene1 = new Scene(gp, 400, 300);
		TextField jtfRadius = new TextField();
		ComboBox<Boolean> jcboFilled = new ComboBox<>(FXCollections.observableArrayList(false, true));
		Label label = new Label("CashBox");

		stage.setTitle("CashBox");
		stage.setScene(scene1);
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.setX(200);
		stage.setY(200);
		stage.show();

		gp.setPadding(new Insets(20));
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER_LEFT);
		gp.add(new Label("Radius"), 0, 0);
		gp.add(new Label("Filled"), 0, 1);
		gp.add(new Label("Color"), 0, 2);
		gp.add(jtfRadius, 1, 0);
		gp.add(jcboFilled, 1, 1);
		jcboFilled.setValue(false);
		gp.add(label, 1, 2);
		jcboFilled.setPrefWidth(100);
		jtfRadius.setPrefWidth(100);
		label.setPrefWidth(100);
	}
}

