package com.example.bookstore.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookstore.MainActivity;
import com.example.bookstore.R;
import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.entity.Category;
import com.example.bookstore.util.FileHelper;
import com.example.bookstore.util.include.TopBarCustomer;

import java.io.FileNotFoundException;
import java.io.InputStream;

import es.dmoral.toasty.Toasty;

public class BookCategoriesAddActivity extends AppCompatActivity {
    private ImageView image_button;
    private Bitmap selectedImage = null;
    private final FileHelper fileHelper = new FileHelper();
    private final TopBarCustomer topBarCustomer = new TopBarCustomer(this, "添加新图书类别");
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        BookStoreDataBase appDb = BookStoreDataBase.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_category);

        topBarCustomer.init(getWindow().getDecorView());
        image_button = findViewById(R.id.add_image_category_button);
        EditText category_name = findViewById(R.id.new_book_category_name);
        Button submit_button = findViewById(R.id.add_new_book_category_button);

        image_button.setOnClickListener(v -> {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto , 1);
        });

        submit_button.setOnClickListener(v -> {
            try {
                Long category_id = appDb.categoryDao().insertCategory(new Category(
                        category_name.getText().toString()
                )).get(0);
                fileHelper.saveImage(BookCategoriesAddActivity.this, selectedImage, "category", category_id + "");
                Toasty.success(getApplicationContext(),
                        "添加新图书类别成功。",
                        Toast.LENGTH_SHORT,
                        true).show();
                Intent intent = new Intent(BookCategoriesAddActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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
