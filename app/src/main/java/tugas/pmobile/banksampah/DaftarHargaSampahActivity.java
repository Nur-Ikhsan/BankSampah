package tugas.pmobile.banksampah;

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
import tugas.pmobile.banksampah.Model.Response;
import tugas.pmobile.banksampah.Model.Trash;
import tugas.pmobile.banksampah.retrofit.ApiService;

public class DaftarHargaSampahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_harga_sampah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.v("DaftarHargaSampah", "Test");


        ApiService.endpoint().getTrash().enqueue(new Callback<Response<List<Trash>>>() {
            @Override
            public void onResponse(Call<Response<List<Trash>>> call, retrofit2.Response<Response<List<Trash>>> response) {
                List<Trash> trash = response.body().getData();
                DaftarHargaSampahAdapter daftarHargaSampahAdapter = new DaftarHargaSampahAdapter(trash, DaftarHargaSampahActivity.this);
                Log.v("DaftarHargaSampah", "Daftar Harga Ditampilkan");
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(daftarHargaSampahAdapter);
            }
            @Override
            public void onFailure(Call<Response<List<Trash>>> call, Throwable t) {
                Log.d("DaftarHargaSampah", t.toString());
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