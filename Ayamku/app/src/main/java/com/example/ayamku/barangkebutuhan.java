package com.example.ayamku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class barangkebutuhan extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_URL = "https://2aba-103-153-190-42.ngrok-free.app/api/brg_kbth_ayam.php";

    private ListView listView;
    private List<Kebutuhan> kebutuhanList;
    private KebutuhanAdapter kebutuhanAdapter;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barangkebutuhan);

        listView = findViewById(R.id.listView);
        kebutuhanList = new ArrayList<>();
        kebutuhanAdapter = new KebutuhanAdapter(this, kebutuhanList);
        listView.setAdapter(kebutuhanAdapter);


        handler = new Handler(Looper.getMainLooper());

        // Fetch data pertama kali saat aktivitas dibuat
        fetchKebutuhanData();

        // Mulai polling untuk memperbarui data secara berkala
        startPolling();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Kebutuhan selectdKebutuhan = kebutuhanList.get(position);

                // Buat Intent untuk memindahkan ke DetailEggActivity
                Intent intent = new Intent(barangkebutuhan.this, EditBarangkebutuhan.class);

                // Kirim data yang diperlukan ke DetailEggActivity melalui Intent
                intent.putExtra("id_kbth", selectdKebutuhan.getIdKebutuhan());
                intent.putExtra("nama_kbth", selectdKebutuhan.getNamaKebutuhan());
                intent.putExtra("jumlah_kbth", selectdKebutuhan.getJumlahKebutuhan());

                // Mulai DetailEggActivity
                startActivity(intent);
            }
        });

    }

    private void fetchKebutuhanData() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Membersihkan eggList sebelum menambahkan data baru
                        kebutuhanList.clear();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String idKebutuhan = jsonObject.getString("id_kbth");
                                String namaKebutuhan = jsonObject.getString("nama_kbth");
                                String jumlahKebutuhan = jsonObject.getString("jumlah_kbth");

                                Kebutuhan kebutuhan = new Kebutuhan(idKebutuhan, namaKebutuhan, jumlahKebutuhan);
                                kebutuhanList.add(kebutuhan);
                            }

                            kebutuhanAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
                            Toast.makeText(barangkebutuhan.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error fetching kebutuhan data: " + error.getMessage());
                        Toast.makeText(barangkebutuhan.this, "Error fetching kebutuhan data", Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(request);
    }

    private void startPolling() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchKebutuhanData();
                handler.postDelayed(this, 1000); // Perbarui data setiap 5 detik
            }
        }, 1000); // Jeda awal 5 detik sebelum memulai polling
    }
}