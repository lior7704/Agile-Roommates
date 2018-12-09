
package Entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashBox {

	private Map<User, Double> usersCashBalance;
	private List<Bill> billList;
	private double totalCashBalance;

	public CashBox(Map<User, Double> usersCashBalance, List<Bill> billList) {
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

	public void payBill(Bill theBill, User user) {
		int i=0;
		for(Bill b: billList) {
			if(b.getNameOfBill()==theBill.getNameOfBill())
				b.setPayedBy(user);
		}
		
		theBill.setPayedBy(user);
		theBill.setIsPayed(true);
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

