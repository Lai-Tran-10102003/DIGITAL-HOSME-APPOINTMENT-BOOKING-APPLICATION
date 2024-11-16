package com.example.Giang.model;

import java.io.Serializable;

public class SpecialistInfor implements Serializable {
    String specialistName;
    String specialistDescription;
    byte[] specialistImage;

    public SpecialistInfor(String specialistName, String specialistDescription, byte[] specialistImage) {
        this.specialistName = specialistName;
        this.specialistDescription = specialistDescription;
        this.specialistImage = specialistImage;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public String getSpecialistDescription() {
        return specialistDescription;
    }

    public void setSpecialistDescription(String specialistDescription) {
        this.specialistDescription = specialistDescription;
    }

    public byte[] getSpecialistImage() {
        return specialistImage;
    }

    public void setSpecialistImage(byte[] specialistImage) {
        this.specialistImage = specialistImage;
    }
}
