package com.example.bookstore.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.R;
import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.util.RecyclerviewLoader;

public class SearchActivity extends AppCompatActivity {
    private final RecyclerviewLoader recyclerviewLoader = new RecyclerviewLoader(this);
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        BookStoreDataBase appDb = BookStoreDataBase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        String input = intent.getStringExtra("input");
        recyclerviewLoader.loadBooksRecycleView(
                findViewById(R.id.books_list),
                appDb.bookDao().getBooksByAuthor(input));
    }
}
