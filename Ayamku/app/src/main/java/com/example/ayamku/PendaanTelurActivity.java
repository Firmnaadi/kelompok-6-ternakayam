package com.example.ayamku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class  PendaanTelurActivity extends AppCompatActivity {
    EditText idPendataan, beratText;
    Button simpanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendataantelur);
        // Inisialisasi komponen tampilan
        idPendataan = findViewById(R.id.idtelur);
        beratText = findViewById(R.id.berat);
        simpanButton = findViewById(R.id.btn_send);

        // Set aksi klik pada tombol Simpan
        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahData();
            }
        });
    }

    private void tambahData() {
        // Ambil nilai dari inputan
        String idPendataanTelur= idPendataan.getText().toString().trim();
        String Berat = beratText.getText().toString().trim();

        // Buat permintaan POST ke API
        String url = "https://2aba-103-153-190-42.ngrok-free.app/api/tambah_telur.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            Toast.makeText(PendaanTelurActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(PendaanTelurActivity.this, "Data Berhasil Masuk", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PendaanTelurActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Kirim parameter ke API
                Map<String, String> params = new HashMap<>();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();
                String currentDate = dateFormat.format(date);

                params.put("id_telur", idPendataanTelur);
                params.put("tanggal", currentDate);
                params.put("berat", Berat);
                return params;
            }
        };

        // Tambahkan permintaan ke antrian request Volley
        Volley.newRequestQueue(this).add(stringRequest);
    }
}