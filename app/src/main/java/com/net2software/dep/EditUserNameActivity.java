package com.net2software.dep;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditUserNameActivity extends AppCompatActivity {

    private EditText username;
    private Button cancle ,btn_simpan;
    private String id, nama;
    ProgressDialog pd;
    public static final String url = Server.URL_UPDATEUSERNAME;
    private static final String TAG = EditUserNameActivity.class.getSimpleName();
    String tag_json_obj = "json_obj_req";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_username);

        username = (EditText)findViewById(R.id.edit_user);
        cancle =(Button) findViewById(R.id.cancle);
        btn_simpan =(Button) findViewById(R.id.update);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            username.setText(""+bundle.getString("user"));
            id = bundle.getString("id");
            nama = username.getText().toString();
        }


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadJSON(id,nama);
                Intent intent = new Intent();
                intent.putExtra("editTextValue", ""+username.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditUserNameActivity.this,ThreeFragment.class);
                startActivity(i);
                finish();

            }
        });

    }

    public void loadJSON(final String iduser, final String name){
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
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

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
                params.put("id", iduser);
                params.put("name", name);
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


