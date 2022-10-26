package com.example.pcs.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.pcs.R;
import com.example.pcs.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference1;

    private TextView latTV, lonTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        int id = intent.getIntExtra("INT",1);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference1 = firebaseDatabase.getReference();

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                double latitude = dataSnapshot.child("/device"+id+"/latitude").getValue(Double.class);
                double longitude = dataSnapshot.child("/device"+id+"/longitude").getValue(Double.class);
                latTV = findViewById(R.id.latitude_textview);
                lonTV = findViewById(R.id.longitude_textview);
                latTV.setText(Double.toString(latitude));
                lonTV.setText(Double.toString(longitude));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MapsActivity.this, "Failed to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        double latitude= Double.parseDouble((String) latTV.getText());
        double longitude= Double.parseDouble((String) lonTV.getText());
        LatLng location = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(location).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
    }
}