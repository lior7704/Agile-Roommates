package Application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MainPane extends Application implements AgileRoommatesFinals {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AgileRoommatesPane pane = AgileRoommatesPane.getInstance();
		if (pane == null) {
			System.out.println("Cannot open more than " + 1 + " windows.");
			return;
		}

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

class AgileRoommatesPane extends GridPane implements AgileRoommatesFinals, AgileRoommatesEvent {
	private RandomAccessFile raf;
	private static RandomAccessFile count_file;
	private TextField jtfApartmentID = new TextField();
	private TextField jtfUserID = new TextField();
	private ShoppingListButton jbtShoppingList;
	private CashBoxButton jbtCashBox;
	private MessagesButton jbtMessages;
	private long state;
	CareTaker careTaker;
//	private EventHandler<ActionEvent> ae = e -> ((Command) e.getSource()).Execute();
	private EventHandler<ActionEvent> myEvent = e -> ((Command) e.getSource()).OpenNewPane();

	private AgileRoommatesPane() {
		state = -1;
		careTaker = new CareTaker();
		try {
			raf = new RandomAccessFile(FILE_NAME, FILE_MODE);
		} catch (IOException ex) {
			System.out.println(ex);
			System.exit(0);
		}
		jbtShoppingList = new ShoppingListButton(this, raf);
		jbtCashBox = new CashBoxButton(this, raf);
		jbtMessages = new MessagesButton(this, raf);
		Label apartID = new Label(APARTMENT_ID);
		Label userID = new Label(USER_ID);
		GridPane p1 = new GridPane();
		p1.add(apartID, 0, 0);
		p1.add(userID, 0, 1);
		p1.setAlignment(Pos.CENTER_LEFT);
		p1.setVgap(8);
		p1.setPadding(new Insets(0, 2, 0, 2));
		GridPane.setVgrow(apartID, Priority.ALWAYS);
		GridPane.setVgrow(userID, Priority.ALWAYS);
		GridPane adP = new GridPane();
		GridPane p4 = new GridPane();
		p4.add(jtfApartmentID, 0, 0);
		p4.add(jtfUserID, 0, 1);
		p4.add(adP, 0, 2);
		p4.setVgap(1);
		GridPane.setHgrow(jtfApartmentID, Priority.ALWAYS);
		GridPane.setHgrow(jtfUserID, Priority.ALWAYS);
		GridPane.setHgrow(adP, Priority.ALWAYS);
		GridPane.setVgrow(jtfApartmentID, Priority.ALWAYS);
		GridPane.setVgrow(jtfUserID, Priority.ALWAYS);
		GridPane.setVgrow(adP, Priority.ALWAYS);
		GridPane jpAddress = new GridPane();
		jpAddress.add(p1, 0, 0);
		jpAddress.add(p4, 1, 0);
		GridPane.setHgrow(p1, Priority.NEVER);
		GridPane.setHgrow(p4, Priority.ALWAYS);
		GridPane.setVgrow(p1, Priority.ALWAYS);
		GridPane.setVgrow(p4, Priority.ALWAYS);
		jpAddress.setStyle(STYLE_COMMAND);
		FlowPane jpButton = new FlowPane();
		jpButton.setHgap(5);
		if (eventType.SHOPPING_LIST.getDoEvent())
			jpButton.getChildren().add(jbtShoppingList);
		if (eventType.CASH_BOX.getDoEvent())
			jpButton.getChildren().add(jbtCashBox);
		if (eventType.MESSAGES.getDoEvent())
			jpButton.getChildren().add(jbtMessages);
		jpButton.setAlignment(Pos.CENTER);
		GridPane.setVgrow(jpButton, Priority.NEVER);
		GridPane.setVgrow(jpAddress, Priority.ALWAYS);
		GridPane.setHgrow(jpButton, Priority.ALWAYS);
		GridPane.setHgrow(jpAddress, Priority.ALWAYS);
		GridPane myGridPane = new GridPane();
		myGridPane.setStyle(STYLE_COMMAND);
		myGridPane.setAlignment(Pos.CENTER);
		GridPane.setHgrow(myGridPane, Priority.ALWAYS);
		myGridPane.setPrefWidth(100);
		myGridPane.setHgap(20);
		this.setVgap(5);
		this.add(jpAddress, 0, 0);
		this.add(jpButton, 0, 1);
		this.add(myGridPane, 0, 2);
		jbtShoppingList.setOnAction(myEvent);
		jbtCashBox.setOnAction(myEvent);
		jbtMessages.setOnAction(myEvent);
		jbtShoppingList.Execute();
	}

	public static AgileRoommatesPane getInstance() throws FileNotFoundException {
		int n = 0;
		count_file = new RandomAccessFile("count.dat", FILE_MODE);
		try {
			if (count_file.length() == 0)
				n = 0;
			else
				n = count_file.readInt();
			if (n < NUMBER_OF_PANES) {
				count_file.setLength(0);
				count_file.writeInt(n + 1);
				return new AgileRoommatesPane();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void SetApartmentID(String text) {
		jtfApartmentID.setText(text);
	}

	public void SetuserID(String text) {
		jtfUserID.setText(text);
	}

	public String GetApartmentID() {
		return jtfApartmentID.getText();
	}

	public String GetuserID() {
		return jtfUserID.getText();
	}

	public void clearTextFields() {
		jtfApartmentID.setText("");
		jtfUserID.setText("");
	}

	/******* Memento_Methods ********/
	public void setState(long state) {
		this.state = state;
	}

	public long getState() {
		return state;
	}

	public Memento saveStateToMemento() {
		return new Memento(state);
	}

	public void getStateFromMemento(Memento memento) {
		this.state = memento.getState();
	}

	/******* Memento_Class ********/
	class Memento {
		private long pos;

		public Memento(long pos) {
			this.pos = pos;
		}

		private long getState() {
			return pos;
		}
	}
}

class CareTaker {
	private int index = 0;
	private ObservableMap<Integer, AgileRoommatesPane.Memento> mementoHashMap = FXCollections.observableHashMap();

	public void add(AgileRoommatesPane.Memento memento) {
		mementoHashMap.put(index, memento);
		index++;
	}

	public AgileRoommatesPane.Memento getPre() {
		if (index > 0)
			index--;
		return mementoHashMap.get(index);
	}

	public AgileRoommatesPane.Memento getNext() {
		if (index < mementoHashMap.size() - 1)
			index++;
		return mementoHashMap.get(index);
	}
}

interface Command {
	public void Execute();
	public void OpenNewPane();
	public void PreExecute();
}

class CommandButton extends Button implements Command, AgileRoommatesFinals {
	private AgileRoommatesPane p;
	private RandomAccessFile raf;
	
	public CommandButton(AgileRoommatesPane pane, RandomAccessFile r) {
		super();
		p = pane;
		raf = r;
	}

	public AgileRoommatesPane getPane() {
		return p;
	}

	public RandomAccessFile getFile() {
		return raf;
	}

	public void setPane(AgileRoommatesPane p) {
		this.p = p;
	}

	@Override
	public void Execute() {
	}

	public void writeAddress(long position) {
		try {
			getFile().seek(position);
			FixedLengthStringIO1.writeFixedLengthString(getPane().GetApartmentID(), APARTMENT_ID_SIZE, getFile());
			FixedLengthStringIO1.writeFixedLengthString(getPane().GetuserID(), USER_ID_SIZE, getFile());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readAddress(long position) throws IOException {
		getFile().seek(position);
		String ApartmentID = FixedLengthStringIO1.readFixedLengthString(APARTMENT_ID_SIZE, getFile());
		String userID = FixedLengthStringIO1.readFixedLengthString(USER_ID_SIZE, getFile());
		getPane().SetApartmentID(ApartmentID);
		getPane().SetuserID(userID);
	}

	@Override
	public void PreExecute() {
		long prePosition = 0;
		try {
			prePosition = getFile().getFilePointer() - (2 * RECORD_SIZE);
			if (prePosition >= 0 && prePosition != getPane().getState()) {
				getPane().setState(prePosition);
				getPane().careTaker.add(getPane().saveStateToMemento());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Execute();
	}

	@Override
	public void OpenNewPane() {
	}
}

class CashBoxButton extends CommandButton {
	public CashBoxButton(AgileRoommatesPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(CASH_BOX);
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
		jtfRadius.setOnAction(e -> Execute());
		jcboFilled.setOnAction(e -> Execute());
	}

	@Override
	public void Execute() {
		try {
			long currentPosition = getFile().getFilePointer();
			if (currentPosition < getFile().length())
				readAddress(currentPosition);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

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
			else;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

class ShoppingListButton extends CommandButton {
	public ShoppingListButton(AgileRoommatesPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(SHOPPING_LIST);
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
		stage.setTitle("Shopping List"); 
		
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
			if (getFile().length() > 0)
				readAddress(0);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}