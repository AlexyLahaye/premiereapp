package com.btsSIO.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPwd;
    private Button btSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etPwd  = (EditText) findViewById(R.id.etPwd);
        btSend = (Button) findViewById(R.id.btSend);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRecap();
            }
        });
    }
    public void toRecap(){
        Intent intent = new Intent(this,ActivityRecap.class );
        intent.putExtra( "Login",etName.getText().toString());
        intent.putExtra( "pwd",etPwd.getText().toString());
        startActivity(intent);
    }

}