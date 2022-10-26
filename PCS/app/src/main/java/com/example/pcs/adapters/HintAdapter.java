package com.example.pcs.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcs.R;
import com.example.pcs.models.Hint;

import java.util.Vector;

public class HintAdapter extends RecyclerView.Adapter<HintAdapter.HintViewHolder> {

    private Vector<Hint> hints;

    public HintAdapter(Vector<Hint> hints) {
        this.hints = hints;
    }

    @NonNull
    @Override
    public HintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hint_cell, parent, false);
        return new HintAdapter.HintViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HintViewHolder holder, int position) {
        holder.bind(hints.get(position));
    }

    @Override
    public int getItemCount() {
        return hints.size();
    }

    public class HintViewHolder extends RecyclerView.ViewHolder {

        private TextView hintTittle;
        private TextView hintText;
        private View view;
        private boolean isShowing = false;

        public HintViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            hintTittle = view.findViewById(R.id.hintTitle);
            hintText = view.findViewById(R.id.hintText);
        }

        public void bind(Hint hint){
            hintTittle.setText(hint.getHintTittle());
            hintText.setText(hint.getHintText());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isShowing) {
                        hintText.setVisibility(View.GONE);
                        isShowing = !isShowing;
                    }
                    else{
                        hintText.setVisibility(View.VISIBLE);
                        isShowing = !isShowing;
                    }
                }
            });
        }

    }
}
