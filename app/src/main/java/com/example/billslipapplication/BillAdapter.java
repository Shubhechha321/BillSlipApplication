package com.example.billslipapplication;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private List<Bitmap> myBitmaps;
    private List<Double> myPrice;

    public BillAdapter(List<Bitmap> myBitmaps, List<Double> myPrice) {
        this.myBitmaps = myBitmaps;
        this.myPrice = myPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_item, null);
        ViewHolder viewHolder = new ViewHolder(listLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.price.setText(Double.toString(myPrice.get(position)));
        holder.img.setImageBitmap((Bitmap) myBitmaps.get(position));
        holder.serialNo.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return myBitmaps.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView serialNo;
        TextView price;
        ImageView img;
        public ViewHolder(View itemView) {

            super(itemView);
            serialNo = (TextView)itemView.findViewById(R.id.serialNo);
            price = (TextView)itemView.findViewById(R.id.price);
            img = (ImageView)itemView.findViewById(R.id.img);
        }
    }
}