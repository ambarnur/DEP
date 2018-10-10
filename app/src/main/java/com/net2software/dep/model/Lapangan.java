package com.net2software.dep.model;

public class Lapangan {
        private String id;
        private String nama, keterangan, gambar;

    public Lapangan() {
    }

        public Lapangan(String id, String nama, String keterangan, String gambar){
            this.id = id;
            this.nama = nama;
            this.keterangan = keterangan;
            this.gambar = gambar;

        }

        public String getId() {
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

        public String getKeterangan(){
        return keterangan;
        }

        public void setKeterangan(String keterangan){

        this.keterangan = keterangan;
        }

        public String getGambar(){
        return gambar;
        }
        public void setGambar(String gambar){
        this.gambar = gambar;
        }
}
