package com.example.jules.restofacile.com.resto.controller;

import android.app.Application;
import android.util.Log;

import com.example.jules.restofacile.com.resto.Constance;
import com.example.jules.restofacile.com.resto.entite.EntiteResto;

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
public class RestoController extends Application {

    private Constance constance;
    ArrayList<EntiteResto> listeResto;

    public ArrayList<EntiteResto> findAllResto(){
        InputStream is = null;
        String result = "";
        listeResto = new ArrayList<>();

        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(constance.URL_LIST_RESTO);
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

                Log.i("log_tag", ", nom: " + json_data.getString("nom_r") +
                                ",telephone: " + json_data.getInt("telephone_r")

                );
                EntiteResto resto = new EntiteResto();
                resto.setId(json_data.getInt("id_r"));
                resto.setNom(json_data.getString("nom_r"));
                resto.setTelephone(json_data.getInt("telephone_r"));
                resto.setImg(json_data.getInt("img_r"));
                listeResto.add(resto);
            }
        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data " + e.toString());

        }
        return listeResto;
    }
}
