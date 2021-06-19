package com.example.bookstore.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "comment",
        foreignKeys = {@ForeignKey(entity = Book.class,
                parentColumns = "id",
                childColumns = "book_id",
                onDelete = ForeignKey.CASCADE)})
public class Comment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "book_id", index = true)
    private int book_id;
    @ColumnInfo(name = "username")
    private String username;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "send_date")
    private Date send_date;

    public Comment(int id, int book_id, String username, String content, Date send_date) {
        this.id = id;
        this.book_id = book_id;
        this.username = username;
        this.content = content;
        this.send_date = send_date;
    }

    @Ignore
    public Comment(int book_id, String username, String content, Date send_date) {
        this.book_id = book_id;
        this.username = username;
        this.content = content;
        this.send_date = send_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSend_date() {
        return send_date;
    }

    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }
}
