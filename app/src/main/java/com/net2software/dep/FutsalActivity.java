package com.net2software.dep;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.net2software.dep.adapter.TempatAdapter;
import com.net2software.dep.app.AppController;
import com.net2software.dep.model.Data;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class FutsalActivity extends AppCompatActivity {

    SearchableSpinner spinner;
    TempatAdapter adapter;
    ProgressDialog pDialog;
    List<Data> datas = new ArrayList<Data>();
    public static final String url = Server.URL_TEMPAT;
    private static final String TAG = FutsalActivity.class.getSimpleName();
    public static final String TAG_ID = "id";
    public static final String TAG_NAMA = "nama";

    private DatePickerDialog datePickerDialog;
    private EditText edit_tanggal;
    private Button tanggal;
    private Button btncari;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal);

        pDialog = new ProgressDialog(FutsalActivity.this);
        loadJson();
        spinner = (SearchableSpinner) findViewById(R.id.spinner);
        adapter = new TempatAdapter(FutsalActivity.this,R.layout.item_tempat,R.id.tempat,datas);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                String MY_PREFS = "id";
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
                editor.putString("id", ""+datas.get(position).getId());
                editor.apply();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        edit_tanggal = (EditText) findViewById(R.id.edit_tanggal);
        tanggal = (Button) findViewById(R.id.btn_tanggal);
        btncari = (Button) findViewById(R.id.cari);
        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FutsalActivity.this,PilihJenisLapanganActivity.class);
                startActivity(intent);
                finish();
            }
        });

        edit_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                edit_tanggal.setText(""+dateFormat.format(newDate.getTime()));

                String MY_PREFS_NAME = "MyPrefsFile";
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("tanggal", ""+dateFormat.format(newDate.getTime()));
                editor.putString("tempat", ""+spinner.getSelectedItem().toString());

                editor.apply();





            }
            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    private void loadJson() {
        pDialog.setMessage("Memuat");
        pDialog.setCancelable(false);
        pDialog.show();

        JsonArrayRequest reqBerita = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                pDialog.cancel();
                Log.d("volley", "response : " +
                        response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject tempat = response.getJSONObject(i);
                        Data mp = new Data();
                        mp.setId(tempat.getInt("id"));
                        mp.setNama(tempat.getString("nama"));
                        datas.add(mp);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.cancel();
                Log.d("volley", "error : " + error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(reqBerita);
    }



    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
        return true;
    }

}
