package com.example.ayamku;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JadwalActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private RequestQueue requestQueue;
    private final String url = "https://9d7b-103-109-209-244.ngrok-free.app/api/home_jadwal.php"; // Ganti dengan URL API yang sesuai

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        listView = findViewById(R.id.list_view);
        adapter = new ArrayAdapter<>(this, R.layout.list_item_layout);
        listView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        fetchDataFromAPI();
    }

    private void fetchDataFromAPI() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<String> jamList = new ArrayList<>();
                            String tanggal = "";

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                if (jsonObject.has("jam")) {
                                    String jam = jsonObject.getString("jam");
                                    jamList.add(jam);
                                }

                                if (jsonObject.has("tanggal")) {
                                    tanggal = jsonObject.getString("tanggal");
                                }
                            }

                            adapter.addAll(jamList);

                            if (!tanggal.isEmpty()) {
                                adapter.add("Tanggal: " + tanggal);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(JadwalActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(request);
    }
}
