package com.example.kalkualtoruas;

public class ItemList {
    private final String Angka1;
    private final String LineOperasi;
    private final String Angka2;
    private final String LineHasil;

    public ItemList(String angka1, String lineOperasi, String angka2, String lineHasil) {
        Angka1 = angka1;
        LineOperasi = lineOperasi;
        Angka2 = angka2;
        LineHasil = lineHasil;
    }

    public String getLine1() {
        return Angka1;
    }

    public String getLineOperasi() {
        return LineOperasi;
    }

    public String getLine2() {
        return Angka2;
    }

    public String getLineHasil() {
        return LineHasil;
    }
}
