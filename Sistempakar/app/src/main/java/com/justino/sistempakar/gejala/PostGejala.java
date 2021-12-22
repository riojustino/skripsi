package com.justino.sistempakar.gejala;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostGejala{
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Gejala> gejalaList;
    @SerializedName("message")
    String message;

    public PostGejala(String status, List<Gejala> gejalaList, String message) {
        this.status = status;
        this.gejalaList = gejalaList;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Gejala> getGejalaList() {
        return gejalaList;
    }

    public void setGejalaList(List<Gejala> gejalaList) {
        this.gejalaList = gejalaList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
