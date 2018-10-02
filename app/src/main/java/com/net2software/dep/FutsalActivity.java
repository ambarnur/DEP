package com.net2software.dep;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class FutsalActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private EditText edit_tanggal;
    private Button tanggal;
    private Button btncari;
    private SearchableSpinner spinner;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futsal);


        spinner = findViewById(R.id.spinner);


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
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                edit_tanggal.setText(""+dateFormat.format(newDate.getTime()));

                String tempat = spinner.getSelectedItem().toString();
                String MY_PREFS_NAME = "MyPrefsFile";
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("tanggal", ""+dateFormat.format(newDate.getTime()));
                editor.putString("tempat", ""+tempat);

                editor.apply();





            }
            },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
        return true;
    }

}
