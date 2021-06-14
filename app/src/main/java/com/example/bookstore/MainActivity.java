package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.ui.LoginActivity;
import com.example.bookstore.ui.NoInternetActivity;
import com.example.bookstore.util.NetworkUtil;

import cn.leancloud.AVUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(NetworkUtil.isOnline(this)) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                setContentView(R.layout.activity_main);
            } else {
                // 跳到登录页面
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        } else {
            // 跳转到无网络界面
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
//        BookStoreDataBase appDb = BookStoreDataBase.getInstance(this);
//        Category c = new Category(1, "现代文学");
//        Book[] books = {new Book("书名", "描述", "作者", 1)};
//        appDb.categoryDao().insertCategory(c);
//        appDb.bookDao().insertBook(books);
//        appDb.bookDao().getAllBooks();
    }
}