package com.example.ayamku;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class ayam_pendapatan extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String API_URL = "https://2aba-103-153-190-42.ngrok-free.app/api/brg_ayam.php";

    private ListView listView;
    private AyamAdapter adapter;
    private List<Ayam> ayamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang_ayam);

        listView = findViewById(R.id.listView);
        ayamList = new ArrayList<>();
        adapter = new AyamAdapter(this, ayamList);
        listView.setAdapter(adapter);

        fetchAyamData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ayam selectedAyam = ayamList.get(position);

                // Buat Intent untuk memindahkan ke DetailEggActivity
                Intent intent = new Intent(ayam_pendapatan.this, EditBarangAyam.class);

                // Kirim data yang diperlukan ke DetailEggActivity melalui Intent
                intent.putExtra("idAyam", selectedAyam.getIdAyam());
                intent.putExtra("JumlahAyam", selectedAyam.getJumlahAyam());

                // Mulai DetailEggActivity
                startActivity(intent);
            }
        });
    }

    private void fetchAyamData() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Menghapus data yang ada di dalam list sebelumnya
                            ayamList.clear();

                            // Parsing JSON response
                            JSONObject jsonObject = response.getJSONObject(0);
                            String idAyam = jsonObject.getString("id_ayam");
                            String jumlahAyam = jsonObject.getString("jumlah_ayam");

                            // Menambahkan data ke dalam list
                            ayamList.add(new Ayam(idAyam, jumlahAyam));

                            // Memberi tahu adapter bahwa data telah berubah
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ayam_pendapatan.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "Error: " + error.getMessage());
                        Toast.makeText(ayam_pendapatan.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(request);
    }
}
