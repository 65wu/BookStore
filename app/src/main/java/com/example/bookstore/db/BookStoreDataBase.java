package com.example.bookstore.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bookstore.dao.BookDao;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Category;


@Database(entities = {Book.class, Category.class}, version = 1)
public abstract class BookStoreDataBase extends RoomDatabase {
    private static final String db_name = "book_store";
    private static BookStoreDataBase instance;

    public static synchronized BookStoreDataBase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    BookStoreDataBase.class,
                    db_name
            ).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract BookDao bookDao();
}
