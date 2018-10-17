package com.net2software.dep.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable{
    private int id;
    private String nama, no_hp, lapangan, tempat;
    private String tanggal, jam, status;

    public Order(){
    }

    protected Order(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        no_hp = in.readString();
        lapangan = in.readString();
        tempat = in.readString();
        tanggal = in.readString();
        jam = in.readString();
        status = in.readString();
    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getLapangan() {
        return lapangan;
    }

    public void setLapangan(String lapangan) {
        this.lapangan = lapangan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(no_hp);
        dest.writeString(lapangan);
        dest.writeString(tempat);
        dest.writeString(tanggal);
        dest.writeString(jam);
        dest.writeString(status);

    }
}
