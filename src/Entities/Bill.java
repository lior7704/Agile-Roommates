package Entities;

public class Bill {

	private String nameOfBill;
	private double cost;
	private boolean isPayed;
	private User payedBy;
	private String dueDate;

	public Bill(String nameOfBill, double cost, String dueDate) {
		this.nameOfBill = nameOfBill;
		this.cost = cost;
		this.dueDate = dueDate;
		this.isPayed = false;
		this.payedBy = null;
	}

	public String getNameOfBill() {
		return nameOfBill;
	}

	public void setNameOfBill(String nameOfBill) {
		this.nameOfBill = nameOfBill;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setIsPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}

	public User getPayedBy() {
		return payedBy;
	}

	public void setPayedBy(User payedBy) {
		setIsPayed(true);
		this.payedBy = payedBy;
	}

	public String getDatesOfBill() {
		return dueDate;
	}

	public void setDatesOfBill(String datesOfBill) {
		this.dueDate = datesOfBill;
	}

	public double sharedPart(int amountOfUsers) {
		return this.getCost() / amountOfUsers;
	}

	@Override
	public String toString() {
		if (isPayed == true)
			return nameOfBill + ", cost: " + cost + ", payed by: " + payedBy;
		else
			return nameOfBill + ", cost: " + cost + ", not payed" + ", due until: " + dueDate;
	}

}