package com.example.jules.restofacile;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RestoActivity extends AppCompatActivity {

    String nom,prenom;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resto);
        Intent intent = getIntent();
        nom = intent.getStringExtra("nom");
        prenom=intent.getStringExtra("prenom");
        id = intent.getIntExtra("id", 0);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.logo);
        actionBar.setTitle("Salut "+nom);
        Button commander = (Button) findViewById(R.id.order);


        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RestoActivity.this, CommandeActivity.class);
                intent1.putExtra("nom",nom);
                intent1.putExtra("prenom", prenom);
                intent1.putExtra("id", id);
                startActivity(intent1);
            }
        });
        Button reserver = (Button)findViewById(R.id.reserver);
        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(RestoActivity.this, ReserverActivity.class);
                intent2.putExtra("nom",nom);
                intent2.putExtra("prenom", prenom);
                intent2.putExtra("id", id);
                startActivity(intent2);
            }
        });
    }
}
