package com.example.ayamku;

public class Kebutuhan {
    private String idKebutuhan;
    private String namaKebutuhan;
    private String jumlahKebutuhan;

    public Kebutuhan(String idKebutuhan, String namaKebutuhan, String jumlahKebutuhan) {
        this.idKebutuhan = idKebutuhan;
        this.namaKebutuhan = namaKebutuhan;
        this.jumlahKebutuhan = jumlahKebutuhan;
    }

    public String getIdKebutuhan() {
        return idKebutuhan;
    }

    public String getNamaKebutuhan() {
        return namaKebutuhan;
    }

    public String getJumlahKebutuhan() {
        return jumlahKebutuhan;
    }
}
