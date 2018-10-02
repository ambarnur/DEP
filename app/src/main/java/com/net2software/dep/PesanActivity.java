package com.net2software.dep;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PesanActivity extends AppCompatActivity {

    private static final String TAG = "PesanActivity";
    TextView textView,jam;
    Button button_pesan;
    Spinner spinner;
    EditText nama;
    EditText nohp;

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

        nama = (EditText) findViewById(R.id.ev_namalengkap);
        nohp = (EditText) findViewById(R.id.ev_nohp);
        button_pesan = (Button) findViewById(R.id.btn_pesan);
        textView = (TextView) findViewById(R.id.tv_pilih_jam);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PesanActivity.this, PilihJam.class);
                startActivityForResult(i, 1);
            }
        });

        button_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PesanActivity.this, DetailBooking.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("nama", nama.getText().toString());
                intent.putExtra("nohp", nohp.getText().toString());
                intent.putExtra("jam", jam.getText().toString());
                intent.putExtra("durasi", spinner.getSelectedItem().toString());
                startActivity(intent);
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getIncomingIntent();


    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if (getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");

        String MY_PREFS_NAME = "MyPrefsFile";
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("lapangan", imageName);
        editor.apply();
            setImage(imageUrl, imageName);
        }
    }

    private void setImage(String imageUrl, String imageName) {
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);

        ImageView image = findViewById(R.id.image_pesan);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String strEditText = data.getStringExtra("editTextValue");
                jam.setText(strEditText);

            }
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        finish();
        return true;
    }
}
