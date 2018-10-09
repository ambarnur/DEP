package com.net2software.dep.model;

public class Jadwal {
        private String id;
        private String jam;

        public Jadwal(){

    }

        public Jadwal(String id, String jam){
            this.id = id;
            this.jam = jam;

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
}
