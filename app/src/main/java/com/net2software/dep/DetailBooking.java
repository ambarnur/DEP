package com.net2software.dep;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailBooking extends AppCompatActivity {

    TextView nomor,namalengkap,tanggal, jam, durasi, lapangan,harga,total,tempat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_booking);

        //membuat obyek dari widget textview
        namalengkap = (TextView) findViewById(R.id.value_nama);
        nomor = (TextView) findViewById(R.id.no_hp_value);
        tanggal =findViewById(R.id.value_tanggal);
        jam = findViewById(R.id.value_jam_masuk);
        tempat = findViewById(R.id.value_tempat);
        lapangan = findViewById(R.id.value_lapangan);
        harga = findViewById(R.id.value_harga);
        durasi = findViewById(R.id.value_durasi);
        total = findViewById(R.id.value_total);


        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("lapangan", null);
       String t = prefs.getString("tanggal", null);
        String temp = prefs.getString("tempat", null);

        if (restoredText   != null) {
            String lpg = prefs.getString("lapangan", "No name defined");//"No name defined" is the default value.
            lapangan.setText(""+lpg);
            if(t !=null){
            String tgl = prefs.getString("tanggal", "No name defined");//"No name defined" is the default value.
                tanggal.setText(""+tgl);
            }
            if(temp !=null){
            String tmp = prefs.getString("tempat", "No name defined");//"No name defined" is the default value.
                tempat.setText(""+tmp);

            }


        }

        //menset nilai dari widget textview
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            namalengkap.setText(" "+bundle.getString("nama"));
            nomor.setText(" "+bundle.getString("nohp"));
            jam.setText(" "+bundle.getString("jam"));
            durasi.setText(" "+bundle.getString("durasi"));



        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
        return true;
    }
}
