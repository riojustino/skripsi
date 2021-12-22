package com.justino.sistempakar.riwayat;

import com.google.gson.annotations.SerializedName;
import com.justino.sistempakar.riwayat.Riwayat;

import java.util.List;

public class GetRiwayat {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Riwayat> listDataRiwayat;
    @SerializedName("message")
    String message;

    public GetRiwayat(String status, List<Riwayat> listDataRiwayat, String message) {
        this.status = status;
        this.listDataRiwayat = listDataRiwayat;
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
    public List<Riwayat> getListDataRiwayat() {
        return listDataRiwayat;
    }
    public void setListDataRiwayat(List<Riwayat> listDataRiwayat) {
        this.listDataRiwayat = listDataRiwayat;
    }

}
