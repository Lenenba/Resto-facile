package com.example.jules.restofacile.com.resto.controller;

import android.app.Application;
import android.util.Log;

import com.example.jules.restofacile.R;
import com.example.jules.restofacile.com.resto.Constance;
import com.example.jules.restofacile.com.resto.entite.EntiteCommande;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by JULES on 30/07/2016.
 */
public class CommandeController extends Application{

    Constance constance;


    public Boolean Register(EntiteCommande cde){

        InputStream is = null;
        String result = "";
        Boolean success = false;

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("idclt",Integer.toString(cde.getIdclt())));
        nameValuePairs.add(new BasicNameValuePair("idplat", Integer.toString(cde.getIdplat())));
        nameValuePairs.add(new BasicNameValuePair("idresto",Integer.toString(cde.getIdresto())));

        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(constance.REGISTER_COMMANDE_URL);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            System.out.println("connexion");
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

            JSONObject json_data = new JSONObject(result);
            success = json_data.getBoolean("success");

            // Affichage dans le LogCat
            Log.i("log_tag","bool: "+json_data.getBoolean("success"));

        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data " + e.toString());

        }

        return success;
    }

}
