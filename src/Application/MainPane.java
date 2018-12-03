package Application;

import java.io.IOException;
import java.io.RandomAccessFile;

import GUI.AgileRoommatesFinals;
import GUI.AgileRoommatesPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPane extends Application implements AgileRoommatesFinals {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AgileRoommatesPane pane =new  AgileRoommatesPane();
		Scene scene = new Scene(pane);
		// scene.getStylesheets().add(STYLES_CSS);
		primaryStage.setTitle(TITLE);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setOnCloseRequest(e -> {
			RandomAccessFile f;
			try {
				f = new RandomAccessFile("count.dat", FILE_MODE);
				int n = f.readInt();
				f.setLength(0);
				f.writeInt(n - 1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
	}
}







