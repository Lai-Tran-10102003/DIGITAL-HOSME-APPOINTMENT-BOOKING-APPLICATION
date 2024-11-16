package com.example.Trân.model;

import java.io.Serializable;

public class User implements Serializable {
    int UserID;
    String UserName;
    int PhoneNumber;
    String DayofBirth;
    String Gender;
    String Email;
    String Password;
    byte[] UserImage;

    public User(int userID, String userName, int phoneNumber, String dayofBirth, String gender, String email, String password, byte[] userImage) {
        UserID = userID;
        UserName = userName;
        PhoneNumber = phoneNumber;
        DayofBirth = dayofBirth;
        Gender = gender;
        Email = email;
        Password = password;
        UserImage = userImage;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getDayofBirth() {
        return DayofBirth;
    }

    public void setDayofBirth(String dayofBirth) {
        DayofBirth = dayofBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public byte[] getUserImage() {
        return UserImage;
    }

    public void setUserImage(byte[] userImage) {
        UserImage = userImage;
    }

}
