package com.example.billslipapplication;

import android.graphics.Bitmap;

import java.util.List;

public class BillData {

    private static BillData instance;

    public static BillData getInstance() {
        if (instance == null)
            instance = new BillData();
        return instance;
    }

    private List<Bitmap> bitmaps;
    private List<Double> doubles;

    private BillData() { }

    public void setBitmaps(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    public List<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public List<Double> getDoubles() {
        return doubles;
    }

    public void setDoubles(List<Double> doubles) {
        this.doubles = doubles;
    }
}
