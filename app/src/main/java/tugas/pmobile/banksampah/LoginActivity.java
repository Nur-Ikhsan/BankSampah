package tugas.pmobile.banksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import tugas.pmobile.banksampah.Model.Login;
import tugas.pmobile.banksampah.Model.Response;
import tugas.pmobile.banksampah.retrofit.ApiService;


public class LoginActivity extends AppCompatActivity {
    Button daftarBtn, masukBtn;
    EditText usernameEdt, passwordEdt;

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
        Login login = new Login();
        usernameEdt = findViewById(R.id.usernameEdt);
        passwordEdt = findViewById(R.id.passwordEdt);

        login.setEmail(usernameEdt.getText().toString());
        login.setPassword(passwordEdt.getText().toString());

        ApiService.endpoint().login(login).enqueue(new Callback<Response<Boolean>>() {
            @Override
            public void onResponse(Call<Response<Boolean>> call, retrofit2.Response<Response<Boolean>> response) {
                if (response.isSuccessful()) {
                    check[0] = response.body().getData();
                    Log.d("Login", "Login = "+ check[0].toString());
                    if (check[0]) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast toast = Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Username atau Password Anda Salah", Toast.LENGTH_SHORT);
                        toast.show();
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