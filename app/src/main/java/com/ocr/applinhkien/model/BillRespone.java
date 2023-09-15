package com.ocr.applinhkien.model;

import java.util.ArrayList;

public class BillRespone {
    private boolean success;
    private String message;
    private ArrayList<Bill> listBill;

    public BillRespone(boolean success, String message, ArrayList<Bill> listBill) {
        this.success = success;
        this.message = message;
        this.listBill = listBill;
    }

    public BillRespone() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Bill> getListBill() {
        return listBill;
    }

    public void setListBill(ArrayList<Bill> listBill) {
        this.listBill = listBill;
    }
}

