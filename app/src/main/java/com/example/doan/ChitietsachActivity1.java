package com.example.doan;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;


public class ChitietsachActivity1 extends AppCompatActivity {
    EditText etTen,etLoai,etNSX,etTacgia,etMaLoai,etId;

    Button btnLuuThayDoi;
    ImageView imvChitiet;
    Integer idBook;
    Book book;
    Spinner spinnerMaLoai;
    ArrayAdapter<String> adapter;
    ArrayList<String> dsMaLoai;
    ArrayList<PhanLoaiBook> dspl;
    AppDatabase db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsach1);
        Toolbar toolbar = findViewById(R.id.toolbarchitiet);
        setSupportActionBar(toolbar);
        addControls();
        hienDanhSach();
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
                Intent intent = new Intent(ChitietsachActivity1.this,ThongTinSinhVien.class);
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
    private void addControls(){
        db= AppDatabase.getAppDatabase(this);
        dspl = new ArrayList<>();
        dsMaLoai = new ArrayList<>();
        dspl.addAll(db.PhanLoaiDao().getallPhanLoai());
        for (PhanLoaiBook pl : dspl)
        {
            dsMaLoai.add(pl.getMaLoai().toString());
        }
        spinnerMaLoai = findViewById(R.id.spinnerMaLoaiSua);
        adapter = new ArrayAdapter<>(ChitietsachActivity1.this, android.R.layout.simple_list_item_1,dsMaLoai);
        spinnerMaLoai.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        etId=findViewById(R.id.etChiTietId);
        etId.setEnabled(false);
        etTen=findViewById(R.id.etChiTietTen);
        etLoai=findViewById(R.id.etChiTietLoai);
        etNSX=findViewById(R.id.etChiTietNSX);
        etTacgia=findViewById(R.id.etChiTietTacgia);
        btnLuuThayDoi = findViewById(R.id.btnLuuThayDoi);
        imvChitiet = findViewById(R.id.imvChiTiet);
    }
    private void hienDanhSach(){
        Intent intent = getIntent();
        book = (Book) intent.getSerializableExtra("chitiet");
        etId.setText(book.getIdBook()       );
        etTen.setText(book.getTenBook());
        etLoai.setText(book.getLoaiBook());
        etTacgia.setText(book.getTenTacGia());
        etNSX.setText(book.getNSX());
        imvChitiet.setImageResource(book.getImg());
        int position = dsMaLoai.indexOf(book.getMaLoai());
        spinnerMaLoai.setSelection(position);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        idBook = data.getIntExtra("idbook",0);
        imvChitiet.setImageResource(idBook);
    }
    private void addEvents(){
        btnLuuThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book1 = new Book();
                if(idBook == null)
                {
                    book1.setImg(book.getImg());
                }
                else {
                    book1.setImg(idBook);
                }
                book1.setIdBook(etId.getText().toString());
                book1.setTenBook(etTen.getText().toString());
                book1.setLoaiBook(etLoai.getText().toString());
                book1.setTenTacGia(etTacgia.getText().toString());
                book1.setNSX(etNSX.getText().toString());
                book1.setMaLoai((String) spinnerMaLoai.getSelectedItem());
                Intent intent = new Intent();
                intent.putExtra("luuthaydoi",book1);
                setResult(4,intent);
                finish();
            }
        });
        imvChitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ChitietsachActivity1.this, "Hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChitietsachActivity1.this,KhoAnhActivity.class);
                startActivityForResult(intent,5);
            }
        });
    }
}