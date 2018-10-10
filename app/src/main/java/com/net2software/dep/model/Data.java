package com.net2software.dep.model;

public class Data {
    private String  nama, gambar;
    private int id;

    public Data() {
    }

    public Data(int id, String nama, String gambar) {
        this.id = id;
        this.nama = nama;
        this.gambar = gambar;
    }

    public int getId() {
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

    public  String getGambar(){
        return gambar;
    }
    public void setGambar(String gambar){
        this.gambar = gambar;
    }
    @Override
    public String toString() {
        return nama;
    }
}