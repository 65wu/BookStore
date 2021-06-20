package com.example.bookstore.util.adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.Comment;

import java.util.List;

public class RecyclerviewLoader {
    private final Context context;

    public RecyclerviewLoader(Context context) {
        this.context = context;
    }

    // 装载RecycleView
    public void loadBooksRecycleView(RecyclerView recyclerView, List<Book> bookList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        BooksAdapter adapter = new BooksAdapter(bookList, context);
        recyclerView.setAdapter(adapter);
    }

    public void loadCategoriesRecycleView(RecyclerView recyclerView, List<Category> categoryList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        CategoriesAdapter adapter = new CategoriesAdapter(categoryList, context);
        recyclerView.setAdapter(adapter);
    }

    public void loadCommentsRecycleView(RecyclerView recyclerView, List<Comment> commentList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        CommentsAdapter adapter = new CommentsAdapter(commentList, context);
        recyclerView.setAdapter(adapter);
    }
}
