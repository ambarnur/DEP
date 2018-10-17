package com.net2software.dep.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.net2software.dep.R;
import com.net2software.dep.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private List<Order> orderList;

    public OrderAdapter(){
        orderList = new ArrayList<>();
    }

    private void add(Order item) {
        orderList.add(item);
        notifyItemInserted(orderList.size() - 1);
    }

    public void addAll(List orderList){
        for (Object order : orderList) {
            add(order);
        }
    }

    private void add(Object order) {
    }

    public void remove(Order item) {
        int position = orderList.indexOf(item);
        if (position > -1){
            orderList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public Order getItem(int position){
        return orderList.get(position);
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position){
        final Order order = orderList.get(position);
        holder.orderNama.setText(order.getNama());
        holder.orderNoHp.setText(order.getNo_hp());
        holder.orderLapangan.setText(order.getLapangan());
        holder.orderTempat.setText(order.getTempat());
        holder.orderTanggal.setText(order.getTanggal());
        holder.orderJam.setText(order.getJam());
        holder.orderStatus.setText(order.getStatus());
    }

    @Override
    public int getItemCount(){
        return orderList.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderNama, orderNoHp, orderLapangan;
        TextView orderTempat, orderTanggal, orderJam, orderStatus;

        public OrderViewHolder(View itemView){
            super(itemView);
            orderNama = (TextView) itemView.findViewById(R.id.nama);
            orderNoHp = (TextView) itemView.findViewById(R.id.no_hp);
            orderLapangan = (TextView) itemView.findViewById(R.id.rv_lapangan);
            orderTempat = (TextView) itemView.findViewById(R.id.rv_tempat);
            orderTanggal = (TextView) itemView.findViewById(R.id.rv_tanggal);
            orderJam = (TextView) itemView.findViewById(R.id.rv_jam);
            orderStatus = (TextView) itemView.findViewById(R.id.rv_status);
        }
    }

}
