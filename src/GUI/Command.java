package GUI;

import Entities.Apartment;
import Entities.User;

public interface Command {
	public void OpenNewPane(Apartment apartment, User currentUser) throws Exception;
}
