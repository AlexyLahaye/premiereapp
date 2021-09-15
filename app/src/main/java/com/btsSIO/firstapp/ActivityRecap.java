package com.btsSIO.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityRecap extends AppCompatActivity {
    private TextView tvRecap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);

        tvRecap = (TextView) findViewById(R.id.tvRecap);

        Intent intent = getIntent();

        String monRecap = "Bienvenue " + intent.getStringExtra("Login");
        monRecap += "\n\n\nVotre login : " + intent.getStringExtra("Login");
        monRecap += "\nVotre mdp : " + intent.getStringExtra("pwd");

        tvRecap.setText(monRecap);
    }
}