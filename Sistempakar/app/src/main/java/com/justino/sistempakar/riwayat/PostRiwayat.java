package com.justino.sistempakar.riwayat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostRiwayat {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Riwayat> riwayatList;
    @SerializedName("message")
    String message;

    public PostRiwayat(String status, List<Riwayat> riwayatList, String message) {
        this.status = status;
        this.riwayatList = riwayatList;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Riwayat> getRiwayatList() {
        return riwayatList;
    }

    public void setRiwayatList(List<Riwayat> riwayatList) {
        this.riwayatList = riwayatList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
