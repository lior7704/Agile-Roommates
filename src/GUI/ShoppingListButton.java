package GUI;

import java.io.IOException;
import java.io.RandomAccessFile;

class ShoppingListButton extends CommandButton {
	public ShoppingListButton(AgileRoommatesPane pane, RandomAccessFile r) {
		super(pane, r);
		this.setText(SHOPPING_LIST);
	}

	@Override
	public void OpenNewPane() {
		new ShoppingListPane();
		//jtfProduct.setOnAction(e -> Execute());
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
