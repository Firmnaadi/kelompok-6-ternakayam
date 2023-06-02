package com.example.ayamku;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inisialisasi komponen tampilan
        emailEditText = findViewById(R.id.txtUserTeknisi);
        passwordEditText = findViewById(R.id.txtPasswordTeknisi);
        signInButton = findViewById(R.id.btnSignInTeknisi);

        // Set aksi klik pada tombol Sign In
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        // Ambil nilai dari inputan
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Buat permintaan POST ke API
        String url = "https://2aba-103-153-190-42.ngrok-free.app/api/login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            if (jsonArray.length() > 0) {
                                // Login berhasil, lakukan tindakan setelah login sukses
                                Toast.makeText(LoginActivity.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                                // Tambahkan kode untuk berpindah ke halaman selanjutnya atau melakukan tindakan setelah login sukses
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            } else {
                                // Login gagal, tampilkan pesan kesalahan
                                Toast.makeText(LoginActivity.this, "Login gagal. Periksa kembali email dan password Anda.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Kirim parameter ke API
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        // Tambahkan permintaan ke antrian request Volley
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
