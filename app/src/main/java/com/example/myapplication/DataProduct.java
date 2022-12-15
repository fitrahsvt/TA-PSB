package com.example.myapplication;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DataProduct {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "category")
    public String category;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo (name = "description")
    public String description;



}
