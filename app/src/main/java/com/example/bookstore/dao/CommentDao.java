package com.example.bookstore.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookstore.entity.Comment;

import java.util.List;

@Dao
public interface CommentDao {
    @Query("Select * from comment where book_id = :book_id")
    List<Comment> getCommentsFromBook(int book_id);

    @Insert
    void insertComment(Comment... comment);
}
