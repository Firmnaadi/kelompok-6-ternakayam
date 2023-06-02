//package com.example.ayamku;
//
//import android.os.Bundle;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//import java.util.logging.Handler;
//
//public class AyamActivity extends AppCompatActivity {
//    private TextView txtJam;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ayam);
//
//        txtJam = findViewById(R.id.txtJam);
//
//        // Handler untuk memperbarui jam setiap detik
//        final Handler handler = new Handler() {
//        }
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                SimpleDateFormat waktuFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
//                String waktuSekarang = waktuFormat.format(new Date());
//                txtJam.setText(waktuSekarang);
//                handler.postDelayed(this, 1000);
//            }
//        });
//    }
//}
