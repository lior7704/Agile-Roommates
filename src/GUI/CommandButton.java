package GUI;

import java.io.IOException;
import java.io.RandomAccessFile;

import javafx.scene.control.Button;

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
//				getPane().careTaker.add(getPane().saveStateToMemento());
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