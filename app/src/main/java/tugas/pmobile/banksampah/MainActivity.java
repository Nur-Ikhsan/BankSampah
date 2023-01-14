package tugas.pmobile.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import tugas.pmobile.banksampah.Model.Account;

public class MainActivity extends AppCompatActivity {

    RelativeLayout lihatLokasi;
    RelativeLayout daftarHarga;
    RelativeLayout riwayat;
    Button tabungSampahBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ambil intent dari activity lain
        Intent intent = getIntent();
        // ambil data dari intent
        Account account = (Account) intent.getSerializableExtra("account");

        lihatLokasi = findViewById(R.id.lihatLokasi);
        lihatLokasi.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, LihatLokasiActivity.class);
            startActivity(intent1);
        });

        daftarHarga = findViewById(R.id.daftarHarga);
        daftarHarga.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, DaftarHargaSampahActivity.class);
            startActivity(intent1);
        });

        tabungSampahBtn = findViewById(R.id.tabungSampahBtn);
        tabungSampahBtn.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, TabungSampahActivity.class);
            intent1.putExtra("account", account);
            startActivity(intent1);
        });

        riwayat = findViewById(R.id.riwayat);
        riwayat.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, RiwayatTabungSampahActivity.class);
            intent1.putExtra("account", account);
            startActivity(intent1);
        });

    }
}