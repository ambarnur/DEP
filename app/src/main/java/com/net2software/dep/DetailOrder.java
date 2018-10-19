package com.net2software.dep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DetailOrder extends AppCompatActivity {
    TextView namaTxt,jamTxt, statusTxt;
    TextView nohpTxt, tempatTxt, lapanganTxt, tanggalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order);

        namaTxt = (TextView) findViewById(R.id.tv_nama);
        jamTxt = (TextView) findViewById(R.id.tv_jam_order);
        statusTxt = (TextView) findViewById(R.id.tv_status);
        nohpTxt = (TextView) findViewById(R.id.tv_no_hp);
        tempatTxt = (TextView) findViewById(R.id.tv_tempat);
        lapanganTxt = (TextView) findViewById(R.id.tv_lapangan);
        tanggalTxt = (TextView) findViewById(R.id.tv_tanggal);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            namaTxt.setText(" "+bundle.getString("nama"));
            nohpTxt.setText(" "+bundle.getString("nohp"));
            tempatTxt.setText(" "+bundle.getString("tempat"));
            lapanganTxt.setText(" "+bundle.getString("lapangan"));
            tanggalTxt.setText(" "+bundle.getString("tglmain"));
            jamTxt.setText(" "+bundle.getString("jam"));
            statusTxt.setText(" "+bundle.getString("status"));
        }
    }

}
