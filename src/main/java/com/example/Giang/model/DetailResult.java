package com.example.Giang.model;

import java.io.Serializable;

public class DetailResult  implements Serializable {

    String patientID;
    String patientName;
    String mainExam;
    String dateTimeIn;
    String dateTimeOut;
    String address;
    String doctor;
    byte[] resultImage;

    public DetailResult(String patientID, String patientName, String mainExam, String dateTimeIn, String dateTimeOut, String address, String doctor, byte[] resultImage) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.mainExam = mainExam;
        this.dateTimeIn = dateTimeIn;
        this.dateTimeOut = dateTimeOut;
        this.address = address;
        this.doctor = doctor;
        this.resultImage = resultImage;
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

    public String getMainExam() {
        return mainExam;
    }

    public void setMainExam(String mainExam) {
        this.mainExam = mainExam;
    }

    public String getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(String dateTimeIn) {
        this.dateTimeIn = dateTimeIn;
    }

    public String getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(String dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public byte[] getResultImage() {
        return resultImage;
    }

    public void setResultImage(byte[] resultImage) {
        this.resultImage = resultImage;
    }
}
