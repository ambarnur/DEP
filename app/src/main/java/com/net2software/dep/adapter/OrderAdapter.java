package com.net2software.dep.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.net2software.dep.R;
import com.net2software.dep.model.Jadwal;
import com.net2software.dep.model.Lapangan;
import com.net2software.dep.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private ArrayList<Order> orderList;
    private Context context;
    public OrderAdapter(Context context, ArrayList<Order> orderList) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position){
//        final Order order = orderList.get(position);
        holder.orderLapangan.setText(orderList.get(position).getLapangan());
        holder.orderTempat.setText(orderList.get(position).getTempat());
        holder.orderTanggal.setText(orderList.get(position).getTanggal());
        holder.orderJam.setText(orderList.get(position).getJam());
        holder.tglmain.setText(orderList.get(position).getTglmain());
    }

    @Override
    public int getItemCount() {
        return (orderList != null) ? orderList.size() : 0;
    }


    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView  orderLapangan, tglmain;
        TextView orderTempat, orderTanggal, orderJam;

        public OrderViewHolder(View itemView){
            super(itemView);
            orderLapangan = (TextView) itemView.findViewById(R.id.rv_lapangan);
            orderTempat = (TextView) itemView.findViewById(R.id.rv_tempat);
            orderTanggal = (TextView) itemView.findViewById(R.id.rv_tanggal);
            orderJam = (TextView) itemView.findViewById(R.id.jam);
            tglmain = itemView.findViewById(R.id.tglmain);
        }
    }

}
