package com.example.bookstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bookstore.db.BookStoreDataBase;

import org.jetbrains.annotations.NotNull;

import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookStoreDataBase appDb = BookStoreDataBase.getInstance(this);
//        appDb.clearAllTables();
//        Category c = new Category(1, "现代文学");
//        Book[] books = {new Book("书名", "描述", "作者", 1)};
//        appDb.categoryDao().insertCategory(c);
//        appDb.bookDao().insertBook(books);
//        appDb.bookDao().getAllBooks();


        AVUser user = new AVUser();
        user.setUsername("test_user");
        user.setEmail("test@qq.com");
        user.setPassword("123456");

        user.signUpInBackground().subscribe(new Observer<AVUser>() {
            public void onSubscribe(@NotNull Disposable disposable) {}
            public void onNext(@NotNull AVUser user) {
                // 注册成功
                System.out.println("注册成功。objectId：" + user.getObjectId());
            }
            public void onError(@NotNull Throwable throwable) {
                System.out.println(throwable);
            }
            public void onComplete() {}
        });
    }
}