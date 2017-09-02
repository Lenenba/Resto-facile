package com.example.jules.restofacile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jules.restofacile.com.resto.controller.CommandeController;
import com.example.jules.restofacile.com.resto.entite.EntiteCommande;

import java.util.ArrayList;
import java.util.HashMap;

public class Commander2Activity extends AppCompatActivity {

    int id_clt, toto;
    String nom_clt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander2);
        TextView total = (TextView)findViewById(R.id.total);
        LinearLayout lin = (LinearLayout)findViewById(R.id.lin);
        LinearLayout lin2 = (LinearLayout)findViewById(R.id.lin2);
        Button confirm = (Button)findViewById(R.id.confirmer);

        Intent intent = getIntent();
        nom_clt = intent.getStringExtra("nom");
        id_clt = intent.getIntExtra("id_clt", 0);
        toto = intent.getIntExtra("total", 0);
        total.setText(toto +"FCFA");

       final CommandeController commandeController = new CommandeController();
       final HashMap<String, Integer> map = (HashMap<String,Integer>)intent.getSerializableExtra("map");
       final ArrayList<Integer> list_idplat = intent.getIntegerArrayListExtra("id_plat");
       final ArrayList<Integer> list_idresto = intent.getIntegerArrayListExtra("id_resto");
       final ArrayList<String> list_libelleplat = intent.getStringArrayListExtra("liste_plat");

        for (int i =0; i<list_libelleplat.size(); i++){
            final TextView platL = new TextView(Commander2Activity.this);
            final TextView platL2 = new TextView(Commander2Activity.this);
            platL.setText(list_libelleplat.get(i).toString());
            platL2.setText("------------");
            lin.addView(platL);
            lin.addView(platL2);
        }
        for (int i =0; i<list_libelleplat.size(); i++){
            final TextView platL3 = new TextView(Commander2Activity.this);
            final TextView platL4 = new TextView(Commander2Activity.this);
             platL3.setText(map.get(list_libelleplat.get(i)).toString());
             platL4.setText("-----------------------------");
             lin2.addView(platL3);
             lin2.addView(platL4);
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i =0; i<list_libelleplat.size(); i++){
                    EntiteCommande entiteCommande = new EntiteCommande();
                    entiteCommande.setIdclt(id_clt);
                    entiteCommande.setIdresto(list_idresto.get(i));
                    entiteCommande.setIdplat(list_idplat.get(i));
                    commandeController.Register(entiteCommande);
                    Toast.makeText(Commander2Activity.this, "commande enregistrer", Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(Commander2Activity.this, CommandeActivity.class);
                      startActivity(in);
                }
            }
        });
    }
}
