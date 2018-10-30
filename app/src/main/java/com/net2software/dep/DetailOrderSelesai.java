package com.net2software.dep;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailOrderSelesai extends AppCompatActivity {
    TextView namaTxt,jamTxt, statusTxt;
    TextView nohpTxt, tempatTxt, lapanganTxt, tanggalTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_order_selesai);

        namaTxt = (TextView) findViewById(R.id.value_nama_selesai);
        jamTxt = (TextView) findViewById(R.id.value_jam_masuk_selesai);
        statusTxt = (TextView) findViewById(R.id.value_status_selesai);
        nohpTxt = (TextView) findViewById(R.id.no_hp_value_selesai);
        tempatTxt = (TextView) findViewById(R.id.value_tempat_selesai);
        lapanganTxt = (TextView) findViewById(R.id.value_lapangan_selesai);
        tanggalTxt = (TextView) findViewById(R.id.value_tanggal_selesai);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            namaTxt.setText(": "+bundle.getString("nama"));
            nohpTxt.setText(": "+bundle.getString("nohp"));
            tempatTxt.setText(": "+bundle.getString("tempat"));
            lapanganTxt.setText(": "+bundle.getString("lapangan"));
            tanggalTxt.setText(": "+bundle.getString("tglmain"));
            jamTxt.setText(": "+bundle.getString("jam"));
            statusTxt.setText(": Selesai");
        }
    }
}
