package com.net2software.dep.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.net2software.dep.R;
import com.net2software.dep.model.Jadwal;
import com.net2software.dep.model.Lapangan;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.LViewHolder> {


    private ArrayList<Lapangan> datalapangan;

    public RecyclerViewAdapter(ArrayList<Lapangan> datalapangan) {
        this.datalapangan = datalapangan;
    }

    @Override
    public LViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_lapangan, parent, false);
        return new LViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LViewHolder holder, int position) {
        holder.nama.setText(datalapangan.get(position).getNama());
        holder.keterangan.setText(datalapangan.get(position).getKeterangan());
    }

    @Override
    public int getItemCount() {
        return (datalapangan != null) ? datalapangan.size() : 0;
    }

    public class LViewHolder extends RecyclerView.ViewHolder{
        private TextView nama,keterangan;


        public LViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.lapangan);
            keterangan = (TextView) itemView.findViewById(R.id.tv_subtitle);
        }
    }
}