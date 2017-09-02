package com.example.jules.restofacile;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jules.restofacile.com.resto.adapter.RestoListAdapter;
import com.example.jules.restofacile.com.resto.controller.RestoController;
import com.example.jules.restofacile.com.resto.entite.EntiteResto;

import java.util.ArrayList;

public class ReserverActivity extends AppCompatActivity {

    String nom,prenom;
    int id_clt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Intent intent = getIntent();
        nom = intent.getStringExtra("nom");
        prenom=intent.getStringExtra("prenom");
        id_clt = intent.getIntExtra("id", 0);
        RestoController restoController = new RestoController();
        ArrayList list_details = restoController.findAllResto();

        final ListView lv1 = (ListView) findViewById(R.id.listResto);
        lv1.setAdapter(new RestoListAdapter(this, list_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = lv1.getItemAtPosition(position);
                EntiteResto resto = (EntiteResto) o;
                Intent intent = new Intent(ReserverActivity.this, Reserver2Activity.class);
                intent.putExtra("id_resto", resto.getId());
                intent.putExtra("nom_resto", resto.getNom());
                intent.putExtra("tel", resto.getTelephone());
                intent.putExtra("nom_clt",nom);
                intent.putExtra("prenom", prenom);
                intent.putExtra("id_clt", id_clt);
                startActivity(intent);


            }
        });
    }
}
