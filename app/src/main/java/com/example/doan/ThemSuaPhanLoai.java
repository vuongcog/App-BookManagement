package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThemSuaPhanLoai extends AppCompatActivity {
    EditText etMa;
    EditText etTen;
    Button btnThemPL,btnSuaPL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sua_phan_loai);
        Toolbar toolbar = findViewById(R.id.toolbarthemphanloai);
        setSupportActionBar(toolbar);
        addControls();
        addEvents();
    }
    private void addControls(){
        etMa = findViewById(R.id.etMaThemSuaPL);
        etTen = findViewById(R.id.etTenThemSuaPL);
//        txtMa.setText(phanLoaiBook.getMaLoai());
//        etTen.setText(phanLoaiBook.getTenLoai());
        btnThemPL = findViewById(R.id.btnThemPl);
        btnSuaPL = findViewById(R.id.btnSuaPL);
        Intent intent = getIntent();
        boolean flag = (boolean) intent.getBooleanExtra("flag",false);
        if(flag)
        {
            btnSuaPL.setEnabled(false);
        }
        else {
            etMa.setEnabled(false);
            btnThemPL.setEnabled(false);
            PhanLoaiBook phanLoaiBook = (PhanLoaiBook) intent.getSerializableExtra("plbook");
            etMa.setText(phanLoaiBook.getMaLoai());
            etTen.setText(phanLoaiBook.getTenLoai());
        }
    }

    private boolean timkiem (String id){

        AppDatabase db = AppDatabase.getAppDatabase(this);
        ArrayList< PhanLoaiBook> dsPhanLoai= new ArrayList<>();
        dsPhanLoai.addAll(db.PhanLoaiDao().getallPhanLoai());
        for(PhanLoaiBook pl :dsPhanLoai){
            if(pl.getMaLoai().equals(id))
            {
                return true;
            }
        }
        return false;
    }
    private void addEvents(){
        btnSuaPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhanLoaiBook plBook = new PhanLoaiBook();
                plBook.setTenLoai(etTen.getText().toString());
                plBook.setMaLoai(etMa.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("suaplbook",plBook);
                setResult(8,intent);
                finish();
            }
        });

        btnThemPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isId = timkiem(etMa.getText().toString());
                if(isId)
                {
                    AlertDialog.Builder alerDialog = new AlertDialog.Builder(ThemSuaPhanLoai.this);
                    alerDialog.setTitle("Tồn tại");
                    alerDialog.setMessage("Mã này đã tồn tại");
                    alerDialog.show();                }
               else {
                    PhanLoaiBook plBook = new PhanLoaiBook();
                    plBook.setTenLoai(etTen.getText().toString());
                    plBook.setMaLoai(etMa.getText().toString());
                    Intent intent = new Intent();
                    intent.putExtra("themplbook",plBook);
                    setResult(10,intent);
                    finish();
                }
            }
        });
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
                Intent intent = new Intent(ThemSuaPhanLoai.this,ThongTinSinhVien.class);
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