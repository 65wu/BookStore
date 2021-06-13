package com.example.bookstore.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.R;

import org.jetbrains.annotations.NotNull;

import cn.leancloud.AVUser;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        AVUser user = new AVUser();
        user.setUsername("test_user");
        user.setEmail("test@qq.com");
        user.setPassword("123456");

        user.signUpInBackground().subscribe(new Observer<AVUser>() {
            public void onSubscribe(@NotNull Disposable disposable) {
            }

            public void onNext(@NotNull AVUser user) {
                // 注册成功
                System.out.println("注册成功。objectId：" + user.getObjectId());
            }

            public void onError(@NotNull Throwable throwable) {
                System.out.println(throwable);
            }

            public void onComplete() {
            }
        });
    }
}
