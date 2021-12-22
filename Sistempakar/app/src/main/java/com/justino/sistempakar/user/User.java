package com.justino.sistempakar.user;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id_user")
    private String idUser;
    @SerializedName("nama_user")
    private String namaUser;
    @SerializedName("nohp_user")
    private String nohpUser;
    @SerializedName("jabatan_user")
    private String jabatanUser;
    @SerializedName("email_user")
    private String emailUser;

    public User(String idUser, String namaUser, String nohpUser, String jabatanUser, String emailUser) {
        this.idUser = idUser;
        this.namaUser = namaUser;
        this.nohpUser = nohpUser;
        this.jabatanUser = jabatanUser;
        this.emailUser = emailUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getNohpUser() {
        return nohpUser;
    }

    public void setNohpUser(String nohpUser) {
        this.nohpUser = nohpUser;
    }

    public String getJabatanUser() {
        return jabatanUser;
    }

    public void setJabatanUser(String jabatanUser) {
        this.jabatanUser = jabatanUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
