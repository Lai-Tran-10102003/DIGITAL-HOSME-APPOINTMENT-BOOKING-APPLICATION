package com.example.Chi.models;

import java.io.Serializable;

public class Bank implements Serializable {
    int bankThumb;
    String bankName;
    int bankCardImage;

    public Bank(int bankThumb, String bankName, int bankCardImage) {
        this.bankThumb = bankThumb;
        this.bankName = bankName;
        this.bankCardImage = bankCardImage;
    }
    public int getBankThumb() {
        return bankThumb;
    }

    public void setBankThumb(int bankThumb) {
        this.bankThumb = bankThumb;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getBankCardImage() {
        return bankCardImage;
    }

    public void setBankCardImage(int bankCardImage) {
        this.bankCardImage = bankCardImage;
    }
}
