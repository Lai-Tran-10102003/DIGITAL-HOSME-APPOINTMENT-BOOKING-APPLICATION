package com.example.Thảo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Chuyenkhoa implements Parcelable {
    private int ChuyenKhoaID;
    private String ChuyenKhoaName;
    private double PriceService;

    public Chuyenkhoa(int chuyenKhoaID, String chuyenKhoaName, double priceService) {
        ChuyenKhoaID = chuyenKhoaID;
        ChuyenKhoaName = chuyenKhoaName;
        PriceService = priceService;
    }

    public int getChuyenKhoaID() {
        return ChuyenKhoaID;
    }

    public void setChuyenKhoaID(int chuyenKhoaID) {
        ChuyenKhoaID = chuyenKhoaID;
    }

    public String getChuyenKhoaName() {
        return ChuyenKhoaName;
    }

    public void setChuyenKhoaName(String chuyenKhoaName) {
        ChuyenKhoaName = chuyenKhoaName;
    }

    public Double getPriceService() {
        return PriceService;
    }

    public void setPriceService(double priceService) {
        PriceService = priceService;
    }

    // Implement the describeContents() method
    @Override
    public int describeContents() {
        return 0;
    }

    // Implement the writeToParcel() method
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ChuyenKhoaID);
        dest.writeString(ChuyenKhoaName);
        dest.writeDouble(PriceService);
    }

    // Implement the CREATOR field
    public static final Parcelable.Creator<Chuyenkhoa> CREATOR = new Parcelable.Creator<Chuyenkhoa>() {
        public Chuyenkhoa createFromParcel(Parcel in) {
            return new Chuyenkhoa(in);
        }

        public Chuyenkhoa[] newArray(int size) {
            return new Chuyenkhoa[size];
        }
    };

    // Private constructor for reading from Parcel
    private Chuyenkhoa(Parcel in) {
        ChuyenKhoaID = in.readInt();
        ChuyenKhoaName = in.readString();
        PriceService = in.readDouble();
    }
}
