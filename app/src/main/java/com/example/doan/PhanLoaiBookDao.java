package com.example.doan;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhanLoaiBookDao {
    @Query("select * from PhanLoaiBook")
    List<PhanLoaiBook> getallPhanLoai();
    @Update
    int updatePhanLoai(PhanLoaiBook phanLoaiBook);
    @Insert
    void insertPhanLoai(PhanLoaiBook phanLoaiBook);
    @Delete
    void deletePhanLoai(PhanLoaiBook phanLoaiBook);
}
