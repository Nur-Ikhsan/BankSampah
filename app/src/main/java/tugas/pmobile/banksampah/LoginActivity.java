package tugas.pmobile.banksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
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
        final Boolean[] check = {false};
        LoginRequest loginRequest = new LoginRequest();
        emailEdt = findViewById(R.id.emailEdt);
        passwordEdt = findViewById(R.id.passwordEdt);

        loginRequest.setEmail(emailEdt.getText().toString());
        loginRequest.setPassword(passwordEdt.getText().toString());

        ApiService.endpoint().login(loginRequest).enqueue(new Callback<Response<Boolean>>() {
            @Override
            public void onResponse(Call<Response<Boolean>> call, retrofit2.Response<Response<Boolean>> response) {
                if (response.isSuccessful()) {
                    check[0] = response.body().getData();
                    Log.d("Login", "Login = "+ check[0].toString());
                    Toast toast;
                    if (Objects.equals(loginRequest.getEmail(), "") || Objects.equals(loginRequest.getPassword(), "")){
                        toast = Toast.makeText(getApplicationContext(), "Field Tidak Boleh Kosong", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        if (check[0]) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            toast = Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            toast = Toast.makeText(getApplicationContext(), "Email atau Password Anda Salah", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Response<Boolean>> call, Throwable t) {
                Log.d("Login", t.toString());
            }
        });
    }
}