package com.example.bookstore.util.include;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bookstore.MainActivity;
import com.example.bookstore.R;


public class TopBarCustomer {
    private final String top_bar_name;
    private final Context context;

    public TopBarCustomer(Context context, String top_bar_name) {
        this.context = context;
        this.top_bar_name = top_bar_name;
    }

    public void init(View v) {
        ImageView back_home_image = v.findViewById(R.id.back_home_image);
        TextView top_bar_text = v.findViewById(R.id.top_bar_text);
        top_bar_text.setText(top_bar_name);
        back_home_image.setOnClickListener(view -> {
            // 跳转到搜索结果界面
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        });
    }
}
