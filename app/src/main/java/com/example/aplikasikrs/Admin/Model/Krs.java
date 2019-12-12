package com.example.aplikasikrs.Admin.Model;

public class Krs {
    private String kodeMkKrs;
    private String namaMkKrs;
    private String hariKrs;
    private String sksKrs;
    private String sesiKrs;
    private String namaDosenKrs;
    private String jmlMhs;

    public Krs(String kodeMkKrs, String namaMkKrs, String hariKrs, String sksKrs, String sesiKrs, String namaDosenKrs, String jmlMhs) {
        this.kodeMkKrs = kodeMkKrs;
        this.namaMkKrs = namaMkKrs;
        this.hariKrs = hariKrs;
        this.sksKrs = sksKrs;
        this.sesiKrs = sesiKrs;
        this.namaDosenKrs = namaDosenKrs;
        this.jmlMhs = jmlMhs;
    }

    public String getKodeMkKrs() {
        return kodeMkKrs;
    }

    public void setKodeMkKrs(String kodeMkKrs) {
        this.kodeMkKrs = kodeMkKrs;
    }

    public String getNamaMkKrs() {
        return namaMkKrs;
    }

    public void setNamaMkKrs(String namaMkKrs) {
        this.namaMkKrs = namaMkKrs;
    }

    public String getHariKrs() {
        return hariKrs;
    }

    public void setHariKrs(String hariKrs) {
        this.hariKrs = hariKrs;
    }

    public String getSksKrs() {
        return sksKrs;
    }

    public void setSksKrs(String sksKrs) {
        this.sksKrs = sksKrs;
    }

    public String getSesiKrs() {
        return sesiKrs;
    }

    public void setSesiKrs(String sesiKrs) {
        this.sesiKrs = sesiKrs;
    }

    public String getNamaDosenKrs() {
        return namaDosenKrs;
    }

    public void setNamaDosenKrs(String namaDosenKrs) {
        this.namaDosenKrs = namaDosenKrs;
    }

    public String getJmlMhs() {
        return jmlMhs;
    }

    public void setJmlMhs(String jmlMhs) {
        this.jmlMhs = jmlMhs;
    }
}
