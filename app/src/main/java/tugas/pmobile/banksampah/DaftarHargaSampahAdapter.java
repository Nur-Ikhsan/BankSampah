package tugas.pmobile.banksampah;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import tugas.pmobile.banksampah.Model.Trash;
import tugas.pmobile.banksampah.retrofit.ApiService;

class DaftarHargaSampahAdapter extends RecyclerView.Adapter<DaftarHargaSampahAdapter.ViewHolder> {
    private List<Trash> trashList;
    private Activity activity;

    public DaftarHargaSampahAdapter(List<Trash> trash, DaftarHargaSampahActivity daftarHargaSampahActivity) {
        this.trashList = trash;
        this.activity = daftarHargaSampahActivity;
    }

    @NonNull
    @Override
    public DaftarHargaSampahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_daftar_harga_sampah, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull DaftarHargaSampahAdapter.ViewHolder holder, int position) {
        Trash trash = trashList.get(position);
        Picasso.get().load(ApiService.getBaseUrl()+"api/trash/image/"+trash.getImageId()).into(holder.img_trash);
        holder.name.setText(trash.getName());
        holder.price.setText(String.format("%s/kg", String.valueOf(trash.getPrice())));
    }

    @Override
    public int getItemCount() {
        return trashList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_trash = itemView.findViewById(R.id.img_trash);
        private TextView name = itemView.findViewById(R.id.nameTV);
        private TextView price = itemView.findViewById(R.id.priceTV);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}