
package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashBox {

	private HashMap<User, Double> usersCashBalance;
	private List<Bill> billList;
	private double totalCashBalance;

	public CashBox(HashMap<User, Double> usersCashBalance, List<Bill> billList) {
		this.usersCashBalance = usersCashBalance;
		this.billList = billList;
	}
	
	public CashBox() {
		usersCashBalance = new HashMap<User, Double>();
		billList = new ArrayList<Bill>();
		totalCashBalance = 0;
	}

	public Map<User, Double> getUsersCashBalance() {
		return usersCashBalance;
	}

	public void setUsersCashBalance(HashMap<User, Double> usersCashBalance) {
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

	public void payBill(Bill theBill, User currentUser) {
		theBill.setPayedBy(currentUser);
		theBill.setIsPayed(true);
		totalCashBalance -= theBill.getCost();
		for (User user : usersCashBalance.keySet()) {
			if(user == currentUser) {
				double value = theBill.getCost() - theBill.sharedPart(usersCashBalance.size());
				usersCashBalance.put(user, usersCashBalance.get(user) + value);
			}
			else {
				double value = theBill.sharedPart(usersCashBalance.size());
				usersCashBalance.put(user, usersCashBalance.get(user) - value);
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

