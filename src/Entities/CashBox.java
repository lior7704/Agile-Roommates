
package Entities;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GUI.AgileRoommatesFinals;
import GUI.FixedLengthStringIO;

public class CashBox implements AgileRoommatesFinals{

	private HashMap<User, Double> usersCashBalance;
	private List<Bill> billList;
	private double totalCashBalance;

	public CashBox(HashMap<User, Double> usersCashBalance, List<Bill> billList) {
		this.usersCashBalance = usersCashBalance;
		this.billList = billList;
	}

	public CashBox(List<User> residents) {
		usersCashBalance = new HashMap<User, Double>();
		for(User user : residents) {
			usersCashBalance.put(user, 0.0);
		}
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
		theBill.setPaidBy(currentUser);
		theBill.setIsPaid(true);
		totalCashBalance -= theBill.getCost();
		for (User user : usersCashBalance.keySet()) {
			if (user == currentUser) {
				double value = theBill.getCost() - theBill.sharedPart(usersCashBalance.size());
				usersCashBalance.put(user, usersCashBalance.get(user) + value);
			} else {
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
	
	public void writeCashBoxToFile(RandomAccessFile rf) {
		try {
			rf.setLength(0);
			rf.seek(0);
			rf.writeInt(billList.size());
			for (int i = 0; i < billList.size(); i++) {
				FixedLengthStringIO.writeFixedLengthString(billList.get(i).getNameOfBill(), LONG_STRING_SIZE,
						rf);
				FixedLengthStringIO.writeFixedLengthString(Double.toString(billList.get(i).getCost()),
						SHORT_INT_SIZE, rf);
				FixedLengthStringIO.writeFixedLengthString(billList.get(i).getDatesOfBill(), LONG_INT_SIZE,
						rf);
				if (billList.get(i).isPaid())
					rf.writeInt(billList.get(i).getPaidBy().getId());
				else
					rf.writeInt(0);
			}
			rf.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void readCashBoxFromFile(RandomAccessFile rf) throws IOException {
		rf.seek(0);
		int size = rf.readInt();
		for (int i = 0; i < size; i++) {
			String name = FixedLengthStringIO.readFixedLengthString(LONG_STRING_SIZE, rf);
			Double cost = Double.parseDouble(FixedLengthStringIO.readFixedLengthString(SHORT_INT_SIZE, rf));
			String date = FixedLengthStringIO.readFixedLengthString(LONG_INT_SIZE, rf);
			addBillToList(new Bill(name.trim(), cost, date.trim()));
			int userID = rf.readInt();
			if (userID != 0) {
				for (User user : usersCashBalance.keySet()) {
					if(user.getId() == userID) {
						billList.get(i).setPaidBy(user);
						break;
					}
				}
			}
		}
	}
}
