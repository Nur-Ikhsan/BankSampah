package tugas.pmobile.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import tugas.pmobile.banksampah.Model.Account;
import tugas.pmobile.banksampah.Model.LoginRequest;
import tugas.pmobile.banksampah.Model.Response;
import tugas.pmobile.banksampah.retrofit.ApiService;


public class LoginActivity extends AppCompatActivity {
    Button daftarBtn, masukBtn;
    EditText emailEdt, passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Login", "Ini adalah onCreate()");
        setContentView(R.layout.activity_login);

        daftarBtn = findViewById(R.id.daftarBtn);
        daftarBtn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        masukBtn = findViewById(R.id.masukBtn);
        masukBtn.setOnClickListener(view -> {
            login();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("Login", "Ini adalah onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Login", "Ini adalah onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("Login", "Ini adalah onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Login", "Ini adalah onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Login", "Ini adalah onDestroy()");
    }

    private void login() {
        LoginRequest loginRequest = new LoginRequest();
        emailEdt = findViewById(R.id.emailEdt);
        passwordEdt = findViewById(R.id.passwordEdt);

        loginRequest.setEmail(emailEdt.getText().toString());
        loginRequest.setPassword(passwordEdt.getText().toString());

        if (Objects.equals(loginRequest.getEmail(), "") || Objects.equals(loginRequest.getPassword(), "")){
            Toast.makeText(getApplicationContext(), "Field Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
        } else {
            ApiService.endpoint().login(loginRequest).enqueue(new Callback<Response<JsonElement>>() {
                @Override
                public void onResponse(Call<Response<JsonElement>> call, retrofit2.Response<Response<JsonElement>> response) {
                    Toast toast;
                    JsonElement jsonElement = response.body().getData();
                    if (jsonElement.isJsonObject()) {
                        Account account = new Gson().fromJson(jsonElement, Account.class);
                        Log.d("Login", "Login = "+ account.toString());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("account", account);
                        startActivity(intent);
                        toast = Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (jsonElement.isJsonPrimitive()) {
                        toast = Toast.makeText(getApplicationContext(), "Email atau Password Anda Salah", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                @Override
                public void onFailure(Call<Response<JsonElement>> call, Throwable t) {
                    Log.d("Login", t.toString());
                }
            });

        }
    }
}