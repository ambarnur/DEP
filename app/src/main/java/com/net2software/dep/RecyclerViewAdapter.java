package com.net2software.dep;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mTvLapangan = new ArrayList<>();
    private ArrayList<String> mImage = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> tvLapangan, ArrayList<String> image){
        mTvLapangan = tvLapangan;
        mImage = image;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_rv_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");


        Glide.with(mContext)
                .asBitmap()
                .load(mImage.get(position))
                .into(holder.image);
        holder.tvLapangan.setText(mTvLapangan.get(position));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + mTvLapangan.get(position));

                Intent intent = new Intent(mContext, PesanActivity.class);
                intent.putExtra("image_url", mImage.get(position));
                intent.putExtra("image_name", mTvLapangan.get(position));
                mContext.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return mTvLapangan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tvLapangan;
        RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.icon_lapangan);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            tvLapangan = itemView.findViewById(R.id.tv_title);
        }
    }
}