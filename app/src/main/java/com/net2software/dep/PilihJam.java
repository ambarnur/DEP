package com.net2software.dep;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class PilihJam extends AppCompatActivity {
    RecyclerView recyclerView;

    Context context;

    RecyclerView.Adapter recyclerView_Adapter;

    RecyclerView.LayoutManager recyclerViewLayoutManager;

    String[] numbers = {
            "09:00",
            "10:00",
            "11:00",
            "12:00",
            "13:00",
            "14:00",
            "15:00",
            "16:00",
            "17:00",
            "18:00",
            "19:00",
            "20:00",
            "21:00",
            "22:00",

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_jam);
        context = getApplicationContext();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);

        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView_Adapter = new PilihJamAdapter(context, numbers);
        recyclerView.setAdapter(recyclerView_Adapter);

    }
}
