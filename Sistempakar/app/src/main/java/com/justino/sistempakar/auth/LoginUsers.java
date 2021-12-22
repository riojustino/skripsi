package com.justino.sistempakar.auth;

import com.google.gson.annotations.SerializedName;

public class LoginUsers {
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("jabatan_user")
    private String jabatan_user;
    @SerializedName("status_user")
    private String status_user;

    public LoginUsers(String id_user, String jabatan_user) {
        this.id_user = id_user;
        this.jabatan_user = jabatan_user;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getJabatan_user() {
        return jabatan_user;
    }

    public void setJabatan_user(String jabatan_user) {
        this.jabatan_user = jabatan_user;
    }

}
