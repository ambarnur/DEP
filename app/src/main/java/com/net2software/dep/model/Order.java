package com.net2software.dep.model;

public class Order {
    private String id, tglmain;
    private String nama, no_hp, lapangan, tempat;
    private String tanggal, jam, status;

    public Order(){

    }

    public Order(String id, String nama,String tglmain, String no_hp, String lapangan, String tempat, String tanggal, String jam, String status) {
        this.id = id ;
        this.nama = nama ;
        this.no_hp = no_hp ;
        this.lapangan = lapangan ;
        this.tempat = tempat ;
        this.tanggal = tanggal ;
        this.jam = jam ;
        this.status = status ;
        this.tglmain = tglmain;
    }


    public String getId(){
        return id;
    }

    public void setId(String id) {
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


    public String getTglmain() {
        return tglmain;
    }

    public void setTglmain(String tglmain) {
        this.tglmain = tglmain;
    }

}
