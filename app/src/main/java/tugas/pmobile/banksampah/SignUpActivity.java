package tugas.pmobile.banksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SignUpActivity extends AppCompatActivity {
    TextView showName;
    EditText Name;
    Button btn, masukBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Name = (EditText) findViewById(R.id.namaEdt);

        btn = findViewById(R.id.daftarkanBtn);
        btn.setOnClickListener(view -> hasil(view));

    }
    public void hasil(View view) {
        setContentView(R.layout.view_afterdaftar);
        showName = (TextView) findViewById(R.id.tampilNama);
        showName.setText(Name.getText());

        masukBtn = findViewById(R.id.masukBtn);
        masukBtn.setOnClickListener(view1 -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}