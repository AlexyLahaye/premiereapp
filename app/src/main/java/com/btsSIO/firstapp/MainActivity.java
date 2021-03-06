package com.btsSIO.firstapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.StringUtils;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPwd;
    private Button btSend;
    private Button btGoInsc;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etPwd  = (EditText) findViewById(R.id.etPwd);
        btSend = (Button) findViewById(R.id.btSend);
        btGoInsc = (Button) findViewById(R.id.btGoInsc);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRecap();
            }
        });

        btGoInsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toInscription();
            }
        });
    }
    public void toInscription (){
        Intent intent = new Intent(getApplicationContext(),InscriptionActivity.class);
        startActivity(intent);
        finish();
    }
    public void toRecap(){
        String login = etName.getText().toString();
        String mdp = etPwd.getText().toString();
        if (!login.equalsIgnoreCase("") && !mdp.equalsIgnoreCase("")){
            stringRequest = new StringRequest(Request.Method.POST, DBPages.login_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("resultatAppli",response);
                    if(StringUtils.contains(response, "Connect??")){
                        Intent intent = new Intent(getApplicationContext(), ActivityRecap.class);
                        intent.putExtra("login",login);
                        intent.putExtra("response", response);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Mauvais login ou mot de passe", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams(){
                    Map<String,String> params = new HashMap<>();
                    params.put("login",login);
                    params.put("mdp",mdp);
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(stringRequest);
    }else{
            Toast.makeText(getApplicationContext(),"Les champs sont incomplets", Toast.LENGTH_SHORT);
        }
    }

}