package com.net2software.dep;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.net2software.dep.adapter.OrderAdapter;
import com.net2software.dep.model.Order;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab2Fragment extends Fragment {


    int position;
    private TextView textView;

    private RecyclerView orderList;
    private LinearLayoutManager linearLayoutManager;
    private OrderAdapter orderAdapter;
    protected Context context;

    public static Tab1Fragment newInstance(){
        return new Tab1Fragment();
    }

    public Tab2Fragment() {
        // Required empty public constructor
    }


    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        Tab1Fragment tabFragment = new Tab1Fragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        orderList = (RecyclerView) rootView.findViewById(R.id.rv_order_tab2);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(context);
        orderAdapter = new OrderAdapter();
        orderList.setLayoutManager(linearLayoutManager);
        orderList.setAdapter(orderAdapter);
        loadData();


    }

    private void loadData(){
        List<Order> orderList1 = new ArrayList<>();
        Order order;

        String nama[] = {"aa", "bb", "cc", "dd"};
        String nohp = "000000";
        String lapangan = "A";
        String tempat = "NCI";
        String tanggal = "08/10/2018";
        String jam = "08:00";
        String status = "Selesai";

        for (int i=0; i < nama.length; i++){
            order = new Order();
            order.setId(i+1);
            order.setNama(nama[i]);
            order.setNo_hp(nohp);
            order.setLapangan(lapangan);
            order.setTempat(tempat);
            order.setTanggal(tanggal);
            order.setJam(jam);
            order.setStatus(status);
        }
        orderAdapter.addAll(orderList1);
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
    }

}
