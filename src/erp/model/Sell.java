package erp.model;

import java.util.Date;

public class Sell {

    private int id;
    private String customerName;
    private String customerAddress;
    private String customerPhone1;
    private String customerPhone2;
    private String customerPostCode;
    private String customerIM;
    private int customerIMType;
    private String customerIMComment;
    private double fee;
    private double feeReal;
    private Date createDate;
    private Date sendDate;
    private int expressId;
    private String commentExpress;
    private String commentInvoice;
    private String sender;
    private int status;
    private double discount;

    public Sell() {
        id = 0;
        customerName = "";
        customerAddress = "";
        customerPhone1 = "";
        customerPhone2 = "";
        customerPostCode = "";
        customerIM = "";
        customerIMType = 0;
        customerIMComment = "";
        fee = 0;
        feeReal = 0;
        createDate = new Date();
        sendDate = new Date();
        expressId = 0;
        commentExpress = "";
        commentInvoice = "";
        sender = "";
        status = 0;
        discount = 0;
    }

    public String getCustomerIM() {
        return customerIM;
    }

    public void setCustomerIM(String customerIM) {
        this.customerIM = customerIM;
    }

    public int getCustomerIMType() {
        return customerIMType;
    }

    public void setCustomerIMType(int customerIMType) {
        this.customerIMType = customerIMType;
    }

    public String getCustomerIMComment() {
        return customerIMComment;
    }

    public void setCustomerIMComment(String customerIMComment) {
        this.customerIMComment = customerIMComment;
    }

    public String getCommentExpress() {
        return commentExpress;
    }

    public void setCommentExpress(String commentExpress) {
        this.commentExpress = commentExpress;
    }

    public String getCommentInvoice() {
        return commentInvoice;
    }

    public void setCommentInvoice(String commentInvoice) {
        this.commentInvoice = commentInvoice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone1() {
        return customerPhone1;
    }

    public void setCustomerPhone1(String customerPhone1) {
        this.customerPhone1 = customerPhone1;
    }

    public String getCustomerPhone2() {
        return customerPhone2;
    }

    public void setCustomerPhone2(String customerPhone2) {
        this.customerPhone2 = customerPhone2;
    }

    public String getCustomerPostCode() {
        return customerPostCode;
    }

    public void setCustomerPostCode(String customerPostCode) {
        if (customerPostCode.length() > 6) {
            customerPostCode = customerPostCode.substring(0, 6);
        }
        this.customerPostCode = customerPostCode;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public double getFeeReal() {
        return feeReal;
    }

    public void setFeeReal(double feeReal) {
        this.feeReal = feeReal;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
