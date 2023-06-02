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

public class EditBarangTelur extends AppCompatActivity {

    private EditText beratTelurEditText;
    private String idTelur, grade, beratTelur;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang_telur);

        // Ambil data dari Intent
        Intent intent = getIntent();
        idTelur = intent.getStringExtra("id_telur");
        grade = intent.getStringExtra("grade");
        beratTelur = intent.getStringExtra("berat_telur");

        // Inisialisasi komponen tampilan
        TextView idTelurTextView = findViewById(R.id.idTelurTextView);
        TextView gradeTextView = findViewById(R.id.gradeTextView);
        beratTelurEditText = findViewById(R.id.beratTelurEditText);
        editButton = findViewById(R.id.editButton);

        // Set data ke komponen tampilan
        idTelurTextView.setText(idTelur);
        gradeTextView.setText(grade);
        beratTelurEditText.setText(beratTelur);

        // Set aksi klik pada tombol Edit
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editBarangTelur();
            }
        });
    }

    private void editBarangTelur() {
        // Ambil nilai berat telur yang diubah
        String beratTelurBaru = beratTelurEditText.getText().toString().trim();

        // Buat permintaan POST ke API
        String url = "https://2aba-103-153-190-42.ngrok-free.app/api/edit_telur.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                String message = jsonObject.getString("message");
                                Toast.makeText(EditBarangTelur.this, message, Toast.LENGTH_SHORT).show();
                                finish(); // Kembali ke halaman sebelumnya setelah edit berhasil
                            } else {
                                String errorMessage = jsonObject.getString("message");
                                Toast.makeText(EditBarangTelur.this, "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(EditBarangTelur.this, "berhasil diedit", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(EditBarangTelur.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Kirim parameter ke API
                Map<String, String> params = new HashMap<>();
                params.put("id_telur", idTelur);
                params.put("gread", grade);
                params.put("berat_telur", beratTelurBaru);
                return params;
            }
        };

        // Tambahkan permintaan ke antrian request Volley
        Volley.newRequestQueue(this).add(stringRequest);
    }


}
