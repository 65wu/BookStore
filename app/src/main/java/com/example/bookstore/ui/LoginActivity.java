package com.example.bookstore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.MainActivity;
import com.example.bookstore.R;
import com.example.bookstore.databinding.LayoutLoginBinding;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.leancloud.AVUser;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        com.example.bookstore.databinding.ActivityLoginBinding binding = com.example.bookstore.databinding.ActivityLoginBinding.inflate(getLayoutInflater());
        com.example.bookstore.databinding.LayoutLoginBinding layoutLogin = binding.layoutLogin;
        setContentView(binding.getRoot());
        
        // 观察者
        Observer<AVUser> o = new Observer<AVUser>() {
            public void onSubscribe(@NotNull Disposable disposable) {}
            public void onNext(@NotNull AVUser user) {
                // 登录成功
                Toasty.success(getApplicationContext(),
                        "登录成功。",
                        Toast.LENGTH_SHORT,
                        true).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
            public void onError(@NotNull Throwable throwable) {
                // 登录失败（可能是密码错误）
                String message = throwable.getMessage();
                assert message != null;
                if(message.equals("登录失败次数超过限制，请稍候再试，或者通过忘记密码重设密码。")) {
                    message = "错误次数过多，请稍候再试。";
                } else {
                    message = "用户名或密码错误";
                }
                Toasty.error(
                        getApplicationContext(),
                        message,
                        Toast.LENGTH_SHORT,
                        true).show();
            }
            public void onComplete() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
        
        layoutLogin.cirLoginButton.setOnClickListener(view -> {
            String email = layoutLogin.editTextEmail.getText().toString();
            String password = layoutLogin.editTextPassword.getText().toString();
            if(isEmail(email)) {
                AVUser.loginByEmail(email, password).subscribe(o);
            } else {
                AVUser.logIn(email, password).subscribe(o);
            }
        });
    }

    public void viewRegisterClicked(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    // 验证是否是邮箱登录
    public static boolean isEmail(String strEmail) {
        String strPattern = "^\\s*\\w+(?:\\.?[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
}
