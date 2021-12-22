package com.justino.sistempakar.berita;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetBerita {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Berita> listDataBerita;
    @SerializedName("message")
    String message;

    public GetBerita(String status, List<Berita> listDataBerita, String message) {
        this.status = status;
        this.listDataBerita = listDataBerita;
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
    public List<Berita> getListDataBerita() {
        return listDataBerita;
    }
    public void setListDataBerita(List<Berita> listDataBerita) {
        this.listDataBerita = listDataBerita;
    }

}
