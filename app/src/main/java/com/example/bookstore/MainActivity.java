package com.example.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;
import com.example.bookstore.ui.LoginActivity;
import com.example.bookstore.ui.NoInternetActivity;
import com.example.bookstore.util.BooksAdapter;
import com.example.bookstore.util.BottomNavigationBehavior;
import com.example.bookstore.util.NetworkUtil;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import cn.leancloud.AVUser;

public class MainActivity extends AppCompatActivity {
    private BookStoreDataBase appDb;
    private AVUser currentUser;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appDb = BookStoreDataBase.getInstance(this);
//        Category c = new Category(1, "现代文学");
//        Book[] books = new Book[10];
//        appDb.categoryDao().insertCategory(c);
//        for(int i = 0; i < 10; i++) {
//            books[i] = new Book("书名" + i, "描述" + i, "作者" + i, 1);
//        }
//        appDb.bookDao().insertBook(books);

        super.onCreate(savedInstanceState);
        if(NetworkUtil.isOnline(this)) {
            currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                setContentView(R.layout.activity_main);
                toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                loadRecycleView();
                initParts();
            } else {
                // 跳到登录页面
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            }
        } else {
            // 跳转到无网络界面
            Intent intent = new Intent(this, NoInternetActivity.class);
            startActivity(intent);
        }
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            if (item.getItemId() == R.id.navigationMenu) {
                    DrawerLayout drawer = findViewById(R.id.drawer_layout);
                    drawer.openDrawer(GravityCompat.START);
                    return true;
                }
            return false;
        }
    };

    private final NavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
            if (item.getItemId() == R.id.navigationMenu) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
                return true;
            }
            return false;
        }
    };

    private void loadRecycleView() {
        // 装载RecycleView
        RecyclerView recyclerView = findViewById(R.id.books_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BooksAdapter adapter = new BooksAdapter(appDb.bookDao().getAllBooks(), this);
        recyclerView.setAdapter(adapter);
    }

    private void initParts() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);

        // 载入当前用户的用户名与邮箱
        View appBarMainBinding = navigationView.getHeaderView(0);
        TextView nav_username = appBarMainBinding.findViewById(R.id.nav_username);
        TextView nav_email = appBarMainBinding.findViewById(R.id.nav_email);
        nav_username.setText(currentUser.getUsername());
        nav_email.setText(currentUser.getEmail());

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationItemSelectedListener);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        bottomNavigationView.setSelectedItemId(R.id.navigationHome);
    }
}