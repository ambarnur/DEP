package com.net2software.dep;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.net2software.dep.app.AppController;
import com.net2software.dep.model.Lapangan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailBooking extends AppCompatActivity {

    TextView nomor,namalengkap,tanggal, jam, durasi, lapangan,harga,total,tempat;

    ProgressDialog pd;
    public static final String url = Server.URL_HARGA;
    private static final String TAG = FutsalActivity.class.getSimpleName();

    String tag_json_obj = "json_obj_req";

    Context context;


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
            String id_jadwal = "1";

        LoadJson(id_jadwal);

        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }


    private void LoadJson(final String id) {
        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Memuat...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean status = jObj.getBoolean("status");
                    String code = jObj.getString("code");
                    String message = jObj.getString("message");

                    if (status) {
                        JSONArray datas = jObj.getJSONArray("data");
                        if (datas != null) {
                            for (int index = 0; index < datas.length(); index++) {
                                JSONObject jsonObject = datas.getJSONObject(index);
                                String id_harga = jsonObject.getString("id");
                                String data_harga = jsonObject.getString("harga");
                                String durasidata = durasi.getText().toString();
//                                if(durasidata == "2 jam"){
//                                harga.setText(""+data_harga);
//                                    int jumlah = data_harga * 2;
//                                total.setText(""+jumlah);
//                                }else{
                                    harga.setText(""+data_harga);
                                    total.setText(""+data_harga);

//                                }

                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "Data Kosong ...", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, " Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

                hideDialog();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("jadwal_id", id);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }


    private void showDialog() {
        if (!pd.isShowing())
            pd.show();
    }

    private void hideDialog() {
        if (pd.isShowing())
            pd.dismiss();
    }



    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
        return true;
    }


}
