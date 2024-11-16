package com.example.Thảo.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Bacsi implements Parcelable {
    int BacsiID;
    String BacsiName;
    String Lichkham;

    public Bacsi(int bacsiID, String bacsiName, String lichkham) {
        BacsiID = bacsiID;
        BacsiName = bacsiName;
        Lichkham = lichkham;
    }

    public int getBacsiID() {
        return BacsiID;
    }

    public void setBacsiID(int bacsiID) {
        BacsiID = bacsiID;
    }

    public String getBacsiName() {
        return BacsiName;
    }

    public void setBacsiName(String bacsiName) {
        BacsiName = bacsiName;
    }

    public String getLichkham() {
        return Lichkham;
    }

    public void setLichkham(String lichkham) {
        Lichkham = lichkham;
    }

    // Implement the describeContents() method
    @Override
    public int describeContents() {
        return 0;
    }

    // Implement the writeToParcel() method
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(BacsiID);
        dest.writeString(BacsiName);
        dest.writeString(Lichkham);
    }

    public static final Parcelable.Creator<Bacsi> CREATOR = new Parcelable.Creator<Bacsi>() {
        public Bacsi createFromParcel(Parcel in) {
            return new Bacsi(in);
        }

        public Bacsi[] newArray(int size) {
            return new Bacsi[size];
        }
    };

    // Private constructor for reading from Parcel
    private Bacsi(Parcel in) {
        BacsiID = in.readInt();
        BacsiName = in.readString();
        Lichkham = in.readString();
    }

}
