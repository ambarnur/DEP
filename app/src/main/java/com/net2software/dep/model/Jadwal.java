package com.net2software.dep.model;

public class Jadwal {
        private String id;
        private String jam;
        private String harga;

        public Jadwal(){

    }

        public Jadwal(String id, String jam, String harga){
            this.id = id;
            this.jam = jam;
            this.harga = harga;

        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getJam() {
            return jam;
        }

        public void setJam(String jam) {
            this.jam = jam;
        }

        public String getHarga(){
            return harga;
        }

        public void setHarga(String harga)
        {
            this.harga = harga;
        }
}
