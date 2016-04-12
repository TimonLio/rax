package erp.model;

public class StatRankList {

	private double totalPrice;
	private int totalNumber;
	private int wareId;
	private String wareName;
	private int wareNumber;

	public StatRankList() {
		totalPrice = 0;
		totalNumber = 0;
		wareId = 0;
		wareName = "";
		wareNumber = 0;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public int getWareId() {
		return wareId;
	}

	public void setWareId(int wareId) {
		this.wareId = wareId;
	}

	public String getWareName() {
		return wareName;
	}

	public void setWareName(String wareName) {
		this.wareName = wareName;
	}

	public int getWareNumber() {
		return wareNumber;
	}

	public void setWareNumber(int wareNumber) {
		this.wareNumber = wareNumber;
	}

}
