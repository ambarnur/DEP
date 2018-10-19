package com.net2software.dep;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.net2software.dep.adapter.PilihJamAdapter;
import com.net2software.dep.app.AppController;
import com.net2software.dep.model.Jadwal;
import com.net2software.dep.model.Lapangan;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PilihJam extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PilihJamAdapter adapter;
    private ArrayList<Jadwal> JamArraylist = new ArrayList<>();
    ProgressDialog pd;
    public static final String url = Server.URL_JADWAL;
    private static final String TAG = FutsalActivity.class.getSimpleName();

    String tag_json_obj = "json_obj_req";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_jam);
        recyclerView = findViewById(R.id.recycler_view1);
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String t = prefs.getString("tanggal", null);
        String id_lapangan = prefs.getString("id_lapangan", null);

        if (t   != null) {
            String tanggal = prefs.getString("tanggal", "No name defined");//"No name defined" is the default value.

            if (id_lapangan   != null) {
                String lapangan_id = prefs.getString("id_lapangan", "No name defined");//"No name defined" is the default value.

                LoadJson(lapangan_id, tanggal);
            }
    }
        RecyclerView.LayoutManager recyclerViewLayoutManager;

        adapter = new PilihJamAdapter(JamArraylist);
        recyclerViewLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(context, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
        @Override public void onItemClick(View view, int position) {

            Jadwal jadwal = JamArraylist.get(position);

            Intent intent = new Intent();
            intent.putExtra("editTextValue", ""+jadwal.getJam());
            intent.putExtra("id_jadwal",""+jadwal.getId());
            intent.putExtra("harga",""+jadwal.getHarga());
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
    private void LoadJson(final String id, final String tanggal) {
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
                                Boolean status_jam = jsonObject.getBoolean("status");
                                if(status_jam){
                                    String id_jadwal = jsonObject.getString("id");
                                    String jam = jsonObject.getString("jam");
                                    String harga = jsonObject.getString("harga");
                                    Jadwal jadwal = new Jadwal();
                                    jadwal.setId(id_jadwal);
                                    jadwal.setJam(jam);
                                    jadwal.setHarga(harga);
                                    JamArraylist.add(jadwal);
                                }

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
                params.put("lapangan_id", id);
                params.put("tanggal", tanggal);
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


}
