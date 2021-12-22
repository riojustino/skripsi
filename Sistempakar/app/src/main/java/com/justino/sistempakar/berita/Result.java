package com.justino.sistempakar.berita;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("id")
    private String id;
    @SerializedName("namapenyakit")
    private String namapenyakit;
    @SerializedName("hasilpenyakit")
    private String hasilpenyakit;

    public Result(String id, String namapenyakit, String hasilpenyakit) {
        this.id = id;
        this.namapenyakit = namapenyakit;
        this.hasilpenyakit = hasilpenyakit;
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
}
