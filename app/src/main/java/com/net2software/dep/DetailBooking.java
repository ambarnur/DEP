package com.net2software.dep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailBooking extends AppCompatActivity {

    TextView namalengkap;
    TextView nomor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_booking);

        Bundle b = getIntent().getExtras();
        //membuat obyek dari widget textview
        namalengkap = (TextView) findViewById(R.id.value_nama);
        nomor = (TextView) findViewById(R.id.no_hp_value);
        //menset nilai dari widget textview
        namalengkap.setText(b.getCharSequence("nama"));
        nomor.setText(b.getCharSequence("nohp"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
        return true;
    }
}
