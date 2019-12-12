package com.example.aplikasikrs.Mahasiswa.Model;

public class Kelas {
    private String kodeMkKelas;
    private String namaMkKelas;
    private String sksMkKelas;
    private String hariMkKelas;
    private String dosenKelas;
    private String jmlMhsKelas;

    public Kelas(String kodeMkKelas, String namaMkKelas, String sksMkKelas, String hariMkKelas, String dosenKelas, String jmlMhsKelas) {
        this.kodeMkKelas = kodeMkKelas;
        this.namaMkKelas = namaMkKelas;
        this.sksMkKelas = sksMkKelas;
        this.hariMkKelas = hariMkKelas;
        this.dosenKelas = dosenKelas;
        this.jmlMhsKelas = jmlMhsKelas;
    }

    public String getKodeMkKelas() {
        return kodeMkKelas;
    }

    public void setKodeMkKelas(String kodeMkKelas) {
        this.kodeMkKelas = kodeMkKelas;
    }

    public String getNamaMkKelas() {
        return namaMkKelas;
    }

    public void setNamaMkKelas(String namaMkKelas) {
        this.namaMkKelas = namaMkKelas;
    }

    public String getSksMkKelas() {
        return sksMkKelas;
    }

    public void setSksMkKelas(String sksMkKelas) {
        this.sksMkKelas = sksMkKelas;
    }

    public String getHariMkKelas() {
        return hariMkKelas;
    }

    public void setHariMkKelas(String hariMkKelas) {
        this.hariMkKelas = hariMkKelas;
    }

    public String getDosenKelas() {
        return dosenKelas;
    }

    public void setDosenKelas(String dosenKelas) {
        this.dosenKelas = dosenKelas;
    }

    public String getJmlMhsKelas() {
        return jmlMhsKelas;
    }

    public void setJmlMhsKelas(String jmlMhsKelas) {
        this.jmlMhsKelas = jmlMhsKelas;
    }
}
