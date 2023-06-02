package com.example.ayamku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }



    public void btnayam(View view) {
        Intent intent = new Intent(MainActivity.this, HomeAyam.class);
        startActivity(intent);
    }
    public void btntelur(View view) {
        Intent intent = new Intent(MainActivity.this, HomeTelur.class);
        startActivity(intent);
    }

    public void btnsubmit(View view) {
        Intent intent = new Intent(MainActivity.this, PendataanActivity.class);
        startActivity(intent);
    }

}
