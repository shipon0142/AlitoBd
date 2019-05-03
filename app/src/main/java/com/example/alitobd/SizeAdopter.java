package com.example.alitobd;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class SizeAdopter extends RecyclerView.Adapter<SizeAdopter.ViewHolder> {
    Context context;
    ArrayList<String> products;


    public SizeAdopter(Context context, ArrayList<String> products) {
        this.context = context;
        this.products = products;


        // super(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.size, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

viewHolder.sizeTV.setText(products.get(i).toString());
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sizeTV;
        ImageView cartCloseIV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sizeTV = itemView.findViewById(R.id.SizeTV);
        }
    }

}
