package com.example.doan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {
    @Query("Select * from Book")
    List<Book> getAllBook();
    @Delete
    void deleteBook(Book book);
    @Insert
    void insertBook(Book book);
    @Update
    int updateBook(Book book);
}
