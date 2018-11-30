
package Entities;

import java.util.Map;

public class CashBox {

	private String nameOfBill;
	private double cost;
	private boolean hasPayed;
	private User payedBy;
	private Map<User, Double> usersCashBalance;
	private double totalCashBalance;

	
	public CashBox(Map<User, Double> usersCashBalance, double totalCashBalance) {
		this.usersCashBalance = usersCashBalance;
		this.totalCashBalance = totalCashBalance;
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

	public Map<User, Double> getUsersCashBalance() {
		return usersCashBalance;
	}

	public void setUsersCashBalance(Map<User, Double> usersCashBalance) {
		this.usersCashBalance = usersCashBalance;
	}

	public double getTotalCashBalance() {
		return totalCashBalance;
	}

	public void setTotalCashBalance(double totalCashBalance) {
		this.totalCashBalance = totalCashBalance;
	}

	public void payBill(double cost, User payedBy, Map<User, Double> usersCashBalance) {
		double needToPay = cost / usersCashBalance.size();

		for (User key : usersCashBalance.keySet()) {
			double userNeedToPay = usersCashBalance.get(key);
			if (!usersCashBalance.containsKey(payedBy)) {
				userNeedToPay = userNeedToPay - needToPay;
				usersCashBalance.replace(key, userNeedToPay);
			}
			else {
				userNeedToPay = userNeedToPay + cost;
				usersCashBalance.replace(payedBy, userNeedToPay);

			}

		}
	}
}
