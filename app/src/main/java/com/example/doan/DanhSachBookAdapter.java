package com.example.doan;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DanhSachBookAdapter extends ArrayAdapter<Book> {
    Activity activity;
    int resource;
    List<Book> listBook;
    public DanhSachBookAdapter(@NonNull Activity context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.resource = resource;
        this.listBook = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =  this.activity.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);
        TextView txtId= item.findViewById(R.id.txtId);
        TextView txtTen = item.findViewById(R.id.txtTen);
        TextView txtLoai = item.findViewById(R.id.txtLoai);
        TextView txtTacGia = item.findViewById(R.id.txtTacGia);
        TextView txtNSX = item.findViewById(R.id.txtNSX);
        ImageView txtAnh = item.findViewById(R.id.txtAnh);
        Toast.makeText(activity, "Hello", Toast.LENGTH_SHORT).show();
        Book book = this.listBook.get(position);
        txtId.setText(book.getIdBook());
        txtTen.setText(book.getTenBook());
        txtLoai.setText(book.getLoaiBook());
        if(book.getImg() != null)
        {
            txtAnh.setImageResource(book.getImg());
        }
        txtNSX.setText(book.getNSX());
        txtTacGia.setText(book.getTenTacGia());
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ChitietsachActivity1.class);
                intent.putExtra("chitiet",book);
                activity.startActivityForResult(intent,3);
            }
        });
        item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AppDatabase db ;
                db = AppDatabase.getAppDatabase(activity);
                db.bookDao().deleteBook(book);
                listBook.remove(position);
                notifyDataSetChanged();
                return false;
            }
        });
        return item;
    }
}
