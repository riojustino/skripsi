package com.justino.sistempakar.riwayat;

import com.google.gson.annotations.SerializedName;

public class Riwayat {
    @SerializedName("id")
    private String id;
    @SerializedName("namapenyakit")
    private String namapenyakit;
    @SerializedName("hasilpenyakit")
    private String hasilpenyakit;
    @SerializedName("date")
    private String date;


    public Riwayat(String id, String namapenyakit, String hasilpenyakit, String date){
        this.id = id;
        this.namapenyakit = namapenyakit;
        this.hasilpenyakit = hasilpenyakit;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamapenyakit() {
        return namapenyakit;
    }

    public void setNamapenyakit(String namapenyakit) {
        this.namapenyakit = namapenyakit;
    }

    public String getHasilpenyakit() {
        return hasilpenyakit;
    }

    public void setHasilpenyakit(String hasilpenyakit) {
        this.hasilpenyakit = hasilpenyakit;
    }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }
}