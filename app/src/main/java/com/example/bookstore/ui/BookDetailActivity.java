package com.example.bookstore.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.MainActivity;
import com.example.bookstore.R;
import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Comment;
import com.example.bookstore.util.FileHelper;
import com.example.bookstore.util.adapter.RecyclerviewLoader;

import java.util.Date;
import java.util.List;

import cn.leancloud.AVUser;
import es.dmoral.toasty.Toasty;

public class BookDetailActivity extends AppCompatActivity {
    String book_id;
    String book_name;
    String book_author;
    String book_description;
    Integer book_category_id;
    Intent intent;
    AlertDialog.Builder builder;
    BookStoreDataBase appDb;
    private final RecyclerviewLoader recyclerviewLoader = new RecyclerviewLoader(this);
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        appDb = BookStoreDataBase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        builder = new AlertDialog.Builder(this);

        intent = getIntent();
        book_id = intent.getStringExtra("book_id");
        book_name = intent.getStringExtra("book_name");
        book_author = intent.getStringExtra("book_author");
        book_description = intent.getStringExtra("book_description");
        book_category_id = Integer.parseInt(intent.getStringExtra("book_category_id"));
        Bitmap book_image = new FileHelper().loadImageBitmap(this, "book", book_id);

        ImageView detail_book_image = findViewById(R.id.detail_book_image);
        TextView detail_book_name = findViewById(R.id.detail_book_name);
        TextView detail_book_author = findViewById(R.id.detail_book_author);
        TextView detail_book_description = findViewById(R.id.detail_book_description);

        // 初始化图书详情
        detail_book_image.setImageBitmap(book_image);
        detail_book_name.setText(book_name);
        detail_book_author.setText(book_author);
        detail_book_description.setText(book_description);

        // 绑定修改图书按钮
        ImageView edit_book = findViewById(R.id.edit_book);
        edit_book.setOnClickListener(view -> {
            Intent i = new Intent(this, BooksAddActivity.class);
            i.putExtras(intent.getExtras());
            i.putExtra("if_edit", true);
            startActivity(i);
        });

        // 装载评论
        List<Comment> categoryList = appDb.commentDao().getCommentsFromBook(Integer.parseInt(book_id));
        TextView comment_title = findViewById(R.id.comment_title);
        String comment_title_detail = "评论(" + categoryList.size() + ")";
        comment_title.setText(comment_title_detail);
        recyclerviewLoader.loadCommentsRecycleView(findViewById(R.id.comments_list), categoryList);
    }
    // 返回首页按钮
    public void back_home(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    // 删除图书按钮
    public void delete_book(View view) {
        builder.setMessage("是否删除该图书？")
                .setCancelable(false)
                .setPositiveButton("确认", (dialog, id) -> {
                    finish();
                    appDb.bookDao().deleteBook(new Book(
                            Integer.parseInt(book_id),
                            book_name,
                            book_author,
                            book_description,
                            book_category_id
                    ));
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton("取消", (dialog, id) -> {
                    //  Action for 'NO' Button
                    dialog.cancel();
                });
        AlertDialog alert = builder.create();
        alert.setTitle("注意");
        alert.show();
        alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#03A9F4"));
        alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#03A9F4"));
    }
    // 发表评论
    public void send_comment(View view) {
        String username = AVUser.getCurrentUser().getUsername();
        EditText send_comment_content = findViewById(R.id.send_comment_content);
        String comment_content = send_comment_content.getText().toString();
        Date date = new Date();

        appDb.commentDao().insertComment(new Comment(
                Integer.parseInt(book_id),
                username,
                comment_content,
                date
        ));

        Toasty.success(this, "发表评论成功。", Toasty.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtras(this.intent.getExtras());
        startActivity(intent);
    }
}