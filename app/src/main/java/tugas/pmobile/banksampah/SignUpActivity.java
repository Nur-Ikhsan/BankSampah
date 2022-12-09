package tugas.pmobile.banksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import tugas.pmobile.banksampah.Model.Account;
import tugas.pmobile.banksampah.Model.Response;
import tugas.pmobile.banksampah.Model.SignUpRequest;
import tugas.pmobile.banksampah.retrofit.ApiService;


public class SignUpActivity extends AppCompatActivity {
    TextView showName;
    EditText name, email, password, rePassword;
    Button btn, masukBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        btn = findViewById(R.id.daftarkanBtn);
        btn.setOnClickListener(view -> {
            final Boolean[] check = {false};
            final Toast[] toast = new Toast[1];
            SignUpRequest signUpRequest = new SignUpRequest();
            name = findViewById(R.id.nameEdt);
            email = findViewById(R.id.emailEdt);
            password = findViewById(R.id.passwordEdt);
            rePassword = findViewById(R.id.rePasswordEdt);

            if (!name.getText().toString().equals("") && !email.getText().toString().equals("") && !password.getText().toString().equals("") && !rePassword.getText().toString().equals("")) {
                if (password.getText().toString().equals(rePassword.getText().toString())) {
                    signUpRequest.setName(name.getText().toString());
                    signUpRequest.setEmail(email.getText().toString());
                    signUpRequest.setPassword(password.getText().toString());
                    ApiService.endpoint().signUp(signUpRequest).enqueue(new Callback<Response<String>>() {
                        @Override
                        public void onResponse(Call<Response<String>> call, retrofit2.Response<Response<String>> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getCode() == 200) {
                                    Log.d("SignUp : ", "Berhasil");
                                    hasil(view);
                                } else if (response.body().getCode() == 409) {
                                    toast[0] = Toast.makeText(getApplicationContext(), "Email Telah Terdaftar", Toast.LENGTH_SHORT);
                                    toast[0].show();
                                } else if (response.body().getCode() == 400) {
                                    toast[0] = Toast.makeText(getApplicationContext(), "Format Email Salah", Toast.LENGTH_SHORT);
                                    toast[0].show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<String>> call, Throwable t) {
                            Log.d("SignUp", t.toString());
                        }
                    });
                } else {
                    toast[0] = Toast.makeText(getApplicationContext(), "Password Harus Sama", Toast.LENGTH_SHORT);
                    toast[0].show();
                }
            } else {
                toast[0] = Toast.makeText(getApplicationContext(), "Field Tidak Boleh Kosong", Toast.LENGTH_SHORT);
                toast[0].show();
            }
        });

    }

    public void hasil(View view) {
        setContentView(R.layout.view_afterdaftar);
        showName = (TextView) findViewById(R.id.tampilNama);
        showName.setText(name.getText());

        masukBtn = findViewById(R.id.masukBtn);
        masukBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}