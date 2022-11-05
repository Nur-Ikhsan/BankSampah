package tugas.pmobile.banksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Login","Ini adalah onCreate()");
        setContentView(R.layout.activity_login);

        btn = findViewById(R.id.daftarBtn);
        btn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.v("Login","Ini adalah onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("Login","Ini adalah onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("Login","Ini adalah onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Login","Ini adalah onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Login","Ini adalah onDestroy()");
    }
}