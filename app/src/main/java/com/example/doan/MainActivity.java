package com.example.doan;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button btnDangnhap,btnHellowrod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        addControls();
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
                Intent intent = new Intent(MainActivity.this,ThongTinSinhVien.class);
                startActivity(intent);
                return true;
            case R.id.exit:
                // Xử lý khi người dùng chọn menu_item2
                finish();
                return true;
            // Thêm các case khác nếu có nhiều mục menu khác
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addControls(){
        username = findViewById(R.id.userName);
        password = findViewById(R.id.passWord);
        btnDangnhap = findViewById(R.id.btnDangNhap);
    }
    private void  addEvents(){
        btnDangnhap.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("123"))
                {
                    Intent intent = new Intent(MainActivity.this,DanhSachBookActivity1.class);
                    startActivity(intent);
                }
                else {
                    AlertDialog.Builder alerDialog = new AlertDialog.Builder(MainActivity.this);
                    alerDialog.setTitle("Sai mật ");
                    alerDialog.setMessage("Sai mật khẩu");
                    alerDialog.show();                }

            }
        });

    }

}