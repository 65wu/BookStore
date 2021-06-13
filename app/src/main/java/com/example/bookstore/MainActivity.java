package com.example.bookstore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.db.BookStoreDataBase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookStoreDataBase appDb = BookStoreDataBase.getInstance(this);
//        Category c = new Category(1, "现代文学");
//        Book[] books = {new Book("书名", "描述", "作者", 1)};
//        appDb.categoryDao().insertCategory(c);
//        appDb.bookDao().insertBook(books);
//        appDb.bookDao().getAllBooks();
    }
}