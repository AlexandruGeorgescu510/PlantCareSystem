package com.example.pcs.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pcs.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReferenceT, databaseReferenceR, databaseReferenceL, databasereferenceH, databaseReferenceP, databaseReferenceI, databaseReferenceN, databaseReferenceB;
    Spinner spinnerInterval;
    Switch lightSwitch;
    private TextView tempTV, rainTV, lightTV, humidityTV, pressureTV, nameTV;

    private Button BtnMaps, BtnTips;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        int id = intent.getIntExtra("INT",0);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReferenceT = firebaseDatabase.getReference("/device"+id+"/temperature");
        databaseReferenceR = firebaseDatabase.getReference("/device"+id+"/rain");
        databaseReferenceL = firebaseDatabase.getReference("/device"+id+"/light");
        databasereferenceH = firebaseDatabase.getReference("/device"+id+"/humidity");
        databaseReferenceP = firebaseDatabase.getReference("/device"+id+"/pressure");
        databaseReferenceI = firebaseDatabase.getReference("/device"+id+"/interval");
        databaseReferenceN = firebaseDatabase.getReference("/device"+id+"/name");
        databaseReferenceB = firebaseDatabase.getReference("/device"+id+"/boost");

        tempTV = findViewById(R.id.temp_textview);
        rainTV = findViewById(R.id.rain_textview);
        lightTV = findViewById(R.id.light_textview);
        humidityTV = findViewById(R.id.humidity_textview);
        pressureTV = findViewById(R.id.pressure_textview);
        BtnMaps = findViewById(R.id.maps_Btn);
        spinnerInterval=findViewById(R.id.spinner);
        nameTV = findViewById(R.id.nameTV);
        BtnTips = findViewById(R.id.tips_Btn);
        lightSwitch = findViewById(R.id.light_switch);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.interval, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerInterval.setAdapter(adapter);
        spinnerInterval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = spinnerInterval.getSelectedItem().toString();
                String sub = selection.substring(0,2);
                int interval = 0;

                if(sub.equals("8 "))
                    interval = 8;
                if(sub.equals("12"))
                    interval = 12;
                if(sub.equals("24"))
                    interval = 24;
                databaseReferenceI.setValue(interval);

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        databaseReferenceB.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int boost = dataSnapshot.getValue(Integer.class);
                if(boost == 0)
                    lightSwitch.setChecked(false);
                else lightSwitch.setChecked(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failure to get the data.", Toast.LENGTH_SHORT).show();
            }
        });

        lightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int boost=0;
                if(isChecked == true) {
                    Toast.makeText(getBaseContext(), "Light boost on!!", Toast.LENGTH_SHORT).show();
                    boost = 1;
                    databaseReferenceB.setValue(boost);
                }
                else {
                    Toast.makeText(getBaseContext(), "Light boost off!!", Toast.LENGTH_SHORT).show();
                    databaseReferenceB.setValue(boost);
                }
            }
        });

        BtnMaps.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                context = getApplicationContext();
                Intent intent = new Intent(context, MapsActivity.class);
                intent.putExtra("INT",id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        BtnTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, TipsActivity.class);
                startActivity(intent2);
            }
        });


        getdata();

    }

    private void getdata() {

        databaseReferenceN.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                nameTV.setText(text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failure to get the data.", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReferenceT.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                tempTV.setText(value + " \u2103");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failure to get the data.", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReferenceR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer val = dataSnapshot.getValue(Integer.class);
                if(val == 0) {
                    rainTV.setText("It's not raining");
                }
                else rainTV.setText("It's raining");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failure to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReferenceL.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                lightTV.setText(value + " lx");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failure to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        databasereferenceH.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                humidityTV.setText(value + " %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failure to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReferenceP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Double value = dataSnapshot.getValue(Double.class);
                pressureTV.setText(value + " hPa");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failure to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}