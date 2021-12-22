package com.justino.sistempakar.penyakit;

import com.google.gson.annotations.SerializedName;

public class Penyakit {
    @SerializedName("kode_penyakit")
    private String kode_penyakit;
    @SerializedName("nama_penyakit")
    private String nama_penyakit;
    @SerializedName("det_penyakit")
    private String det_penyakit;
    @SerializedName("srn_penyakit")
    private String srn_penyakit;
    @SerializedName("gambar")
    private String gambar;
    private boolean expandableLayout;

    public Penyakit(String kode_penyakit, String nama_penyakit, String det_penyakit, String srn_penyakit, String gambar,boolean expandableLayout) {
        this.kode_penyakit = kode_penyakit;
        this.nama_penyakit = nama_penyakit;
        this.det_penyakit = det_penyakit;
        this.srn_penyakit = srn_penyakit;
        this.gambar = gambar;
        this.expandableLayout = false;
    }

    public String getKode_penyakit() { return kode_penyakit; }

    public void setKode_penyakit(String kode_penyakit) { this.kode_penyakit = kode_penyakit; }

    public String getNama_penyakit() { return nama_penyakit; }

    public void setNama_penyakit(String nama_penyakit) { this.nama_penyakit = nama_penyakit; }

    public String getDet_penyakit() { return det_penyakit; }

    public void setDet_penyakit(String det_penyakit) { this.det_penyakit = det_penyakit; }

    public String getSrn_penyakit() { return srn_penyakit; }

    public void setSrn_penyakit(String srn_penyakit) { this.srn_penyakit = srn_penyakit; }

    public String getGambar() { return gambar; }

    public void setGambar(String gambar) { this.gambar = gambar; }

    public boolean isExpandableLayout() { return expandableLayout; }

    public void setExpandableLayout(boolean expandableLayout) { this.expandableLayout = expandableLayout; }


}
