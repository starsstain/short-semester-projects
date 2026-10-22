package com.ccbupt.entity;

public class CartEntity {
    private Integer id;
    private Integer goodsid;
    private Integer userid;
    private Integer num;
    private Integer ordersid;   // 0=购物车，>0=已下单

    /* 下面是 Getter/Setter，省略构造 */
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getGoodsid() { return goodsid; }
    public void setGoodsid(Integer goodsid) { this.goodsid = goodsid; }
    public Integer getUserid() { return userid; }
    public void setUserid(Integer userid) { this.userid = userid; }
    public Integer getNum() { return num; }
    public void setNum(Integer num) { this.num = num; }
    public Integer getOrdersid() { return ordersid; }
    public void setOrdersid(Integer ordersid) { this.ordersid = ordersid; }
}