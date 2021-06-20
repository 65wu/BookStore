package com.example.bookstore.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.MainActivity;
import com.example.bookstore.R;
import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.util.FileHelper;
import com.example.bookstore.util.include.TopBarCustomer;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class BooksAddActivity extends AppCompatActivity {
    Integer category_id;
    private ImageView image_button;
    private BookStoreDataBase appDb;
    private Bitmap selectedImage = null;
    private final List<Integer> categoriesIdList = new ArrayList<>();
    private final List<String> categoriesNameList = new ArrayList<>();
    private final HashMap<String, Integer> categoriesMap = new HashMap<>();
    private final FileHelper fileHelper = new FileHelper();
    private final TopBarCustomer topBarCustomer = new TopBarCustomer(this, "添加新图书");

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        appDb = BookStoreDataBase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        Intent i = getIntent();
        boolean if_edit = i.getBooleanExtra("if_edit", false);

        topBarCustomer.init(getWindow().getDecorView());

        categoriesParse();
        PowerSpinnerView powerSpinnerView = findViewById(R.id.spinner_book_category);
        powerSpinnerView.setItems(categoriesNameList);
        powerSpinnerView.setOnSpinnerItemSelectedListener((OnSpinnerItemSelectedListener<String>) (oldIndex, oldItem, newIndex, newItem) -> {
            category_id = categoriesMap.get(newItem);
        });

        image_button = findViewById(R.id.add_image_button);
        Button submit_button = findViewById(R.id.add_new_book_button);
        EditText book_name = findViewById(R.id.new_book_name);
        EditText book_author = findViewById(R.id.new_book_author);
        EditText book_description = findViewById(R.id.new_book_description);

        image_button.setOnClickListener(v -> {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto , 1);
        });

        // 如果是修改图书，装入之前的数据
        if(if_edit) {
            image_button.setImageBitmap(fileHelper.loadImageBitmap(this, "book", i.getStringExtra("book_id")));
            TextView image_text = findViewById(R.id.add_image_text);
            image_text.setText("修改图书图片");
            submit_button.setText("修改");
            book_name.setText(i.getStringExtra("book_name"));
            book_author.setText(i.getStringExtra("book_author"));
            book_description.setText(i.getStringExtra("book_description"));
            // 设置下拉列表默认位置
            powerSpinnerView.selectItemByIndex(
                    categoriesIdList.indexOf(
                            Integer.parseInt(
                                    i.getStringExtra("book_category_id"))));
        }

        submit_button.setOnClickListener(v -> {
            try {
                String message, book_id;
                Book book = new Book(
                        book_name.getText().toString(),
                        book_description.getText().toString(),
                        book_author.getText().toString(),
                        category_id
                );
                if(if_edit) {
                    message = "修改新图书成功。";
                    book_id = i.getStringExtra("book_id");
                    book.setId(Integer.parseInt(book_id));
                    appDb.bookDao().updateBook(book);
                } else {
                    message = "添加新图书成功。";
                    book_id = appDb.bookDao().insertBook(book).get(0) + "";
                }
                fileHelper.saveImage(BooksAddActivity.this, selectedImage, "book", book_id);
                Toasty.success(getApplicationContext(),
                        message,
                        Toast.LENGTH_SHORT,
                        true).show();
                Intent intent = new Intent(BooksAddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    // 图书种类id与图书种类名双向绑定
    private void categoriesParse() {
        List<Category> categoryList = appDb.categoryDao().getAllCategories();
        for(Category c: categoryList) {
            categoriesIdList.add(c.getId());
            categoriesNameList.add(c.getName());
            categoriesMap.put(c.getName(), c.getId());
        }
    }

    // 读取相册图片
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            final Uri imageUri = data.getData();
            final InputStream imageStream;
            try {
                imageStream = getContentResolver().openInputStream(imageUri);
                selectedImage = BitmapFactory.decodeStream(imageStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    // 在相册界面切回app时更新图片
    @Override
    public void onResume() {
        super.onResume();
        if (selectedImage != null) {
            image_button.setImageBitmap(selectedImage);
            image_button.setPadding(0, 0, 0, 0);
        }
    }
}
