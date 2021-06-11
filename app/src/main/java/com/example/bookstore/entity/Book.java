package com.example.bookstore.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "book",
        foreignKeys = {@ForeignKey(entity = Category.class,
        parentColumns = "id",
        childColumns = "category_id",
        onDelete = ForeignKey.CASCADE)})
public class Book {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "author")
    private String author;
    @ColumnInfo(name = "category_id")
    private int category_id;

    public Book(String name, String description, String author, int category_id) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.category_id = category_id;
    }
}
