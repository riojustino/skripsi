package com.justino.sistempakar.auth;

import com.google.gson.annotations.SerializedName;

public class ConfirmDeleteUsers {
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("nama_user")
    private String nama_user;

    public ConfirmDeleteUsers(String id_user, String nama_user) {
        this.id_user = id_user;
        this.nama_user = nama_user;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }
}
