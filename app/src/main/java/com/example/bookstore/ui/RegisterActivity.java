package com.example.bookstore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.R;
import com.example.bookstore.databinding.ActivityRegisterBinding;

import org.jetbrains.annotations.NotNull;

import cn.leancloud.AVUser;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        com.example.bookstore.databinding.ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        com.example.bookstore.databinding.LayoutRegisterBinding layoutRegister = binding.layoutRegister;
        setContentView(binding.getRoot());

        layoutRegister.registerButton.setOnClickListener(view -> {
            String username = layoutRegister.username.getText().toString();
            String mobile_number = layoutRegister.mobileNumber.getText().toString();
            String email = layoutRegister.email.getText().toString();
            String password = layoutRegister.password.getText().toString();

            AVUser user = new AVUser();
            user.setUsername(username);
            user.setMobilePhoneNumber(mobile_number);
            user.setEmail(email);
            user.setPassword(password);

            user.signUpInBackground().subscribe(new Observer<AVUser>() {
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                public void onNext(@NotNull AVUser user) {
                    // 注册成功
                    Toasty.success(getApplicationContext(),
                            "注册成功，请验证邮箱。",
                            Toast.LENGTH_SHORT,
                            true).show();
                }

                public void onError(@NotNull Throwable throwable) {
                    String message = throwable.getMessage();
                    assert message != null;
                    if (message.equals("Username has already been taken.")) {
                        message = "此用户名已被占用。";
                    }
                    Toasty.error(
                            getApplicationContext(),
                            message,
                            Toast.LENGTH_SHORT,
                            true).show();
                }

                public void onComplete() {
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });
        });
    }

    public void viewLoginClicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
