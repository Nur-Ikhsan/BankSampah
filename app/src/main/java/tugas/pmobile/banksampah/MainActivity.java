package tugas.pmobile.banksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    RelativeLayout lihatLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lihatLokasi = findViewById(R.id.lihatLokasi);
        lihatLokasi.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, LihatLokasiActivity.class);
            startActivity(intent);
        });
    }
}