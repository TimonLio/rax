package erp.model;

import java.util.Date;

public class InvoiceItem {

	private int number;
	private Date date;
	private String name;
	private String byerId;
	private String byerName;
	double price;
	double exFee;

	public InvoiceItem() {
		number = 0;
		date = new Date();
		name = "";
		byerId = "";
		byerName = "";
		price = 0;
		exFee = 0;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getByerId() {
		return byerId;
	}

	public void setByerId(String byerId) {
		this.byerId = byerId;
	}

	public String getByerName() {
		return byerName;
	}

	public void setByerName(String byerName) {
		this.byerName = byerName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getExFee() {
		return exFee;
	}

	public void setExFee(double exFee) {
		this.exFee = exFee;
	}

}
