package com.ocr.applinhkien.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Order {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("sanpham")
    private ArrayList<Item> listItem;
    @SerializedName("total_money")
    private int totalPrice;
    @SerializedName("email")
    private String email;
    @SerializedName("sdt")
    private String phoneNumber;
    @SerializedName("diachi")
    private String address;

    @SerializedName("create_at")
    private String createAt;

    @SerializedName("note")
    private String note;

    public Order(String userId, ArrayList<Item> listItem, int totalPrice, String email, String phoneNumber, String address, String createAt, String note) {
        this.userId = userId;
        this.listItem = listItem;
        this.totalPrice = totalPrice;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createAt = createAt;
        this.note = note;
    }

    public Order() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
