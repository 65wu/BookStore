package com.example.bookstore.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookstore.entity.Book;

import java.util.List;

@Dao
public interface BookDao {
    @Query("Select * from book")
    List<Book> getAllBooks();

    @Query("Select * from book where category_id = :category_id")
    List<Book> getBooksByCategory(int category_id);

    @Query("Select * from book where author = :author")
    List<Book> getBooksByAuthor(String author);

    @Insert
    List<Long> insertBook(Book... book);

    @Update
    void updateBook(Book... book);

    @Delete
    void deleteBook(Book... book);
}
