package com.example.bookstore.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.R;
import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.entity.Category;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class BooksAddActivity extends AppCompatActivity {
    private final List<String> categoriesNameList = new ArrayList<>();
    private final HashMap<String, Integer> categoriesMap = new HashMap<>();

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        categoriesParse();
        PowerSpinnerView powerSpinnerView = findViewById(R.id.spinner_book_category);
        powerSpinnerView.setItems(categoriesNameList);
        powerSpinnerView.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
            Integer category_id = categoriesMap.get(newItem);
            assert category_id != null;
            Toasty.success(
                    getApplicationContext(),
                    category_id + "",
                    Toast.LENGTH_SHORT,
                    true).show();
        });
    }
    private void categoriesParse() {
        BookStoreDataBase appDb = BookStoreDataBase.getInstance(this);
        List<Category> categoryList = appDb.categoryDao().getAllCategories();
        for(Category c: categoryList) {
            categoriesNameList.add(c.getName());
            categoriesMap.put(c.getName(), c.getId());
        }
    }
}
