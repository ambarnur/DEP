package com.net2software.dep.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.net2software.dep.R;
import com.net2software.dep.model.Data;

import java.util.List;

public class TempatAdapter extends ArrayAdapter<Data> {

    LayoutInflater flater;

    public TempatAdapter(Activity contex, int resouceId, int textviewId, List<Data> list){

        super(contex,resouceId,textviewId,list);
        flater = contex.getLayoutInflater();
    }

    @Override
    public View getView(int position, View contvertView, ViewGroup parent){
        Data data = getItem(position);
        View rowview = flater.inflate(R.layout.item_tempat,null,true);
        TextView textTitle = (TextView) rowview.findViewById(R.id.tempat);
        textTitle.setText(data.getNama());
        return rowview;
    }

}
