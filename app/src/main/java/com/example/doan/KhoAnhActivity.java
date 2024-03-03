package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class KhoAnhActivity extends AppCompatActivity {
    private GridView gridView;
    private ImageView selectImageView;
    private Integer[] imageIds= {
            R.drawable.vatli11,R.drawable.vatly12,R.drawable.benhnoikhoa,R.drawable.hoasinh,R.drawable.khoahoc4,R.drawable.khoahoccongnghe,R.drawable.kinhtehoc,R.drawable.sachkhatvong,R.drawable.yhoccotruyen,R.drawable.vatly10,R.drawable.vatly7
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kho_anh);
        gridView = findViewById(R.id.gridView);
        selectImageView = findViewById(R.id.selectedImageView);
        ImageAdapter adapter = new ImageAdapter(this, imageIds);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                Integer idImg = imageIds[i];
                intent.putExtra("idbook",idImg);
                System.out.println(idImg);
                setResult(3,intent);
                finish();
            }
        });
        selectImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageView.setVisibility(View.GONE);
            }
        });

    }
}