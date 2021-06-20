package com.example.bookstore.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.MainActivity;
import com.example.bookstore.R;

public class NoInternetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
    }

    public void backMainClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
