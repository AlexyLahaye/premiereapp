package com.btsSIO.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityRecap extends AppCompatActivity {
    private TextView tvRecap;
    private Button btAfficheTout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recap);

        tvRecap = (TextView) findViewById(R.id.tvRecap);
        btAfficheTout = (Button) findViewById(R.id.btAfficheTout);


        Intent intent = getIntent();

        String monRecap = "Vous êtes " + intent.getStringExtra("login");

        tvRecap.setText(monRecap);

        btAfficheTout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),AfficheActivity.class);
                startActivity(intent1);
            }
        });
    }
}