package com.net2software.dep;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditUserNameActivity extends AppCompatActivity {

    private EditText username;
    private Button cancle ,btn_simpan;

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
        }


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

}
