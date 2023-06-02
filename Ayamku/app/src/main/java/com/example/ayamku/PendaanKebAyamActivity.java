package com.example.ayamku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class PendaanKebAyamActivity extends AppCompatActivity {

    private EditText idKebutuhanEditText, tanggalEditText, jumlahEditText;
    private Button simpanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendataankebutuhanayam);

        // Inisialisasi komponen tampilan
        idKebutuhanEditText = findViewById(R.id.idtelur);
        jumlahEditText = findViewById(R.id.idberat);
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
        String idKebutuhan = idKebutuhanEditText.getText().toString().trim();
        String jumlah = jumlahEditText.getText().toString().trim();

        // Buat permintaan POST ke API
        String url = "https://2aba-103-153-190-42.ngrok-free.app/api/pengeluaran_kebutuhan.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String message = jsonObject.getString("message");
                            Toast.makeText(PendaanKebAyamActivity.this, message, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(PendaanKebAyamActivity.this, "Data Berhasil Masuk", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PendaanKebAyamActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Kirim parameter ke API
                Map<String, String> params = new HashMap<>();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date = new Date();
                String currentDate = dateFormat.format(date);

                params.put("id_kbth", idKebutuhan);
                params.put("tanggal", currentDate);
                params.put("jumlah", jumlah);
                return params;
            }
        };

        // Tambahkan permintaan ke antrian request Volley
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
