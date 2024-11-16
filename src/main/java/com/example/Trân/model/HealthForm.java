package com.example.Trân.model;

import java.io.Serializable;

public class HealthForm implements Serializable {
    int bookID;
    int userID;
    String patientID;
    String patientName;
    int departmentID;
    String departmentName;
    String addressHos;
    int queue;
    String room;
    String date;
    String time;
    String status;
    String ngaySinh;
    String gioitinh;
    String phoneNumber;


    public HealthForm(int bookID, int userID, String patientID, String patientName, int departmentID, String departmentName, String addressHos, int queue, String room, String date, String time, String status, String ngaySinh, String gioitinh, String phoneNumber) {
        this.bookID = bookID;
        this.userID = userID;
        this.patientID = patientID;
        this.patientName = patientName;
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.addressHos = addressHos;
        this.queue = queue;
        this.room = room;
        this.date = date;
        this.time = time;
        this.status = status;
        this.ngaySinh = ngaySinh;
        this.gioitinh = gioitinh;
        this.phoneNumber = phoneNumber;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAddressHos() {
        return addressHos;
    }

    public void setAddressHos(String addressHos) {
        this.addressHos = addressHos;
    }

    public int getQueue() {
        return queue;
    }

    public void setQueue(int queue) {
        this.queue = queue;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

