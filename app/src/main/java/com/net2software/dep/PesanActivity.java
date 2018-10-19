package com.net2software.dep;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.net2software.dep.app.AppController;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PesanActivity extends AppCompatActivity {

    TextView textView,jam,nama_lapangan;
    ImageView gambar_lapangan;
    Button button_pesan;
    Spinner spinner;
    EditText nama;
    EditText nohp;
    private String url = Server.URL_ORDER;
    ProgressDialog pDialog;
    private static final String TAG = PesanActivity.class.getSimpleName();

    private static final String TAG_CODE = "code";
    private static final String TAG_MESSAGE = "message";
    private String id_user;
    private String id_jadwal;
    private String harga;
    String tag_json_obj = "json_obj_req";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        Log.d(TAG, "onCreate: started.");
        spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.durasi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        jam = findViewById(R.id.tv_jam);
        pDialog = new ProgressDialog(PesanActivity.this);
        nama_lapangan = findViewById(R.id.image_description);
        nama = (EditText) findViewById(R.id.ev_namalengkap);
        nohp = (EditText) findViewById(R.id.ev_nohp);
        button_pesan = (Button) findViewById(R.id.btn_pesan);
        gambar_lapangan = findViewById(R.id.image_pesan);
        textView = (TextView) findViewById(R.id.tv_pilih_jam);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PesanActivity.this, PilihJam.class);
                startActivityForResult(i, 1);
            }
        });
        String MY_USER = "id_user";
        SharedPreferences prefs = getSharedPreferences(MY_USER, MODE_PRIVATE);
        String user_id = prefs.getString("user_id", null);

        if (user_id   != null) {
            String lpg = prefs.getString("user_id", "No name defined");//"No name defined" is the default value.
            id_user = lpg;
        }


            button_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String nama_pemesan = nama.getText().toString();
               String no_hp = nohp.getText().toString();
               Date c = Calendar.getInstance().getTime();
               SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
               String tanggal = format.format(c);
                Time today = new Time(Time.getCurrentTimezone());
                today.setToNow();
                String jam_pesan = today.format("%k:%M:%S");


                LoadJsonPesan(id_jadwal,nama_pemesan,no_hp,tanggal,jam_pesan,id_user);
                Intent intent = new Intent(PesanActivity.this, DetailBooking.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", nama.getText().toString());
                intent.putExtra("nohp", nohp.getText().toString());
                intent.putExtra("jam", jam.getText().toString());
                intent.putExtra("harga", harga );
                intent.putExtra("durasi", spinner.getSelectedItem().toString());
                intent.putExtra("id_jadwal",id_jadwal);
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getIncomingIntent();


    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("nama")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String imageName = getIntent().getStringExtra("nama");
            String image = getIntent().getStringExtra("gambar");
            nama_lapangan.setText(""+imageName);
            Picasso.with(getApplicationContext()).load(Server.URL_IMAGE + image)
                    .networkPolicy(NetworkPolicy.NO_CACHE)
                    .memoryPolicy(MemoryPolicy.NO_CACHE)
                    .into(gambar_lapangan, new Callback(){
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {

                        }
                    });
        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("lapangan", imageName);
        editor.apply();

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("editTextValue");
                jam.setText(strEditText);
                id_jadwal = data.getStringExtra("id_jadwal");
                harga = data.getStringExtra("harga");
            }
        }
    }

    private void LoadJsonPesan ( final String jadwal_id, final String nama,
                                 final String nohp, final String tanggal, final String jam_pesan,final String user_id){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Pesan ...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Response: " + response.toString());
//                pDialog.cancel();

                try {
                    JSONObject jObj = new JSONObject(response);
                    int code = jObj.getInt(TAG_CODE);

                    // Check for error node in json
                    if (code == 200) {

                        Log.e("Successfully !", jObj.toString());

                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(),
                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
//                pDialog.cancel();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("jadwal_id", jadwal_id);
                params.put("nama", nama);
                params.put("no_hp", nohp);
                params.put("tanggal",tanggal);
                params.put("jam_pesan",jam_pesan);
                params.put("user_id",user_id);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }



    @Override
    public boolean onSupportNavigateUp(){

        finish();
        return true;
    }
}
