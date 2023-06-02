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

public class EditBarangkebutuhan extends AppCompatActivity {

    private EditText jumlahkebutuhanEditText;
    private String idkbth, nama_kbth, jumlah_kbth;
    private Button editButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang_kebutuhan);

        // Ambil data dari Intent
        Intent intent = getIntent();
        idkbth = intent.getStringExtra("id_kbth");
        nama_kbth = intent.getStringExtra("nama_kbth");
        jumlah_kbth = intent.getStringExtra("jumlah_kbth");

        // Inisialisasi komponen tampilan
        TextView idkebutuhanTextView = findViewById(R.id.idkebutuhanTextView);
        TextView NamakebutuhanTextView = findViewById(R.id.NamakebutuhanTextView);
        jumlahkebutuhanEditText = findViewById(R.id.jumlahkebutuhanEditText);
        editButton = findViewById(R.id.editButton);

        // Set data ke komponen tampilan
        idkebutuhanTextView.setText(idkbth);
        NamakebutuhanTextView.setText(nama_kbth);
        jumlahkebutuhanEditText.setText(jumlah_kbth);

        // Set aksi klik pada tombol Edit
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBarangkebutuhan();
            }
        });
    }

    private void editBarangkebutuhan() {
        // Ambil nilai berat telur yang diubah
        String jumlah_kbthbaru = jumlahkebutuhanEditText.getText().toString().trim();

        // Buat permintaan POST ke API
        String url = "https://2aba-103-153-190-42.ngrok-free.app/api/edit_kbth_ayam.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                String message = jsonObject.getString("message");
                                Toast.makeText(EditBarangkebutuhan.this, message, Toast.LENGTH_SHORT).show();
                                finish(); // Kembali ke halaman sebelumnya setelah edit berhasil
                            } else {
                                String errorMessage = jsonObject.getString("message");
                                Toast.makeText(EditBarangkebutuhan.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(EditBarangkebutuhan.this, "berhasil diedit", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditBarangkebutuhan.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Kirim parameter ke API
                Map<String, String> params = new HashMap<>();
                params.put("id_kbth", idkbth);
                params.put("nama_kbth", nama_kbth);
                params.put("jumlah_kbth", jumlah_kbthbaru);
                return params;
            }
        };

        // Tambahkan permintaan ke antrian request Volley
        Volley.newRequestQueue(this).add(stringRequest);
    }


}
