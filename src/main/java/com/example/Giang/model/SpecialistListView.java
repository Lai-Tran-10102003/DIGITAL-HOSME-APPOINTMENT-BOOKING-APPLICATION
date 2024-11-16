package com.example.Giang.model;

import java.io.Serializable;

public class SpecialistListView implements Serializable {
    byte[] specialistThumb;
    String specialistName;

    public SpecialistListView(byte[] specialistThumb, String specialistName) {
        this.specialistThumb = specialistThumb;
        this.specialistName = specialistName;
    }

    public byte[] getSpecialistThumb() {
        return specialistThumb;
    }

    public void setSpecialistThumb(byte[] specialistThumb) {
        this.specialistThumb = specialistThumb;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }
}
