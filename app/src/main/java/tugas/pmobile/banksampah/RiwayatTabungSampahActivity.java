package tugas.pmobile.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import tugas.pmobile.banksampah.Model.Account;
import tugas.pmobile.banksampah.Model.Response;
import tugas.pmobile.banksampah.Model.StoringTrash;
import tugas.pmobile.banksampah.retrofit.ApiService;

public class RiwayatTabungSampahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_tabung_sampah);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");
        int accountId = account.getId();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.v("RiwayatTabungSampah", "Test");

        ApiService.endpoint().getStoringTrashByAccountId(accountId).enqueue(new Callback<Response<List<StoringTrash>>>() {
            @Override
            public void onResponse(Call<Response<List<StoringTrash>>> call, retrofit2.Response<Response<List<StoringTrash>>> response) {
                if (response.isSuccessful()) {
                    List<StoringTrash> storingTrashList = response.body().getData();
                    RiwayatTabungSampahAdapter riwayatTabungSampahAdapter = new RiwayatTabungSampahAdapter(storingTrashList, RiwayatTabungSampahActivity.this);
                    Log.v("RiwayatTabungSampah", "Riwayat Ditampilkan");
                    RecyclerView recyclerView = findViewById(R.id.recyclerView);
                    recyclerView.setAdapter(riwayatTabungSampahAdapter);
                }
                else {
                    Log.v("RiwayatTabungSampah", "Riwayat Gagal Ditampilkan");
                }
            }

            @Override
            public void onFailure(Call<Response<List<StoringTrash>>> call, Throwable t) {
                Log.d("RiwayatTabungSampah", t.toString());
            }
        });
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
