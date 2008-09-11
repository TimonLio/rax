package erp.model;

import java.util.Date;

public class Stat {

    private Date statDate;
    private double amount;
    private double profit;
    private int number;
    private double fee;
    private double feeReal;

    public Stat() {
        statDate = new Date();
        amount = 0;
        profit = 0;
        number = 0;
        fee = 0;
        feeReal = 0;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getFeeReal() {
        return feeReal;
    }

    public void setFeeReal(double feeReal) {
        this.feeReal = feeReal;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
