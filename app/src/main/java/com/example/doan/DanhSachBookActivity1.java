package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
public class DanhSachBookActivity1 extends AppCompatActivity {
    FloatingActionButton btnThem;
    Button btnPhanLoai;
    ArrayList<Book> dsBook;
    DanhSachBookAdapter adapter ;
    ListView lvDsBook;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_book1);
        DBConfigUtil.coppyDatabaseFromAssets(DanhSachBookActivity1.this);
        Toolbar toolbar = findViewById(R.id.toolbardanhsachbook);
        setSupportActionBar(toolbar);
        addControls();
        hienDuLieu();
        addEvents();

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
                Intent intent = new Intent(DanhSachBookActivity1.this,ThongTinSinhVien.class);
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
    @Override
    protected void onResume() {
        super.onResume();
        hienDuLieu();
    }
    private void addControls(){
        ImageView test = findViewById(R.id.test);
        db = AppDatabase.getAppDatabase(this);
        Book book1 = new Book("1","GDCD","SGK",10000,"Huynh Nhat Vuong");
        Book book2 = new Book("2","GDCD","SGK",10000,"Huynh Nhat Vuong");
        Book book3 = new Book("3","GDCD","SGK",10000,"Huynh Nhat Vuong");
        Book book4 = new Book("4","GDCD","SGK",10000,"Huynh Nhat Vuong");
        dsBook = new ArrayList<Book>();
        dsBook.add(book1);
        dsBook.add(book2);
        dsBook.add(book3);
        dsBook.add(book4);
        btnThem = findViewById(R.id.btnThem);
        btnPhanLoai = findViewById(R.id.btnPhanLoai);
        adapter = new DanhSachBookAdapter(DanhSachBookActivity1.this,R.layout.viewlistview,dsBook);
        ArrayAdapter<Book> adapter1 = new ArrayAdapter<>(DanhSachBookActivity1.this, android.R.layout.simple_list_item_1,dsBook);
        lvDsBook = findViewById(R.id.lvDanhSachBook);
        lvDsBook.setAdapter(adapter);
    }
    private void hienDuLieu(){
        dsBook.clear();
        dsBook.addAll(db.bookDao().getAllBook());
        adapter.notifyDataSetChanged();
    }
    private void addEvents(){
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachBookActivity1.this,ThemActivity1.class);
                startActivityForResult(intent,1);
            }
        });

        lvDsBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(DanhSachBookActivity1.this,"Hello",Toast.LENGTH_LONG).show();
            }
        });
        btnPhanLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachBookActivity1.this,PhanLoaiActivity.class);
                startActivityForResult(intent,6);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 2)
        {
            Book book = (Book) data.getSerializableExtra("book");
            db.bookDao().insertBook(book);
            hienDuLieu();
        }
        if(requestCode == 3 && resultCode == 4){
            Book book = (Book) data.getSerializableExtra("luuthaydoi");
            Toast.makeText(this, book.getIdBook()+book.getImg(), Toast.LENGTH_SHORT).show();
            db.bookDao().updateBook(book);
            hienDuLieu();
        }

    }

}