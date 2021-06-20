package com.example.bookstore.util.include;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookstore.R;
import com.example.bookstore.ui.SearchActivity;

import es.dmoral.toasty.Toasty;

public class SearchButtonClick {
    private final Context context;
    public SearchButtonClick(Context context) {
        this.context = context;
    }
    public void authorSearch(View v) {
        Button search_button = v.findViewById(R.id.search_button);
        EditText search_input = v.findViewById(R.id.search_input);
        search_button.setOnClickListener(view -> {
            // 跳转到搜索结果界面
            Intent intent = new Intent(context, SearchActivity.class);
            String input = search_input.getText().toString();
            intent.putExtra("type", "author");
            intent.putExtra("value", input);
            if(!input.isEmpty()) {
                context.startActivity(intent);
            } else {
                Toasty.error(
                        context,
                        "输入不能为空",
                        Toast.LENGTH_SHORT,
                        true).show();
            }
        });
    }
}
