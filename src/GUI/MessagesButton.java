package GUI;

import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

class MessagesButton extends CommandButton {
	public MessagesButton(AgileRoommatesPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(MESSAGES);
	}

	@Override
	public void OpenNewPane() {
		super.OpenNewPane();
		Stage stage = new Stage();
		GridPane gp = new GridPane();
		Scene scene1 = new Scene(gp, 400, 300);
		gp.setVisible(true);
		TextField jtfRadius = new TextField();
		ComboBox<Boolean> jcboFilled = new ComboBox<>(FXCollections.observableArrayList(false, true));
		stage.setScene(scene1);
		stage.setAlwaysOnTop(true);
		stage.setResizable(false);
		stage.setX(200);
		stage.setY(200);
		stage.show();
		stage.setTitle("Messages");

		gp.setPadding(new Insets(15));
		gp.setHgap(10);
		gp.setVgap(10);
		gp.setAlignment(Pos.CENTER_LEFT);
		gp.add(new Label("Radius"), 0, 0);
		gp.add(new Label("Filled"), 0, 1);
		gp.add(new Label("Color"), 0, 2);
		gp.add(jtfRadius, 1, 0);
		gp.add(jcboFilled, 1, 1);
		jcboFilled.setValue(false);
		jcboFilled.setPrefWidth(100);
		jtfRadius.setPrefWidth(100);
		jtfRadius.setOnAction(e -> Execute());
		jcboFilled.setOnAction(e -> Execute());
	}

	@Override
	public void Execute() {
		try {
			long currentPosition = getFile().getFilePointer();
			if (currentPosition - 2 * 2 * RECORD_SIZE >= 0)
				readAddress(currentPosition - 2 * 2 * RECORD_SIZE);
			else
				;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
