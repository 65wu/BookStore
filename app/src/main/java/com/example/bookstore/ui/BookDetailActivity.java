package com.example.bookstore.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.R;
import com.example.bookstore.util.FileHelper;

public class BookDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        String book_id = intent.getStringExtra("book_id");
        String book_name = intent.getStringExtra("book_name");
        String book_author = intent.getStringExtra("book_author");
        String book_description = intent.getStringExtra("book_description");
        Bitmap book_image = new FileHelper().loadImageBitmap(this, "book", book_id);

        ImageView detail_book_image = findViewById(R.id.detail_book_image);
        TextView detail_book_name = findViewById(R.id.detail_book_name);
        TextView detail_book_author = findViewById(R.id.detail_book_author);
        TextView detail_book_description = findViewById(R.id.detail_book_description);

        detail_book_image.setImageBitmap(book_image);
        detail_book_name.setText(book_name);
        detail_book_author.setText(book_author);
        detail_book_description.setText(book_description);
    }
}