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

import com.example.bookstore.db.BookStoreDataBase;
import com.example.bookstore.ui.BookCategoriesAddActivity;
import com.example.bookstore.ui.BooksAddActivity;
import com.example.bookstore.ui.LoginActivity;
import com.example.bookstore.ui.NoInternetActivity;
import com.example.bookstore.util.include.SearchButtonClick;
import com.example.bookstore.util.include.BottomNavigationBehavior;
import com.example.bookstore.util.NetworkUtil;
import com.example.bookstore.util.adapter.RecyclerviewLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

import cn.jzvd.Jzvd;
import cn.leancloud.AVUser;

public class MainActivity extends AppCompatActivity {
    private AVUser currentUser;
    private Toolbar toolbar;
    private BookStoreDataBase appDb;
    private final RecyclerviewLoader recyclerviewLoader = new RecyclerviewLoader(this);
    private final SearchButtonClick searchButtonClick = new SearchButtonClick(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appDb = BookStoreDataBase.getInstance(this);

//        Comment[] c = new Comment[5];
//        for(int i = 0; i < 5; i++) {
//            c[i] = new Comment(
//                    2,
//                    "测试用户" + i,
//                    i + "一长串评论测试，这本书感觉还不错，纸张印刷很好，推荐大家购买，是本好书",
//                    new Date()
//            );
//        }
//        appDb.commentDao().insertComment(c);

        super.onCreate(savedInstanceState);
        if(NetworkUtil.isOnline(this)) {
            currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                setContentView(R.layout.activity_main);
                toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                initRecyclerView();
                initParts();
                searchButtonClick.authorSearch(getWindow().getDecorView());
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

    private void initRecyclerView() {
        recyclerviewLoader.loadBooksRecycleView(
                findViewById(R.id.books_list),
                appDb.bookDao().getAllBooks());
        recyclerviewLoader.loadCategoriesRecycleView(
                findViewById(R.id.categories_list),
                appDb.categoryDao().getAllCategories()
        );
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

        // 设置视频
        cn.jzvd.JzvdStd jzvdStd = findViewById(R.id.jz_video);
        jzvdStd.setUp("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4", "书城介绍视频");
    }

    // 用户登出
    public void logout(MenuItem menuItem) {
        AVUser.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void addNewBooks(MenuItem menuItem) {
        Intent intent = new Intent(this, BooksAddActivity.class);
        startActivity(intent);
    }

    public void addNewBookCategory(MenuItem menuItem) {
        Intent intent = new Intent(this, BookCategoriesAddActivity.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }
}