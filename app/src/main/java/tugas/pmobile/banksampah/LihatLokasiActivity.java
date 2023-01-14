package tugas.pmobile.banksampah;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import tugas.pmobile.banksampah.Model.Location;
import tugas.pmobile.banksampah.Model.Response;
import tugas.pmobile.banksampah.retrofit.ApiService;

public class LihatLokasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_lokasi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.v("LihatLokasiActivity", "Test");


        ApiService.endpoint().getLocations().enqueue(new Callback<Response<List<Location>>>() {
            @Override
            public void onResponse(Call<Response<List<Location>>> call, retrofit2.Response<Response<List<Location>>> response) {
                List<Location> locations = response.body().getData();
                LihatLokasiAdapter lihatLokasiAdapter = new LihatLokasiAdapter(locations, LihatLokasiActivity.this);
                Log.v("LihatLokasiActivity", "Lokasi Ditampilkan");
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setAdapter(lihatLokasiAdapter);

            }

            @Override
            public void onFailure(Call<Response<List<Location>>> call, Throwable t) {
                Log.d("LihatLokasiActivity", t.toString());
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