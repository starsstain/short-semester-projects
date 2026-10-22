package com.ccbupt.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 报修实体类
 * 与数据库 t_repair 表字段一一对应
 */
public class RepairEntity {
    // 原有字段
    private Integer id;                // 报修记录ID
    private Integer houseid;           // 房屋ID，关联t_house表
    private Integer userid;            // 报修人ID，关联t_user表
    private String description;        // 报修描述
    private String status;             // 状态：0=待处理，1=处理中，2=已完成

    // 新增字段（对应数据库表新增列）
    private String reporter;           // 报修人姓名
    private String phone;              // 报修人联系电话
    private String address;            // 报修地址
    private BigDecimal cost;           // 维修费用（对应数据库decimal类型）
    private Date repairDate;           // 维修日期（对应数据库date类型）
    private Date createdTime;          // 创建时间（对应数据库timestamp类型）
    private Date updatedTime;          // 更新时间（对应数据库timestamp类型）

    /* Getter/Setter 方法 - 包含所有字段 */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseid() {
        return houseid;
    }

    public void setHouseid(Integer houseid) {
        this.houseid = houseid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}