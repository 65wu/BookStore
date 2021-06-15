package com.example.bookstore.util;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.entity.Book;

import java.util.List;

public class RecyclerviewLoader {
    private final Context context;
    public RecyclerviewLoader(Context context) {
        this.context = context;
    }
    public void loadBooksRecycleView(RecyclerView recyclerView, List<Book> bookList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        BooksAdapter adapter = new BooksAdapter(bookList, context);
        recyclerView.setAdapter(adapter);
    }
}
