package com.justino.sistempakar.berita;

import com.google.gson.annotations.SerializedName;

public class Berita {
    @SerializedName("kode_berita")
    private String kode_berita;
    @SerializedName("nama_berita")
    private String nama_berita;
    @SerializedName("det_berita")
    private String det_berita;
    @SerializedName("penerbit")
    private String penerbit;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("gambar_berita")
    private String gambar_berita;
    private boolean expandableLayout;

    public Berita(String kode_berita, String nama_berita, String det_berita, String gambar_berita,String penerbit, String tanggal, boolean expandableLayout) {
        this.kode_berita = kode_berita;
        this.nama_berita = nama_berita;
        this.det_berita = det_berita;
        this.gambar_berita = gambar_berita;
        this.penerbit = penerbit;
        this.tanggal = tanggal;
        this.expandableLayout = false;
    }

    public String getKode_berita() { return kode_berita; }

    public void setKode_berita(String kode_berita) { this.kode_berita = kode_berita; }

    public String getNama_berita() { return nama_berita; }

    public void setNama_berita(String nama_berita) { this.nama_berita = nama_berita; }

    public String getDet_berita() { return det_berita; }

    public void setDet_berita(String det_berita) { this.det_berita = det_berita; }

    public String getPenerbit() { return penerbit; }

    public void setPenerbit(String penerbit) { this.penerbit = penerbit; }

    public String getTanggal() { return tanggal; }

    public void setTanggal(String tanggal) { this.tanggal = tanggal; }

    public String getGambar() { return gambar_berita; }

    public void setGambar(String gambar_berita) { this.gambar_berita = gambar_berita; }

    public boolean isExpandableLayout() { return expandableLayout; }

    public void setExpandableLayout(boolean expandableLayout) { this.expandableLayout = expandableLayout; }


}
