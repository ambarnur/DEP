package com.net2software.dep;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.net2software.dep.model.Jadwal;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PilihJam extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PilihJamAdapter adapter;
    private ArrayList<Jadwal> jamArrayList;


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_jam);
        recyclerView = findViewById(R.id.recycler_view1);
        addData();
        RecyclerView.LayoutManager recyclerViewLayoutManager;

        adapter = new PilihJamAdapter(jamArrayList);
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
        @Override public void onItemClick(View view, int position) {

            Jadwal jadwal = jamArrayList.get(position);

            Intent intent = new Intent();
            intent.putExtra("editTextValue", ""+jadwal.getJam());
            setResult(RESULT_OK, intent);
            finish();

        }

        @Override public void onLongItemClick(View view, int position) {
            // do whatever
        }
    })
            );







        //Change 2 to your choice because here 2 is the number of Grid layout Columns in each row.


    }

    void addData() {
        jamArrayList = new ArrayList<>();
        jamArrayList.add(new Jadwal("1", "09:00"));
        jamArrayList.add(new Jadwal("2", "10:00"));
        jamArrayList.add(new Jadwal("3", "11:00"));
        jamArrayList.add(new Jadwal("4", "12:00"));
        jamArrayList.add(new Jadwal("5", "13:00"));
        jamArrayList.add(new Jadwal("6", "14:00"));
        jamArrayList.add(new Jadwal("7", "15:00"));
        jamArrayList.add(new Jadwal("8", "16:00"));
        jamArrayList.add(new Jadwal("9", "17:00"));
        jamArrayList.add(new Jadwal("10", "18:00"));
        jamArrayList.add(new Jadwal("11", "19:00"));
        jamArrayList.add(new Jadwal("12", "20:00"));
        jamArrayList.add(new Jadwal("13", "21:00"));
        jamArrayList.add(new Jadwal("14", "22:00"));

    }
}
