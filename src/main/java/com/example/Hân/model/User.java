package com.example.Hân.model;

import java.io.Serializable;

public class User implements Serializable {
    String userID;
    String userName;
    String phoneNumber;
    String password;

    public User(String userID, String userName, String phoneNumber) {
        this.userID = userID;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public User(String password) {
        this.password = password;
    }

    public String getUserID() {return userID;}

    public void setUserID(String patientID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}
