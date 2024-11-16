package com.example.Giang.model;

public class Specialist {
    int specialistThumb;
    String specialistName;

    public Specialist(int specialistThumb, String specialistName) {
        this.specialistThumb = specialistThumb;
        this.specialistName = specialistName;
    }

    public int getSpecialistThumb() {
        return specialistThumb;
    }

    public void setSpecialistThumb(int specialistThumb) {
        this.specialistThumb = specialistThumb;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }
}
