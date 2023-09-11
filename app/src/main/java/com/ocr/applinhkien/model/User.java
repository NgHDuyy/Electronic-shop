package com.ocr.applinhkien.model;

import java.io.Serializable;

public class User implements Serializable {

    private String user_id;

    public User() {
    }

    public User(String user_id, String username, String gender, String phoneNumber, String address, String city, String email, String password) {
        this.user_id = user_id;
        this.username = username;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.email = email;
        this.password = password;
    }

    private String username;
    private String gender;
    private String dateofbirth;
    private String phoneNumber;
    private String address;
    private String city;
    private String email;
    private String password;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", dateofbirth='" + dateofbirth + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

