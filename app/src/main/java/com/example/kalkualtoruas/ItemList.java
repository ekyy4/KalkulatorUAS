package com.example.kalkualtoruas;

public class ItemList {
    private final String mAngka1;
    private final String mLineOperasi;
    private final String mAngka2;
    private final String mLineHasil;

    public ItemList(String angka1, String lineOperasi, String angka2, String lineHasil) {
        mAngka1 = angka1;
        mLineOperasi = lineOperasi;
        mAngka2 = angka2;
        mLineHasil = lineHasil;
    }

    public String getLine1() {
        return mAngka1;
    }

    public String getLineOperasi() {
        return mLineOperasi;
    }

    public String getLine2() {
        return mAngka2;
    }

    public String getLineHasil() {
        return mLineHasil;
    }
}
