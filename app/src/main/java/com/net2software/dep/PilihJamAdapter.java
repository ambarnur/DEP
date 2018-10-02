package com.net2software.dep;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PilihJamAdapter extends RecyclerView.Adapter<PilihJamAdapter.ViewHolder> {

    String[] values;
    Context context1;

    public PilihJamAdapter(Context context2,String[] values2){
    values = values2;
    context1 = context2;
}

public static class ViewHolder extends RecyclerView.ViewHolder{

    public TextView textView;

        public ViewHolder(View v){
            super(v);
            textView = (TextView) v.findViewById(R.id.textview1);

        }
    }

    @Override
    public PilihJamAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view1 = LayoutInflater.from(context1).inflate(R.layout.item_pilih_jam,parent,false);
        ViewHolder viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position){

        Vholder.textView.setText(values[position]);
        Vholder.textView.setBackgroundColor(Color.parseColor("#018786"));
        Vholder.textView.setTextColor(Color.parseColor("#FFFFFF"));

    }

    @Override
    public int getItemCount(){

        return values.length;
    }
}
