package com.example.Giang.model;

import java.io.Serializable;

public class ResultMedicalRecord implements Serializable {
    String patientID;
    String patientName;
    String specialist;
    String dateTimeIn;
    String dateTimeOut;
    String predict;
    String prescription;
    String doctor;

    public ResultMedicalRecord(String patientID, String patientName, String specialist, String dateTimeIn, String dateTimeOut, String predict, String prescription, String doctor) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.specialist = specialist;
        this.dateTimeIn = dateTimeIn;
        this.dateTimeOut = dateTimeOut;
        this.predict = predict;
        this.prescription = prescription;
        this.doctor = doctor;
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

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
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

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
