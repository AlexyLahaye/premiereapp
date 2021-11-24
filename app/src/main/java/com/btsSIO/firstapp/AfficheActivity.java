package com.btsSIO.firstapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AfficheActivity extends AppCompatActivity {
    private ListView lvMembre;
    private ArrayList<Membres> listeMembres;
    private JsonArrayRequest jsonArrayRequest;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        lvMembre = (ListView) findViewById(R.id.lvMembre);
        listeMembres = new ArrayList<>();

        chargerLaliste();
    }
    private void chargerLaliste(){
        jsonArrayRequest = new JsonArrayRequest((DBPages.afficheTout_url), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i< response.length(); i ++){
                    try {
                        jsonObject = response.getJSONObject(i);
                        Membres unMembre = new Membres(jsonObject.getInt("id"),
                                jsonObject.getString("login"),jsonObject.getString("mdp"),
                                jsonObject.getString("nom"), jsonObject.getString("prenom"),
                                jsonObject.getString("mail"), jsonObject.getString("img"));
                        listeMembres.add(unMembre);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }

                }
                setAdapterMembre(listeMembres);
            }
        }, new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext() ,error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(AfficheActivity.this);
        requestQueue.add(jsonArrayRequest);
    }
    private void setAdapterMembre(ArrayList<Membres> listeMembres) {
        ListeAdapteur listeAdapter = new ListeAdapteur(this, listeMembres);
        lvMembre.setAdapter((listeAdapter));
    }
}