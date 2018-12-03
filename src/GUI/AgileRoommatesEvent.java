package GUI;

public interface AgileRoommatesEvent {
	enum eventType {
		ADD(true), SHOPPING_LIST(true), LAST(true), CASH_BOX(true), MESSAGES(true), CLEAR(true), REVERSE(true);
		private boolean doEvent;

		eventType(boolean doEvent) {
			this.doEvent = doEvent;
		}

		boolean getDoEvent() {
			return doEvent;
		}
	}

}