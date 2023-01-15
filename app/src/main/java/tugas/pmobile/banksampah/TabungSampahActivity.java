package tugas.pmobile.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import tugas.pmobile.banksampah.Model.Account;
import tugas.pmobile.banksampah.Model.Location;
import tugas.pmobile.banksampah.Model.Response;
import tugas.pmobile.banksampah.Model.StoringTrash;
import tugas.pmobile.banksampah.Model.StoringTrashRequest;
import tugas.pmobile.banksampah.Model.Trash;
import tugas.pmobile.banksampah.retrofit.ApiService;

public class TabungSampahActivity extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private EditText inputAngka;
    private Button btnSubmit;
    private List<Trash> trashList;
    private List<Location> locationsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabung_sampah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Account account = (Account) intent.getSerializableExtra("account");

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        inputAngka = findViewById(R.id.input_angka);
        btnSubmit = findViewById(R.id.btn_submit);

        ApiService.endpoint().getTrash().enqueue(new Callback<Response<List<Trash>>>() {
            @Override
            public void onResponse(Call<Response<List<Trash>>> call, retrofit2.Response<Response<List<Trash>>> response) {
                trashList = response.body().getData();
                ArrayList<String> trashNameList = new ArrayList<>();
                for (Trash trash : trashList) {
                    trashNameList.add(trash.getName());
                }
                ArrayAdapter<String> adapter1 = new ArrayAdapter<>(TabungSampahActivity.this, android.R.layout.simple_spinner_item, trashNameList);
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<Response<List<Trash>>> call, Throwable t) {
                Log.d("TabungSampahActivity", t.toString());
            }

        });

        ApiService.endpoint().getLocations().enqueue(new Callback<Response<List<Location>>>() {
            @Override
            public void onResponse(Call<Response<List<Location>>> call, retrofit2.Response<Response<List<Location>>> response) {
                locationsList = response.body().getData();
                ArrayList<String> locationNameList = new ArrayList<>();
                for (Location location : locationsList) {
                    locationNameList.add(location.getName());
                }
                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(TabungSampahActivity.this, android.R.layout.simple_spinner_item, locationNameList);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<Response<List<Location>>> call, Throwable t) {
                Log.d("TabungSampahActivity", t.toString());
            }
        });

        btnSubmit.setOnClickListener(view -> {
            int selectedTrashId = trashList.get(spinner1.getSelectedItemPosition()).getId();
            int weight = Integer.parseInt(inputAngka.getText().toString());
            int selectedLocationId = locationsList.get(spinner2.getSelectedItemPosition()).getId();

            // Kirim data ke server
            StoringTrashRequest storingTrashRequest = new StoringTrashRequest(account.getId(), selectedTrashId, selectedLocationId, weight);
            ApiService.endpoint().storingTrash(storingTrashRequest).enqueue(new Callback<Response<StoringTrash>>() {
                @Override
                public void onResponse(Call<Response<StoringTrash>> call, retrofit2.Response<Response<StoringTrash>> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(TabungSampahActivity.this, "Data berhasil dikirim", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TabungSampahActivity.this, AfterTabungSampahActivity.class);
                        intent.putExtra("namaSampah",trashList.get(spinner1.getSelectedItemPosition()).getName());
                        intent.putExtra("banyakSampah", weight);
                        intent.putExtra("lokasi", locationsList.get(spinner2.getSelectedItemPosition()).getName());
                        startActivity(intent);
                    } else {
                        Toast.makeText(TabungSampahActivity.this, "Terjadi kesalahan saat mengirim data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<Response<StoringTrash>> call, Throwable t) {
                    Toast.makeText(TabungSampahActivity.this, "Terjadi kesalahan saat mengirim data", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
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