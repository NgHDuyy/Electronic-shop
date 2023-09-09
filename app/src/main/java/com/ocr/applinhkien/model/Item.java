package com.ocr.applinhkien.model;

public class Item {
    private int id;
    private String name;
    private String urlImage;
    private String mota;
    private int price;
    private int soluong;

    public Item(int id,String name, String urlImage, String mota, int price, int soluong) {
        this.id = id;
        this.urlImage = urlImage;
        this.mota = mota;
        this.price = price;
        this.soluong = soluong;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
