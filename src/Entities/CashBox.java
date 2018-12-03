
package Entities;

import java.util.*;

public class CashBox {

	private Map<User, Double> usersCashBalance;
	private List<Bill> billList;
	private double totalCashBalance;

	public CashBox(Map<User, Double> usersCashBalance, List<Bill> billList) {
		this.usersCashBalance = usersCashBalance;
		this.billList = billList;
	}

	public Map<User, Double> getUsersCashBalance() {
		return usersCashBalance;
	}

	public void setUsersCashBalance(Map<User, Double> usersCashBalance) {
		this.usersCashBalance = usersCashBalance;
	}

	public List<Bill> getBillList() {
		return billList;
	}

	public void setBillList(List<Bill> billList) {
		this.billList = billList;
	}

	public double getTotalCashBalance() {
		return totalCashBalance;
	}

	public void setTotalCashBalance(double totalCashBalance) {
		this.totalCashBalance = totalCashBalance;
	}

	public void payBill(Bill theBill, Map<User, Double> usersCashBalance) {
		double needToPay = theBill.sharedPart(usersCashBalance.size());

		for (User key : usersCashBalance.keySet()) {
			theBill.setPayedBy(key);
			theBill.setHasPayed(true);
			double userNeedToPay = usersCashBalance.get(key);
			if (!usersCashBalance.containsKey(theBill.getPayedBy())) {
				userNeedToPay = userNeedToPay - needToPay;
				usersCashBalance.replace(key, userNeedToPay);
			} else {
				userNeedToPay = userNeedToPay + needToPay * (usersCashBalance.size() - 1);
				usersCashBalance.replace(theBill.getPayedBy(), userNeedToPay);
				key.addToList(theBill);
			}
		}
	}

	public void removeBillFromList(Bill theBill) {
		billList.remove(theBill);
		totalCashBalance = totalCashBalance - theBill.getCost();
	}
	
	public void addBillToList(Bill theBill) {
		billList.add(theBill);
		totalCashBalance = totalCashBalance + theBill.getCost();
	}
	
	
	

}

