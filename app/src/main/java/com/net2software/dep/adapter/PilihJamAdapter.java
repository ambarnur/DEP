package com.net2software.dep.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.net2software.dep.R;
import com.net2software.dep.model.Jadwal;

import java.util.ArrayList;

public class PilihJamAdapter extends RecyclerView.Adapter<PilihJamAdapter.MahasiswaViewHolder> {


    private ArrayList<Jadwal> dataList;

    public PilihJamAdapter(ArrayList<Jadwal> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MahasiswaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_pilih_jam, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MahasiswaViewHolder holder, int position) {
        holder.jam.setText(dataList.get(position).getJam());
        holder.jam.setBackgroundColor(Color.parseColor("#018786"));
        holder.jam.setTextColor(Color.parseColor("#FFFFFF"));

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder{
        private TextView jam;


        public MahasiswaViewHolder(View itemView) {
            super(itemView);
          jam = (TextView) itemView.findViewById(R.id.textview1);

        }
    }
}