package com.ccbupt.entity;

public class PaymentEntity {
    private int id;
    private int userid;       // 业主ID
    private int houseid;      // 房屋ID
    private String paytype;   // 缴费类型（物业费、水电费、停车费等）
    private double amount;    // 缴费金额
    private String paydate;   // 缴费日期
    private String status;    // 缴费状态（已缴、未缴、逾期等）
    private String remarks;   // 备注

    // 构造函数
    public PaymentEntity() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public String getPaytype() {
        return paytype;
    }

    public void setPaytype(String paytype) {
        this.paytype = paytype;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}


