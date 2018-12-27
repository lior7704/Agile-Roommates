package Entities;

public class Bill {

	private String nameOfBill;
	private double cost;
	private boolean isPaid;
	private User paidBy;
	private String dueDate;

	public Bill(String nameOfBill, double cost, String dueDate) {
		this.nameOfBill = nameOfBill;
		this.cost = cost;
		this.dueDate = dueDate;
		this.isPaid = false;
		this.paidBy = null;
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

	public boolean isPaid() {
		return isPaid;
	}

	public void setIsPaid(boolean isPaid) {
		this.isPaid = isPaid;
	}

	public User getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(User paidBy) {
		setIsPaid(true);
		this.paidBy = paidBy;
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
		if (isPaid == true)
			return nameOfBill + ", cost: " + cost + ", paid by: " + paidBy;
		else
			return nameOfBill + ", cost: " + cost + ", not paid" + ", due until: " + dueDate;
	}

}