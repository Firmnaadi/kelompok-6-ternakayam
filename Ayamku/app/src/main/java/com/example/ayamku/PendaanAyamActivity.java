package com.example.ayamku;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class PendaanAyamActivity extends AppCompatActivity {

    private EditText idAyamEditText, keteranganEditText, tanggalEditText, jumlahEditText;
    private Button simpanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendataanayam);

        idAyamEditText = findViewById(R.id.idtelur);
        keteranganEditText = findViewById(R.id.idketerangan);
        tanggalEditText = findViewById(R.id.idtanggal);
        jumlahEditText = findViewById(R.id.jumlah);
        simpanButton = findViewById(R.id.btn_simpan);

        simpanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idAyam = idAyamEditText.getText().toString();
                String keterangan = keteranganEditText.getText().toString();
                String tanggal = tanggalEditText.getText().toString();
                String jumlah = jumlahEditText.getText().toString();

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

                String url = "https://2aba-103-153-190-42.ngrok-free.app/api/pengeluaran_ayam.php";
                Map<String, String> params = new HashMap<>();
                params.put("id_ayam", idAyam);
                params.put("keterangan", keterangan);
                params.put("jumlah", jumlah);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String result = jsonObject.getString("result");
                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(PendaanAyamActivity.this, "Data Berhasil Masuk", Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        return params;
                    }
                };

                requestQueue.add(stringRequest);
            }
        });
    }
}
