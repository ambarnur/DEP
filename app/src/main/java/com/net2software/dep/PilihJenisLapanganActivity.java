package com.net2software.dep;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;

import java.util.ArrayList;

public class PilihJenisLapanganActivity extends AppCompatActivity {

    private static final String TAG = "PilihJenisLapangan";
   // RecyclerView recyclerView;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_jenis_lapangan);
        Log.d(TAG, "onCreate: started.");

        initImageBitmaps();


        final Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapse_toolbar);
        collapsingToolbarLayout.setTitle("NCI FUTSAL");



        Context context = this;
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context,R.color.colorBackground));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#FFFFFF"));
        collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#FFFFFF"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://www.kontraktorlantaifutsal.com/wp-content/uploads/2018/03/biaya-pembuatan-lapangan-futsal-1.jpg");
        mNames.add("Lapangan A");

        mImageUrls.add("https://www.jaringfutsalpengaman.com/wp-content/uploads/2018/06/Jasa-Pembuatan-Lapangan-Futsal.jpg");
        mNames.add("Lapangan B");

        mImageUrls.add("https://i1.wp.com/carainvestasibisnis.com/wp-content/uploads/2018/01/Bisnis-Sewa-Lapangan-Futsal.jpeg?resize=917%2C584&ssl=1");
        mNames.add("Lapangan C");

        mImageUrls.add("https://konsultanarsitekjogja.files.wordpress.com/2015/09/lapangan-futsal.jpg");
        mNames.add("Lapangan D");

        mImageUrls.add("https://cdn-images-1.medium.com/max/640/1*sm1F-R9fxdvxqQbRjpSDhA.jpeg");
        mNames.add("Lapangan E");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.rv_main);
        //ininya engga kepanggil
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        //disininya null
        //trus
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
