package com.example.ayamku;

public class Egg {
    private String idTelur;
    private String grade;
    private String beratTelur;

    public Egg(String idTelur, String grade, String beratTelur) {
        this.idTelur = idTelur;
        this.grade = grade;
        this.beratTelur = beratTelur;
    }

    public String getIdTelur() {
        return idTelur;
    }

    public String getGrade() {
        return grade;
    }

    public String getBeratTelur() {
        return beratTelur;
    }
}
