package erp.model;

public class Ware {

    private int id;
    private int categoryId;
    private int categoryIdOld;
    private String name;
    private double cost;
    private double lowestCost;
    private double lastPrice;
    private String barcode;
    private int number;
    private int status;
    private int numberAlarm;
    private int numberAlarmEnable;

    public Ware() {
        id = 0;
        categoryId = 0;
        categoryIdOld = 0;
        name = "";
        cost = 0;
        lowestCost = 0;
        lastPrice = 0;
        barcode = "";
        number = 0;
        status = 0;
        numberAlarm = 0;
        numberAlarmEnable = 1;
    }

    public int getNumberAlarmEnable() {
        return numberAlarmEnable;
    }

    public void setNumberAlarmEnable(int e) {
        this.numberAlarmEnable = e;
    }
    
    public void setNumberAlarmEnable(boolean e) {
        this.numberAlarm = e ? 1 : 0;
    }
    
    public void setNumberAlarmEnable(String e) {
        this.numberAlarm = e.toLowerCase().indexOf("true") > -1 ? 1 : 0;
    }

    public int getNumberAlarm() {
        return numberAlarm;
    }
    
    public void setNumberAlarm(int numberAlarm) {
        this.numberAlarm = numberAlarm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryIdOld = this.categoryId;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCategoryIdOld() {
        return categoryIdOld;
    }

    public double getLowestCost() {
        return lowestCost;
    }

    public void setLowestCost(double lowestCost) {
        this.lowestCost = lowestCost;
    }

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

}
