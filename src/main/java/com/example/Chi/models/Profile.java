package com.example.Chi.models;

import java.io.Serializable;

public class Profile implements Serializable {
    String PatientID;
    String PatientName;
    String PhoneNumber;
    String PatientIDCard;
    String PatientDOB;
    String PatientGender;
    String PatientAddress;
    String PatientEmail;

    public Profile(String patientID, String patientName, String phoneNumber, String patientIDCard, String patientDOB, String patientGender, String patientAddress, String patientEmail) {
        PatientID = patientID;
        PatientName = patientName;
        PhoneNumber = phoneNumber;
        PatientIDCard = patientIDCard;
        PatientDOB = patientDOB;
        PatientGender = patientGender;
        PatientAddress = patientAddress;
        PatientEmail = patientEmail;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPatientIDCard() {
        return PatientIDCard;
    }

    public void setPatientIDCard(String patientIDCard) {
        PatientIDCard = patientIDCard;
    }

    public String getPatientDOB() {
        return PatientDOB;
    }

    public void setPatientDOB(String patientDOB) {
        PatientDOB = patientDOB;
    }

    public String getPatientGender() {
        return PatientGender;
    }

    public void setPatientGender(String patientGender) {
        PatientGender = patientGender;
    }

    public String getPatientAddress() {
        return PatientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        PatientAddress = patientAddress;
    }

    public String getPatientEmail() {
        return PatientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        PatientEmail = patientEmail;
    }
}

