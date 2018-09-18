package com.example.archek.currentcoursescurrencies;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder> {

    private ArrayList<Valute> valutes;


    RecAdapter(ArrayList<Valute> valutes) {
        this.valutes = valutes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Valute valute = valutes.get(position);

        holder.tvName.setText(valute.getName());
        holder.tvValue.setText(valute.getValue());
    }

    @Override
    public int getItemCount() {
        return valutes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvValue;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvValue = itemView.findViewById(R.id.tvValue);
        }
    }
}
