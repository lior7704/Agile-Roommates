package Entities;
public class Product {
	private String nameOfProduct;
	private int amount;

	public Product(String nameOfProduct, int amount) {
		super();
		this.nameOfProduct = nameOfProduct;
		this.amount = amount;
	}

	public String getNameOfProduct() {
		return nameOfProduct;
	}

	public void setNameOfProduct(String nameOfProduct) {
		this.nameOfProduct = nameOfProduct;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return nameOfProduct +" (" + amount + ")";
	}

}