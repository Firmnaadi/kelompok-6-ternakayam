package com.example.ayamku;

public class JamModel {
    private String jam;
    private String tanggal;

    public JamModel(String jam, String tanggal) {
        this.jam = jam;
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public String getTanggal() {
        return tanggal;
    }
}
