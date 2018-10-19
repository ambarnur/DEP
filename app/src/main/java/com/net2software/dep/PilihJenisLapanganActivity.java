package com.net2software.dep;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.net2software.dep.adapter.PilihJamAdapter;
import com.net2software.dep.adapter.RecyclerViewAdapter;
import com.net2software.dep.app.AppController;
import com.net2software.dep.model.Data;
import com.net2software.dep.model.Jadwal;
import com.net2software.dep.model.Lapangan;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.net2software.dep.Server.URL;

public class PilihJenisLapanganActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<Lapangan> LapangaArraylist = new ArrayList<>();
    ProgressDialog pd;
    public static final String url = Server.URL_LAPANGAN;
    private static final String TAG = FutsalActivity.class.getSimpleName();

    String tag_json_obj = "json_obj_req";

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_jenis_lapangan);
        String MY_PREFS = "id";
        SharedPreferences pref = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        String id = pref.getString("id", null);
        String image = pref.getString("gambar", null);
        if (id !=null){

        final String tempat_id = pref.getString("id", "No name defined");//"No name defined" is the default value.

            LoadJson(tempat_id);
        }


        recyclerView = findViewById(R.id.rv_main);
        adapter = new RecyclerViewAdapter(PilihJenisLapanganActivity.this,LapangaArraylist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Lapangan lapangan = LapangaArraylist.get(position);
                        String MY_PREFS_NAME = "MyPrefsFile";
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("id_lapangan", ""+lapangan.getId());
                        editor.apply();


                        Intent intent = new Intent(PilihJenisLapanganActivity.this,PesanActivity.class);
                        intent.putExtra("nama", ""+lapangan.getNama());
                        intent.putExtra("id", ""+lapangan.getId());
                        intent.putExtra("gambar",""+lapangan.getGambar());
                        startActivity(intent);
                        finish();

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );




        final Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        ImageView gambar_tempat = findViewById(R.id.bgheader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        String temp = prefs.getString("tempat", null);

        if(temp !=null) {
            String tmp = prefs.getString("tempat", "No name defined");//"No name defined" is the default value.


            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
            collapsingToolbarLayout.setTitle(""+tmp);


            Context context = this;
            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(context, R.color.colorBackground));
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.parseColor("#FFFFFF"));
            collapsingToolbarLayout.setExpandedTitleColor(Color.parseColor("#FFFFFF"));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


            if(image !=null){
                final String gambar = pref.getString("gambar", "No name defined");//"No name defined" is the default value.

            Picasso.with(getApplicationContext()).load(Server.URL_IMAGE + gambar)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(gambar_tempat, new Callback(){
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
            }
        }


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
                                String id_lapangan = jsonObject.getString("id");
                                String nama_lapangan = jsonObject.getString("nama");
                                String keterangan_lapangan = jsonObject.getString("ket");
                                String gambar_lapangan = jsonObject.getString("gambar");
                                Lapangan Lp = new Lapangan();
                                Lp.setId(id_lapangan);
                                Lp.setNama(nama_lapangan);
                                Lp.setKeterangan(keterangan_lapangan);
                                Lp.setGambar(gambar_lapangan);
                                LapangaArraylist.add(Lp);
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
                adapter.notifyDataSetChanged();
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
                params.put("tempat_id", id);

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
       finish();
        return true;
    }
}
