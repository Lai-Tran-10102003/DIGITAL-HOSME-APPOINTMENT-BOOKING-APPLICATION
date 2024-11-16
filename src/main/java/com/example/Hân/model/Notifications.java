package com.example.Hân.model;

import java.io.Serializable;

public class Notifications implements Serializable {
    int NoID;
    String NoTitle;
    String NoDes;

    public Notifications(int noID, String noTitle, String noDes) {
        NoID = noID;
        NoTitle = noTitle;
        NoDes = noDes;
    }

    public int getNoID() {
        return NoID;
    }

    public void setNoID(int noID) {
        NoID = noID;
    }

    public String getNoTitle() {
        return NoTitle;
    }

    public void setNoTitle(String noTitle) {
        NoTitle = noTitle;
    }

    public String getNoDes() {
        return NoDes;
    }

    public void setNoDes(String noDes) {
        NoDes = noDes;
    }
}
