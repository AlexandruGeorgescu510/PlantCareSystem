package com.example.pcs.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcs.R;
import com.example.pcs.adapters.HintAdapter;
import com.example.pcs.models.Hint;

import java.util.Vector;

public class TipsActivity extends AppCompatActivity {

    private RecyclerView hintView;
    private HintAdapter hintAdapter;
    private Vector<Hint> hints = new Vector<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        hintView = findViewById(R.id.tipsRecyclerView);

        initialiseHints();

        hintAdapter = new HintAdapter(hints);
        hintView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        hintView.setAdapter(hintAdapter);

    }

    private void initialiseHints(){
        Hint hint;
        for(int i = 1; i < 5; i++) {
            switch(i) {
                case 1:
                    hint = new Hint("Plant care 101", getApplicationContext().getString(R.string.hint1));
                    hints.add(hint);
                    break;
                case 2:
                    hint = new Hint("Plant data basics", getApplicationContext().getString(R.string.hint2));
                    hints.add(hint);
                    break;
                case 3:
                    hint = new Hint("Watering interval", getApplicationContext().getString(R.string.hint3));
                    hints.add(hint);
                    break;
                case 4:
                    hint = new Hint("Light boost", getApplicationContext().getString(R.string.hint4));
                    hints.add(hint);
                    break;
            }

        }
    }
}