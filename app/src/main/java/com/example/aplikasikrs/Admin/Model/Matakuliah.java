package com.example.aplikasikrs.Admin.Model;

public class Matakuliah {
    private String kodeMk;
    private String namaMk;
    private String hari;
    private String sks;
    private String sesi;

    public Matakuliah(String kodeMk, String namaMk, String hari, String sks, String sesi) {
        this.kodeMk = kodeMk;
        this.namaMk = namaMk;
        this.hari = hari;
        this.sks = sks;
        this.sesi = sesi;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }

    public String getSesi() {
        return sesi;
    }

    public void setSesi(String sesi) {
        this.sesi = sesi;
    }
}
