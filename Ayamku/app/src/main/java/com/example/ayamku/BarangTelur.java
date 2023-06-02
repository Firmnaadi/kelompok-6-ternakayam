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

public class BarangTelur extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_URL = "https://2aba-103-153-190-42.ngrok-free.app/api/brg_telur.php";

    private ListView listView;
    private List<Egg> eggList;
    private EggAdapter eggAdapter;

    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_telur);

        listView = findViewById(R.id.listView);
        eggList = new ArrayList<>();
        eggAdapter = new EggAdapter(this, eggList);
        listView.setAdapter(eggAdapter);

        handler = new Handler(Looper.getMainLooper());

        // Fetch data pertama kali saat aktivitas dibuat
        fetchEggData();

        // Mulai polling untuk memperbarui data secara berkala
        startPolling();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Egg selectedEgg = eggList.get(position);

                // Buat Intent untuk memindahkan ke DetailEggActivity
                Intent intent = new Intent(BarangTelur.this, EditBarangTelur.class);

                // Kirim data yang diperlukan ke DetailEggActivity melalui Intent
                intent.putExtra("id_telur", selectedEgg.getIdTelur());
                intent.putExtra("grade", selectedEgg.getGrade());
                intent.putExtra("berat_telur", selectedEgg.getBeratTelur());

                // Mulai DetailEggActivity
                startActivity(intent);
            }
        });
    }


    private void fetchEggData() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Membersihkan eggList sebelum menambahkan data baru
                        eggList.clear();

                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String idTelur = jsonObject.getString("id_telur");
                                String grade = jsonObject.getString("gread");
                                String beratTelur = jsonObject.getString("berat_telur");

                                Egg egg = new Egg(idTelur, grade, beratTelur);
                                eggList.add(egg);
                            }

                            eggAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
                            Toast.makeText(BarangTelur.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error fetching egg data: " + error.getMessage());
                        Toast.makeText(BarangTelur.this, "Error fetching egg data", Toast.LENGTH_SHORT).show();
                    }
                });

        // Adding request to request queue
        Volley.newRequestQueue(this).add(request);
    }


    private void startPolling() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fetchEggData();
                handler.postDelayed(this, 1000); // Perbarui data setiap 5 detik
            }
        }, 1000); // Jeda awal 5 detik sebelum memulai polling
    }
}