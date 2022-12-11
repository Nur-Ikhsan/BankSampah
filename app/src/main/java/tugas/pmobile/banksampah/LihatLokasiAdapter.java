package tugas.pmobile.banksampah;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tugas.pmobile.banksampah.Model.Location;

public class LihatLokasiAdapter extends RecyclerView.Adapter<LihatLokasiAdapter.ViewHolder> {
    private List<Location> locationList;
    private Activity activity;

    public LihatLokasiAdapter(List<Location> locationList, Activity activity) {
        this.locationList = locationList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public LihatLokasiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lihat_lokasi, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LihatLokasiAdapter.ViewHolder holder, int position) {
        holder.name.setText(locationList.get(position).getName());
        holder.address.setText(locationList.get(position).getAddress());
        holder.open_time.setText(locationList.get(position).getOpen_time().substring(0,5));
        holder.close_time.setText(locationList.get(position).getClose_time().substring(0,5));
        holder.contacts.setText(locationList.get(position).getContacts());
        holder.itemLocation.setOnClickListener(view -> {
            Log.d("Maps", "Di klik");
            Intent intent = new Intent(activity, LihatLokasiMapsActivity.class);
            intent.putExtra("latitude", locationList.get(position).getLatitude());
            intent.putExtra("longitude", locationList.get(position).getLongitude());
            intent.putExtra("name", locationList.get(position).getName());
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name = itemView.findViewById(R.id.nameTV);
        private TextView address = itemView.findViewById(R.id.addressTV2);
        private TextView open_time = itemView.findViewById(R.id.opeatationTimeTV2);
        private TextView close_time = itemView.findViewById(R.id.opeatationTimeTV4);
        private TextView contacts = itemView.findViewById(R.id.contactTV2);
        private RelativeLayout itemLocation = itemView.findViewById(R.id.lokasiRL);


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
