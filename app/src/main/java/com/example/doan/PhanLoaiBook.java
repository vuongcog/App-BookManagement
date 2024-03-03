package com.example.doan;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class PhanLoaiBook implements Serializable {
    @PrimaryKey
    @NonNull
    String maLoai;
    String tenLoai;
    public PhanLoaiBook(){}
    public String getMaLoai() {
        return maLoai;
    }
    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }
    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
