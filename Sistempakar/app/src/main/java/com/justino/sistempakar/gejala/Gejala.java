package com.justino.sistempakar.gejala;

import com.google.gson.annotations.SerializedName;

public class Gejala{
    @SerializedName("kode_gejala")
    private String kodeGejala;
    @SerializedName("nama_gejala")
    private String namaGejala;

    public Gejala(String kodeGejala, String namaGejala) {
        this.kodeGejala = kodeGejala;
        this.namaGejala = namaGejala;
    }

    public String getKodeGejala() {
        return kodeGejala;
    }

    public void setKodeGejala(String kodeGejala) {
        this.kodeGejala = kodeGejala;
    }

    public String getNamaGejala() {
        return namaGejala;
    }

    public void setNamaGejala(String namaGejala) {
        this.namaGejala = namaGejala;
    }



}