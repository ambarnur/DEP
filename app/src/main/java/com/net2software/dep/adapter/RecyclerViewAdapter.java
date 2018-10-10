package com.net2software.dep.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.net2software.dep.R;
import com.net2software.dep.Server;
import com.net2software.dep.model.Jadwal;
import com.net2software.dep.model.Lapangan;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.LViewHolder> {


    private ArrayList<Lapangan> datalapangan;
    private Context context;
    public RecyclerViewAdapter(Context context, ArrayList<Lapangan> datalapangan) {
        this.datalapangan = datalapangan;
        this.context = context;
    }

    @Override
    public LViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_lapangan, parent, false);
        return new LViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LViewHolder holder, int position) {
        holder.nama.setText(datalapangan.get(position).getNama());
        holder.keterangan.setText(datalapangan.get(position).getKeterangan());
        Picasso.with(context).load(Server.URL_IMAGE + datalapangan.get(position).getGambar())
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(holder.gambar , new Callback(){
                    @Override
                    public void onSuccess(){

                    }
                    @Override
                    public void onError(){
                        holder.gambar.setImageDrawable(context.getResources().getDrawable(R.drawable.indicator_circle));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return (datalapangan != null) ? datalapangan.size() : 0;
    }

    public class LViewHolder extends RecyclerView.ViewHolder{
        private TextView nama,keterangan;
        private ImageView gambar ;


        public LViewHolder(View itemView) {
            super(itemView);
            nama = (TextView) itemView.findViewById(R.id.lapangan);
            keterangan = (TextView) itemView.findViewById(R.id.tv_subtitle);
            gambar = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}