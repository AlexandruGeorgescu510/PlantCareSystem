package com.example.pcs.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcs.R;
import com.example.pcs.adapters.DeviceAdapter;
import com.example.pcs.models.Device;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    RecyclerView recycler;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReferenceN;

    private ArrayList<Device> devices = new ArrayList<Device>();

    private String text;

    DeviceAdapter adapter;

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        recycler = findViewById(R.id.standard_RV);
        setDeviceInfo();
        setAdapter();
    }

    private void setAdapter() {
        adapter = new DeviceAdapter(devices, getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);


    }

    private void setDeviceInfo() {
        firebaseDatabase = FirebaseDatabase.getInstance();

        for(int i=1;i<4;i++) {
             DatabaseReference databaseReferenceN = firebaseDatabase.getReference("/device"+i+"/name");
             Device device = new Device();
             device.setId(i);

            databaseReferenceN.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String name = dataSnapshot.getValue(String.class);
                    device.setName(name);
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(MenuActivity.this, "Failure to get the data.", Toast.LENGTH_SHORT).show();
                }
            });

            devices.add(device);
        }
    }
}