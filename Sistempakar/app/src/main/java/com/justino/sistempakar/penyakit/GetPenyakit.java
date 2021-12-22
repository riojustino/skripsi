package com.justino.sistempakar.penyakit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPenyakit {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Penyakit> listDataPenyakit;
    @SerializedName("message")
    String message;

    public GetPenyakit(String status, List<Penyakit> listDataPenyakit, String message) {
        this.status = status;
        this.listDataPenyakit = listDataPenyakit;
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
    public List<Penyakit> getListDataPenyakit() {
        return listDataPenyakit;
    }
    public void setListDataPenyakit(List<Penyakit> listDataPenyakit) {
        this.listDataPenyakit = listDataPenyakit;
    }

}
