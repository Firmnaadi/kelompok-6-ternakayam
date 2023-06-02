package com.example.ayamku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditBarangAyam extends AppCompatActivity {

    private EditText beratTelurEditText;
    private String idayam, jumlahayam;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang_ayam);

        // Ambil data dari Intent
        Intent intent = getIntent();
        idayam = intent.getStringExtra("idAyam");
        jumlahayam = intent.getStringExtra("JumlahAyam");

        // Inisialisasi komponen tampilan
        TextView idAyamTextView = findViewById(R.id.idAyamTextView);
        beratTelurEditText = findViewById(R.id.jumlahAyamEditText);
        editButton = findViewById(R.id.editButton);

        // Set data ke komponen tampilan
        idAyamTextView.setText(idayam);
        beratTelurEditText.setText(jumlahayam);

        // Set aksi klik pada tombol Edit
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBarangAyam();
            }
        });
    }

    private void editBarangAyam() {
        // Ambil nilai berat telur yang diubah
        String beratAyamBaru = beratTelurEditText.getText().toString().trim();

        // Buat permintaan POST ke API
        String url = "https://2aba-103-153-190-42.ngrok-free.app/api/edit_ayam.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                String message = jsonObject.getString("message");
                                Toast.makeText(EditBarangAyam.this, message, Toast.LENGTH_SHORT).show();
                                finish(); // Kembali ke halaman sebelumnya setelah edit berhasil
                            } else {
                                String errorMessage = jsonObject.getString("message");
                                Toast.makeText(EditBarangAyam.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(EditBarangAyam.this, "berhasil diedit", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditBarangAyam.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Kirim parameter ke API
                Map<String, String> params = new HashMap<>();
                params.put("id_ayam", idayam);
                params.put("jumlah_ayam", beratAyamBaru);
                return params;
            }
        };

        // Tambahkan permintaan ke antrian request Volley
        Volley.newRequestQueue(this).add(stringRequest);
    }


}
