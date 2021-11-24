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

public class InscriptionActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etPwd1;
    private EditText etPwd2;
    private EditText etMail;
    private EditText etNom;
    private EditText etPrenom;
    private Button btSend;
    private Button btGoCnx;
    private StringRequest stringRequest;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        etName = (EditText) findViewById(R.id.etNameInsc);
        etMail = (EditText) findViewById(R.id.etMail);
        etNom = (EditText) findViewById(R.id.etNom);
        etPrenom= (EditText) findViewById(R.id.etPrenom);
        etPwd1  = (EditText) findViewById(R.id.etPwd1);
        etPwd2 = (EditText) findViewById(R.id.etPwd2);
        btSend = (Button) findViewById(R.id.btSendInsc);
        btGoCnx = (Button) findViewById(R.id.btGoCnx);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toRecap();
            }
        });
        btGoCnx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent2);
            }
        });
    }
    public void toRecap(){
        String login = etName.getText().toString();
        String email = etMail.getText().toString();
        String nom = etNom.getText().toString();
        String prenom = etPrenom.getText().toString();
        String mdp1 = etPwd1.getText().toString();
        String mdp2 = etPwd2.getText().toString();
        if (mdp1.equals(mdp2) && !login.equalsIgnoreCase("") && !mdp1.equalsIgnoreCase("") && !mdp2.equalsIgnoreCase("") && !prenom.equalsIgnoreCase("") && !nom.equalsIgnoreCase("") && !email.equalsIgnoreCase("")){
            stringRequest = new StringRequest(Request.Method.POST, DBPages.inscription_url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("resultatAppli",response);
                    if(StringUtils.contains(response, "Inscription")){
                        Intent intent3 = new Intent(getApplicationContext(), ActivityRecap.class);
                        intent3.putExtra("login",login);
                        startActivity(intent3);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "FAUX", Toast.LENGTH_SHORT).show();
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
                    params.put("mail",email);
                    params.put("nom",nom);
                    params.put("prenom",prenom);
                    params.put("mdp1",mdp1);
                    params.put("mdp2",mdp2);
                    return params;
                }
            };
            requestQueue = Volley.newRequestQueue(InscriptionActivity.this);
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(getApplicationContext(),"Les champs sont incomplets", Toast.LENGTH_SHORT);
        }
    }

}