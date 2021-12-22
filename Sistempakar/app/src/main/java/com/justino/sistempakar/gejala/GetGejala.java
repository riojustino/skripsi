package com.justino.sistempakar.gejala;

import com.google.gson.annotations.SerializedName;
import com.justino.sistempakar.penyakit.Penyakit;

import java.util.List;

public class GetGejala {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Gejala> listDataGejala;
    @SerializedName("message")
    String message;

    public GetGejala(String status, List<Gejala> listDataGejala, String message) {
        this.status = status;
        this.listDataGejala = listDataGejala;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Gejala> getListDataGejala() {
        return listDataGejala;
    }
    public void setListDataGejala(List<Gejala> listDataGejala) {
        this.listDataGejala = listDataGejala;
    }

}
