package com.example.jules.restofacile.com.resto.controller;

import android.app.Application;
import android.util.Log;

import com.example.jules.restofacile.R;
import com.example.jules.restofacile.com.resto.Constance;
import com.example.jules.restofacile.com.resto.entite.EntitePlat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by JULES on 30/07/2016.
 */
public class PlatController extends Application {

    Constance constance;
    ArrayList<EntitePlat> listePlat;

    public ArrayList<EntitePlat> findAllPlat(){
        InputStream is = null;
        String result = "";
        listePlat = new ArrayList<>();

        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(constance.URL_LIST_PLAT);
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

        }catch(Exception e){
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
        // Convertion de la requête en string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");

            }
            is.close();
            result=sb.toString();
        }catch(Exception e){
            Log.e("log_tag", "Error converting result " + e.toString());
        }
        // Parse les données JSON
        try{
            JSONArray jArray = new JSONArray(result);

            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);

                Log.i("log_tag", ", nom: " + json_data.getString("libelle") +
                                ",telephone: " + json_data.getInt("prix" ) + R.drawable.boulangerie

                );
                EntitePlat plat = new EntitePlat();
                plat.setId(json_data.getInt("id_plat"));
                plat.setId_r(json_data.getInt("id_resto"));
                plat.setLibelle(json_data.getString("libelle"));
                plat.setCat(json_data.getString("libelle_cat"));
                plat.setPrix(json_data.getInt("prix"));
                plat.setImg(json_data.getInt("img"));
                plat.setNom(json_data.getString("nom_resto"));
                listePlat.add(plat);
            }
        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data " + e.toString());

        }
        return listePlat;
    }


}
