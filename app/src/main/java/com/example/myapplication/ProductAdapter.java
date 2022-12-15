package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Product> productList;
    private ArrayList<Product> dataitem;


    public ProductAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;
    }

    public void setProductList(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.view_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.judul.setText(productList.get(position).getTitle());
        holder.kategori.setText(productList.get(position).getCategory());
        String[] datastring = {productList.get(position).getTitle(),productList.get(position).getCategory(),productList.get(position).getImage(),productList.get(position).getDescription()};
        Glide.with(context)
                .load(this.productList.get(position).getImage())
                .apply(RequestOptions.centerCropTransform())
                .into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProduct.class);
                intent.putExtra("datastring", datastring);
                context.startActivity(intent);
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
                DataProduct dataProduct = new DataProduct();
                dataProduct.title = datastring[0];
                dataProduct.category = datastring[1];
                dataProduct.image = datastring[2];
                dataProduct.description = datastring[3];
                databaseHelper.dataProductDao().insert(dataProduct);
                Toast.makeText(context, "Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            }
        });



    }



    @Override
    public int getItemCount() {
        return this.productList.size();
    }


    public void filterList(List<Product> filteredList){
        productList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul;
        TextView kategori;
        ImageView image;
        Button button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.textView);
            kategori = itemView.findViewById(R.id.textView2);
            image = itemView.findViewById(R.id.imageView);
            button = itemView.findViewById(R.id.download);

        }

    }



}
