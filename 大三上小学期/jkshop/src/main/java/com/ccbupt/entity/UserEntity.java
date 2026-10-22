package com.ccbupt.entity;

public class UserEntity {
    private int id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String address; // 新增地址字段

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() { // 新增getter方法
        return address;
    }

    public void setAddress(String address) { // 新增setter方法
        this.address = address;
    }
}
