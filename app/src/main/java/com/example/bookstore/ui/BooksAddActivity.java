package com.example.bookstore.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.R;
import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.entity.Category;
import com.skydoves.powerspinner.PowerSpinnerView;

public class BooksAddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        BookStoreDataBase appDb = BookStoreDataBase.getInstance(this);
        PowerSpinnerView powerSpinnerView = findViewById(R.id.spinner_book_category);
        powerSpinnerView.setItems(appDb.categoryDao().getAllCategoriesName());
    }
}
