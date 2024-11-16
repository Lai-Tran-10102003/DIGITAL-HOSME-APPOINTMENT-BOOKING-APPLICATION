package com.example.Giang.model;

public class Doctor {
    int doctorThumb;
    String doctorName;

    public Doctor(int doctorThumb, String doctorName) {
        this.doctorThumb = doctorThumb;
        this.doctorName = doctorName;
    }

    public int getDoctorThumb() {
        return doctorThumb;
    }

    public void setDoctorThumb(int doctorThumb) {
        this.doctorThumb = doctorThumb;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}
