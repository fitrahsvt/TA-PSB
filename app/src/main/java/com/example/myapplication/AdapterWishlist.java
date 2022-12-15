package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdapterWishlist extends RecyclerView.Adapter<AdapterWishlist.ViewHolderWishlist> {
    private Context context;
    private List<DataProduct> list;

    public AdapterWishlist(Context context, List<DataProduct> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterWishlist.ViewHolderWishlist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_wishlist, parent, false);
        return new ViewHolderWishlist(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWishlist.ViewHolderWishlist holder, int position) {
        holder.title.setText(list.get(position).title);
        holder.category.setText(list.get(position).category);


        DataProduct datadel = list.get(position);
        String[] datastring = {list.get(position).title, list.get(position).category, list.get(position).image, list.get(position).description};
        Glide.with(context)
                .load(this.list.get(position).image)
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
                databaseHelper.dataProductDao().delete(datadel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return  list.size();
    }

    public class ViewHolderWishlist extends RecyclerView.ViewHolder {
        TextView title, category;
        ImageView image;
        Button button;

        public ViewHolderWishlist(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView6);
            category =itemView.findViewById(R.id.textView7);
            image = itemView.findViewById(R.id.imageView3);
            button = itemView.findViewById(R.id.delete);
        }
    }
}
