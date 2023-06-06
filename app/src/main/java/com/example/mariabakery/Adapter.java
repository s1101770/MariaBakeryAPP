package com.example.mariabakery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    Context context;
    ArrayList<ShopViewModel> viewModels;
    RecyclerViewInterface recyclerViewInterface;
    public Adapter(Context context,ArrayList<ShopViewModel> viewModels,RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.viewModels = viewModels;
        this.recyclerViewInterface = recyclerViewInterface;

    }
    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shopitem, parent, false);

        return new Adapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(viewModels.get(position).getName());
        holder.tvDetail.setText(viewModels.get(position).getDetail() + "元");
        holder.tvImage.setImageResource(viewModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return viewModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle,tvDetail;
        ImageView tvImage;

        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.itemNameTXV);
            tvDetail = itemView.findViewById(R.id.itemDetailTXV);
            tvImage = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
