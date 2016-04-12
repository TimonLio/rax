package erp.model;

import java.util.Date;

public class StatWare {

	private int id;
	private Date lastBuyDate;
	private Date lastSellDate;

	public StatWare() {
		this.id = 0;
		this.lastBuyDate = new Date();
		this.lastSellDate = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLastBuyDate() {
		return lastBuyDate;
	}

	public void setLastBuyDate(Date lastBuyDate) {
		this.lastBuyDate = lastBuyDate;
	}

	public Date getLastSellDate() {
		return lastSellDate;
	}

	public void setLastSellDate(Date lastSellDate) {
		this.lastSellDate = lastSellDate;
	}

}
