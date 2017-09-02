package com.example.jules.restofacile.com.resto.controller;

import android.app.Application;
import android.util.Log;

import com.example.jules.restofacile.com.resto.Constance;
import com.example.jules.restofacile.com.resto.entite.EntiteClient;

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
 * Created by JULES on 29/07/2016.
 */
public class ClientController extends Application {

    private EntiteClient client;
    private Constance constance;

    public EntiteClient getClient() {
        return client;
    }

    public void setClient(EntiteClient client) {
        this.client = client;
    }

    public Boolean Register(EntiteClient client){

        InputStream is = null;
        String result = "";
        Boolean success = false;
        // Envoyer la requête au script PHP.
        // Script PHP : $sql=mysql_query("select * from tblVille where Nom_ville like '".$_REQUEST['ville']."%'");
        // $_REQUEST['ville'] sera remplacé par L dans notre exemple.
        // Ce qui veut dire que la requête enverra les villes commençant par la lettre L
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("nom",client.getNom()));
        nameValuePairs.add(new BasicNameValuePair("prenom",client.getPrenom()));
        nameValuePairs.add(new BasicNameValuePair("email",client.getEmail()));
        nameValuePairs.add(new BasicNameValuePair("adresse",client.getAdresse()));
        nameValuePairs.add(new BasicNameValuePair("mdp",client.getMdp()));
        nameValuePairs.add(new BasicNameValuePair("tel",Integer.toString(client.getTelephone())));
        // Envoie de la commande http
        System.out.println(Integer.toString(client.getTelephone()));
        System.out.println(client.getNom());
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(constance.REGISTER_CLIENT_URL);
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
                Log.i("log_tag","nom: "+json_data.getBoolean("success"));

        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data " + e.toString());

        }

         return success;
    }



    public Boolean findClient(String nom,String mdp) {
        InputStream is = null;
        boolean reponse = false;
        String result = "";
        String returnt= null;

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("nom",nom));
        nameValuePairs.add(new BasicNameValuePair("mdp",mdp));
        // Envoie de la commande http
        try{
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(constance.FIND_CLIENT_URL);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
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
            client = new EntiteClient();
            JSONObject json_data = new JSONObject(result);
            if(json_data.length()>=1){

                // Affichage dans le LogCat
                Log.i("log_tag","nom: "+json_data.getString("nom_clt")+
                                ", motdepasse: "+json_data.getString("motdepasse")
                );
                // Résultats de la requête
                returnt += "\n\t" + json_data.getString("nom_clt");
                client.setNom(json_data.getString("nom_clt"));
                client.setPrenom(json_data.getString("prenom_clt"));
                client.setId(json_data.getInt("id_clt"));
                reponse = true;
                 // Résultats de la requête

              returnt += "\n\t" + json_data.getString("nom_clt") ;
            }else {

                reponse = false;
            }
        }catch(JSONException e){
            Log.e("log_tag", "Error parsing data " + e.toString());

        }
        return reponse;
    }
}
