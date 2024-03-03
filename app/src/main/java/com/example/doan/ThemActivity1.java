package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ThemActivity1 extends AppCompatActivity {
    FloatingActionButton btnThemBook;
    ImageButton btnChonAnh;
    EditText etId, etLoai, etTacgia, etTen, etNSX;
    Spinner spinnerMaLoai;
    ImageView imageView;
    Integer idBook;
    ArrayAdapter<String> adapter;
    ArrayList<String> dsMaLoai;
    ArrayList<PhanLoaiBook> dspl;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them1);
        Toolbar toolbar = findViewById(R.id.toolbarthembook);
        setSupportActionBar(toolbar);
        addControls();
        addEvents();
    }

    private void addControls() {
        db = AppDatabase.getAppDatabase(this);
        dspl = new ArrayList<>();
        dsMaLoai = new ArrayList<>();
        dspl.addAll(db.PhanLoaiDao().getallPhanLoai());
        for (PhanLoaiBook pl : dspl) {
            dsMaLoai.add(pl.getMaLoai().toString());
        }
        spinnerMaLoai = findViewById(R.id.spinnerMaLoai);
        adapter = new ArrayAdapter<>(ThemActivity1.this, android.R.layout.simple_list_item_1, dsMaLoai);
        spinnerMaLoai.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        etId = findViewById(R.id.etIdThem);
        etTen = findViewById(R.id.etTenThem);
        etLoai = findViewById(R.id.etLoaiThem);
        etTacgia = findViewById(R.id.etTacGiaThem);
        etNSX = findViewById(R.id.etNSXThem);
        btnThemBook = findViewById(R.id.btnThemBook);
        btnChonAnh = findViewById(R.id.btnChonAnh);
        imageView = findViewById(R.id.imgView);
    }
    private boolean timkiem (String id){
        ArrayList<Book> dsbook= new ArrayList<>();
        dsbook.addAll(db.bookDao().getAllBook());
        for(Book book :dsbook){
            if(book.getIdBook().equals(id))
            {
                return true;
            }
        }
        return false;
    }
    private void addEvents(){
        btnThemBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean flag = timkiem(etId.getText().toString());
               if(flag == false)
               {
                   Book book = new Book();
                   book.setIdBook(etId.getText().toString());
                   book.setTenBook(etTen.getText().toString());
                   book.setLoaiBook(etLoai.getText().toString());
                   book.setTenTacGia(etTacgia.getText().toString());
                   book.setImg(idBook);
                   book.setNSX(etNSX.getText().toString());
                   book.setMaLoai((String) spinnerMaLoai.getSelectedItem());
                   Intent intent = new Intent();
                   intent.putExtra("book",book);
                   setResult(2,intent);
                   finish();
               }
               else {
                   AlertDialog.Builder alerDialog = new AlertDialog.Builder(ThemActivity1.this);
                   alerDialog.setTitle("Tồn tại");
                   alerDialog.setMessage("Mã này đã tồn tại");
                   alerDialog.show();
               }

            }
        });
        btnChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemActivity1.this,KhoAnhActivity.class);
                startActivityForResult(intent,2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == 3)
        {
            idBook = data.getIntExtra("idbook",0);
            imageView.setImageResource(idBook);
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
                Intent intent = new Intent(ThemActivity1.this,ThongTinSinhVien.class);
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