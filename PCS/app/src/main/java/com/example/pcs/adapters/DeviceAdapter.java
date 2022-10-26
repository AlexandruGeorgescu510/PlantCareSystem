package com.example.pcs.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcs.R;
import com.example.pcs.activities.MainActivity;
import com.example.pcs.models.Device;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.DeviceAdapterViewHolder> {

    private ArrayList<Device> devices;
    private Context context;


    public DeviceAdapter(ArrayList<Device> devices, Context context) {
        this.devices = devices;
        this.context = context;
    }

    public class DeviceAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView idTextView, nameTextView;
        View view;

        public DeviceAdapterViewHolder(final View view) {
            super(view);
            this.view = view;

            idTextView = view.findViewById(R.id.id_TV);
            nameTextView = view.findViewById(R.id.name_TV);
        }

        public void bind(Device device){
            String name = device.getName();
            int id = device.getId();
            nameTextView.setText(name);
            idTextView.setText(String.valueOf(id));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("INT",id);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
    @NonNull
    @Override
    public DeviceAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new DeviceAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceAdapterViewHolder holder, int position) {
        Device device = devices.get(position);
        holder.bind(device);
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }
}
