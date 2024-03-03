package com.example.doan;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Book")
public class Book implements Serializable {
    @PrimaryKey
    @NonNull
    String idBook;
    String tenBook;
    String tenTacGia;
    String loaiBook;
    Integer img;
    String NSX;
    float giaBook;
    String maLoai;
    public String getMaLoai() {
        return maLoai;
    }
    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }
    public Book (){
    }
    public String getNSX() {
        return NSX;
    }

    public void setNSX(String NSX) {
        this.NSX = NSX;
    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public Book(String idBook, String tenBook , String loaiBook, float giaBook, String tenTacGia)
    {
        this.giaBook=giaBook;
        this.tenBook=tenBook;
        this.idBook=idBook;
        this.loaiBook=loaiBook;
        this.tenTacGia=tenTacGia;
    }

    public float getGiaBook() {
        return giaBook;
    }

    public String getIdBook() {
        return idBook;
    }

    public String getLoaiBook() {
        return loaiBook;
    }

    public String getTenBook() {
        return tenBook;
    }

    public void setIdBook(String idBook) {
        this.idBook = idBook;
    }

    public void setGiaBook(float giaBook) {
        this.giaBook = giaBook;
    }

    public void setLoaiBook(String loaiBook) {
        this.loaiBook = loaiBook;
    }

    public void setTenBook(String tenBook) {
        this.tenBook = tenBook;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    @NonNull
    @Override
    public String toString() {
        return this.idBook+this.tenBook+maLoai+img;
    }
}
