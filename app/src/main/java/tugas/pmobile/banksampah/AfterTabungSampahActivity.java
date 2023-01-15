package tugas.pmobile.banksampah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import tugas.pmobile.banksampah.R;

public class AfterTabungSampahActivity extends AppCompatActivity {
    TextView namaSampah, banyakSampah, lokasiBankSampah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_tabung_sampah);

        Toolbar toolbar = findViewById(R.id.toolbarAfterTabung);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String namaSampahIntent = intent.getStringExtra("namaSampah");
        String banyakSampahIntent = intent.getStringExtra("banyakSampah");
        String lokasiBankSampahIntent = intent.getStringExtra("lokasi");


        namaSampah = findViewById(R.id.namaSampah);
        banyakSampah = findViewById(R.id.banyakSampah);
        lokasiBankSampah = findViewById(R.id.lokasiBanksSampah);

        namaSampah.setText(namaSampahIntent);
        banyakSampah.setText(banyakSampahIntent);
        lokasiBankSampah.setText(lokasiBankSampahIntent);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}