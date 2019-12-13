package com.example.krskoe.Admin.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mahasiswa {
    @SerializedName("nim")
    @Expose
    private String nim;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String emailMhs;
    @SerializedName("alamat")
    @Expose
    private String alamatMhs;
    @SerializedName("foto")
    @Expose
    private String foto;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("nim_progmob")
    @Expose
    private String nim_progmob;

    public Mahasiswa(String nim, String nama, String emailMhs, String alamatMhs, String foto, String id, String nim_progmob) {
        this.nim = nim;
        this.nama = nama;
        this.emailMhs = emailMhs;
        this.alamatMhs = alamatMhs;
        this.foto = foto;
        this.id = id;
        this.nim_progmob = nim_progmob;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmailMhs() {
        return emailMhs;
    }

    public void setEmailMhs(String emailMhs) {
        this.emailMhs = emailMhs;
    }

    public String getAlamatMhs() {
        return alamatMhs;
    }

    public void setAlamatMhs(String alamatMhs) {
        this.alamatMhs = alamatMhs;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim_progmob() {
        return nim_progmob;
    }
    public void setNim_progmob(String nim_progmob) {
        this.nim_progmob = nim_progmob;
    }
}
