package com.example.bookstore.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookstore.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Query("Select * from category")
    List<Category> getAllCategories();
    @Query("Select * from category where id = :category_id")
    List<Category> getCategoriesById(int category_id);
    @Insert
    void insertCategory(Category... category);
    @Update
    void updateCategory(Category... category);
    @Delete
    void deleteCategory(Category... category);
}
