package com.ocr.applinhkien.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Bill {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("order_id")
    private String orderId;
    @SerializedName("soluong")
    private String soluong;
    @SerializedName("total_money")
    private String totalMoney;
    @SerializedName("email")
    private String email;
    @SerializedName("sdt")
    private String phoneNumber;
    @SerializedName("diachi")
    private String address;
    @SerializedName("note")
    private String note;
    @SerializedName("create_at")
    private String createAt;
    @SerializedName("item")
    private ArrayList<Item> listItem;

    public Bill(String userId, String orderId, String soluong, String totalMoney, String email, String phoneNumber, String address, String note, String createAt, ArrayList<Item> listItem) {
        this.userId = userId;
        this.orderId = orderId;
        this.soluong = soluong;
        this.totalMoney = totalMoney;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.note = note;
        this.createAt = createAt;
        this.listItem = listItem;
    }

    public Bill() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }
}
