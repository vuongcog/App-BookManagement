package com.example.doan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PhanLoaiActivity extends AppCompatActivity {
    ArrayList<PhanLoaiBook> dsPhanLoai;
    ArrayList<Book> dsBook;
    private boolean isVisible = false;
    ImageButton btnThemPL;
    AppDatabase db;
    private boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_loai);
        Toolbar toolbar = findViewById(R.id.toolbarphanloai);
        setSupportActionBar(toolbar);
        db = AppDatabase.getAppDatabase(this);
        dsPhanLoai = new ArrayList<>();
        dsBook = new ArrayList<>();
        dsPhanLoai.addAll ( db.PhanLoaiDao().getallPhanLoai());
        dsBook.addAll(db.bookDao().getAllBook());
        LinearLayout linearLayout =  findViewById(R.id.linearlayout);
        for (PhanLoaiBook plBook:dsPhanLoai) {
            ArrayList<Book> dsBook1= new ArrayList<>();
            for(Book book :dsBook)
            {
                if(plBook.getMaLoai().equals(book.getMaLoai()))
                {
                    if(plBook.getMaLoai().equals("2"))
                    {
                        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(this, book.getIdBook()+book.getImg()+book.getMaLoai(), Toast.LENGTH_SHORT).show();

                    }
                    dsBook1.add(book);
                }
            }
            DanhSachBookAdapter adapter1= new DanhSachBookAdapter(PhanLoaiActivity.this, R.layout.viewlistview,dsBook1);
            Button button = new Button(this);
            button.setText(plBook.getTenLoai());
            ListView lv1 = new ListView(this);
//
            lv1.setNestedScrollingEnabled(true);
            linearLayout.addView(button);
            linearLayout.addView(lv1);
            lv1.setAdapter(adapter1);
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    for(Book bo : dsBook1){
                        db.bookDao().deleteBook(bo);
                    }
                    db.PhanLoaiDao().deletePhanLoai(plBook);
                    finish();
                    startActivity(getIntent());
                    return false;
                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flag = false;
                    Intent intent = new Intent(PhanLoaiActivity.this,ThemSuaPhanLoai.class);
                    intent.putExtra("flag",flag);
                    intent.putExtra("plbook",plBook);
                    startActivityForResult(intent,7);
                }
            });
        }
        addControls();
        addEvents();
    }
    private void addEvents(){
        btnThemPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag = true;
                Intent intent = new Intent(PhanLoaiActivity.this,ThemSuaPhanLoai.class);
                intent.putExtra("flag",flag);
                startActivityForResult(intent,9);
            }
        });
    }
    private void addControls()
    {
        btnThemPL = findViewById(R.id.btnThemPL_PhanLoai);
    }
    private void hienThiDuLieu(){
        finish();
        startActivity(getIntent());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 7 && resultCode == 8){
            PhanLoaiBook plBook = (PhanLoaiBook) data.getSerializableExtra("suaplbook");
            Toast.makeText(this, plBook.maLoai+plBook.tenLoai, Toast.LENGTH_SHORT).show();
            db.PhanLoaiDao().updatePhanLoai(plBook);
            finish();
            startActivity(getIntent());
//            hienThiDuLieu();
        }
        if(requestCode == 9 && resultCode == 10){
            PhanLoaiBook phanLoaiBook =(PhanLoaiBook) data.getSerializableExtra("themplbook");
            db.PhanLoaiDao().insertPhanLoai(phanLoaiBook);
            finish();
            startActivity(getIntent());
//            hienThiDuLieu();
        }
        if(requestCode == 1 && resultCode == 2)
        {
            Book book = (Book) data.getSerializableExtra("book");
            db.bookDao().insertBook(book);
            hienThiDuLieu();
        }
        if(requestCode == 3 && resultCode == 4){
            Book book = (Book) data.getSerializableExtra("luuthaydoi");
            Toast.makeText(this, book.getIdBook()+book.getImg(), Toast.LENGTH_SHORT).show();
            db.bookDao().updateBook(book);
            hienThiDuLieu();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                // Xử lý khi người dùng chọn menu_item1
                Intent intent = new Intent(PhanLoaiActivity.this,ThongTinSinhVien.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                finish();

                // Xử lý khi người dùng chọn menu_item2
                return true;
            // Thêm các case khác nếu có nhiều mục menu khác
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}