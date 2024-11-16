package com.example.Giang.model;

import java.io.Serializable;

public class DoctorInfor implements Serializable {
    String doctorID;
    String doctorName;
    String specialistName;
    String doctorDescription;
    byte[] DoctorImage;

    public DoctorInfor(String doctorID, String doctorName, String specialistName, String doctorDescription, byte[] doctorImage) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.specialistName = specialistName;
        this.doctorDescription = doctorDescription;
        DoctorImage = doctorImage;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getDoctorDescription() {
        return doctorDescription;
    }

    public void setDoctorDescription(String doctorDescription) {
        this.doctorDescription = doctorDescription;
    }

    public byte[] getDoctorImage() {
        return DoctorImage;
    }

    public void setDoctorImage(byte[] doctorImage) {
        DoctorImage = doctorImage;
    }
}
