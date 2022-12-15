package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WishlistActivity extends AppCompatActivity {
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        recyclerView = findViewById(R.id.recycle2);
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        AdapterWishlist adapterWishlist = new AdapterWishlist(WishlistActivity.this, databaseHelper.dataProductDao().getAll());
        recyclerView.setAdapter(adapterWishlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(WishlistActivity.this));
    }
}




