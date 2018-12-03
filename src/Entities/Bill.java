package Entities;

public class Bill {

	private String nameOfBill;
	private double cost;
	private boolean hasPayed;
	private User payedBy;
	private String datesOfBill;
	
	public Bill(String nameOfBill, double cost, String datesOfBill) {
		this.nameOfBill = nameOfBill;
		this.cost = cost;
		this.datesOfBill = datesOfBill;
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

	public boolean isHasPayed() {
		return hasPayed;
	}

	public void setHasPayed(boolean hasPayed) {
		this.hasPayed = hasPayed;
	}

	public User getPayedBy() {
		return payedBy;
	}

	public void setPayedBy(User payedBy) {
		this.payedBy = payedBy;
	}
	
	public String getDatesOfBill() {
		return datesOfBill;
	}

	public void setDatesOfBill(String datesOfBill) {
		this.datesOfBill = datesOfBill;
	}

	public double sharedPart(int amountOfUsers) {
		return this.getCost() / amountOfUsers;
	}

}