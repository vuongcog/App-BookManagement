package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
public class ThongTinSinhVien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_sinh_vien);
        Toolbar toolbar = findViewById(R.id.toolbarthongtinsinhvien);
        setSupportActionBar(toolbar);
        Button btnCall=findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });
    }
    public void makePhoneCall( ) {
        String phoneNumber = "0348079230";
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        startActivity(dialIntent);
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
                Intent intent = new Intent(ThongTinSinhVien.this,ThongTinSinhVien.class);
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