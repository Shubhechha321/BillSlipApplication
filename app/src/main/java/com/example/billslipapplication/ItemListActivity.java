package com.example.billslipapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {
    private List<Bitmap> myBitmaps;
    private List<Double> myPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        myBitmaps = BillData.getInstance().getBitmaps();
        myPrice = BillData.getInstance().getDoubles();
        BillAdapter adapter = new BillAdapter(myBitmaps, myPrice);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setAutoMeasureEnabled(false);  // This is the key
        recyclerView.setLayoutManager(llm);
    }
}