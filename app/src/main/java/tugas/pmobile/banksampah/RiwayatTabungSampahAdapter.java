package tugas.pmobile.banksampah;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;

import tugas.pmobile.banksampah.Model.StoringTrash;
import tugas.pmobile.banksampah.retrofit.ApiService;

public class RiwayatTabungSampahAdapter extends RecyclerView.Adapter<RiwayatTabungSampahAdapter.ViewHolder> {
    private List<StoringTrash> trashList;
    private Activity activity;

    public RiwayatTabungSampahAdapter(List<StoringTrash> trashList, Activity activity) {
        this.trashList = trashList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RiwayatTabungSampahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_riwayat_tabung_sampah, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatTabungSampahAdapter.ViewHolder holder, int position) {
        Picasso.get().load(ApiService.getBaseUrl()+"api/trash/image/"+trashList.get(position).getTrashId().getImageId()).into(holder.trashImage);
        holder.trash.setText(trashList.get(position).getTrashId().getName());
        holder.location.setText(trashList.get(position).getLocationId().getName());
        holder.price.setText(trashList.get(position).getTrashId().getPrice().toString());
        holder.weight.setText(trashList.get(position).getWeight().toString());
        holder.total.setText(trashList.get(position).getTotal().toString());
        holder.status.setText(trashList.get(position).getStatus());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String dateString = formatter.format(trashList.get(position).getCreatedAt());
        holder.created.setText(dateString);
    }

    @Override
    public int getItemCount() {
        return trashList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView trashImage = itemView.findViewById(R.id.trashImage);
        private TextView trash = itemView.findViewById(R.id.trashTV);
        private TextView location = itemView.findViewById(R.id.locationTV2);
        private TextView price = itemView.findViewById(R.id.priceTV2);
        private TextView weight = itemView.findViewById(R.id.weightTV2);
        private TextView total = itemView.findViewById(R.id.totalTV2);
        private TextView status = itemView.findViewById(R.id.statusTV2);
        private TextView created = itemView.findViewById(R.id.created_atTV);
        private RelativeLayout riwayatRL = itemView.findViewById(R.id.riwayatRL);

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

