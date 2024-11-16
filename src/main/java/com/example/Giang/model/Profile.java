package com.example.Giang.model;

import java.io.Serializable;

public class Profile implements Serializable {
    String patientID;
    String patientName;
    String phoneNumber;

    public Profile(String patientID, String patientName, String phoneNumber) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
