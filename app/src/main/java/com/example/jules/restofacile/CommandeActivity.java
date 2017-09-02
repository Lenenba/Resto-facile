package com.example.jules.restofacile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jules.restofacile.com.resto.adapter.PlatListAdapter;
import com.example.jules.restofacile.com.resto.controller.PlatController;
import com.example.jules.restofacile.com.resto.entite.EntitePlat;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandeActivity extends AppCompatActivity {

    String nom_clt,prenom_clt;
    int total, id_clt;
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        Intent intent = getIntent();
        nom_clt = intent.getStringExtra("nom");
        prenom_clt=intent.getStringExtra("prenom");
        id_clt = intent.getIntExtra("id", 0);
        final int[] qte = new int[1];
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo);
        actionBar.setTitle("Salut " + nom_clt);
        final HashMap<String, Integer>map = new HashMap<>();
        final ArrayList<EntitePlat>list2 = new ArrayList<>();
        final ArrayList<String>listplat3 = new ArrayList<String>();
        final ArrayList<Integer>listidresto = new ArrayList<Integer>();
        final ArrayList<Integer>listidplat = new ArrayList<Integer>();
        final TextView result = (TextView)findViewById(R.id.result);
        PlatController platController= new PlatController();
        ArrayList list_details = platController.findAllPlat();

        final ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new PlatListAdapter(this, list_details));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = lv.getItemAtPosition(position);
                final EntitePlat plat = (EntitePlat) o;
                if (list2.contains(plat)) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CommandeActivity.this);

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("voulez annuler ce produit")
                            .setCancelable(false)
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    setTotal(Integer.parseInt(result.getText().toString()) - (plat.getPrix() * map.get(plat.getLibelle())));
                                    result.setText(Integer.toString(total));
                                    listidplat.remove(plat.getId());
                                    listidresto.remove(plat.getId_r());
                                    listplat3.remove(plat.getLibelle());
                                    list2.remove(plat);
                                    map.remove(plat.getLibelle());
                                }
                            })
                            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();

                } else {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CommandeActivity.this);

                    // set title
                    alertDialogBuilder.setTitle("Entrer la quantité");
                    final EditText input = new EditText(CommandeActivity.this);
                    input.setHint("Entrer la quantiter a commander");
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    alertDialogBuilder.setView(input);

                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false)
                            .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    if (input.getText().toString().isEmpty()) {
                                        dialog.cancel();
                                    } else {
                                        qte[0] = Integer.parseInt(input.getText().toString());
                                        map.put(plat.getLibelle(), qte[0]);
                                        setTotal(Integer.parseInt(result.getText().toString()) + (plat.getPrix() * qte[0]));
                                        result.setText(Integer.toString(total));
                                        list2.add(plat);
                                        listidplat.add(plat.getId());
                                        listidresto.add(plat.getId_r());
                                        listplat3.add(plat.getLibelle());
                                    }

                                }
                            })
                            .setNegativeButton("non", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    dialog.cancel();
                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            }
        });

        Button valider = (Button)findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(list2.isEmpty()){
                    Toast.makeText(CommandeActivity.this,"Vous devez entrer au moins un produit dans votre commande",Toast.LENGTH_LONG).show();
                }else{
                Intent i = new Intent(CommandeActivity.this, Commander2Activity.class);
                i.putExtra("map", map);
                i.putExtra("id_clt", id_clt);
                i.putExtra("nom_clt", nom_clt);
                i.putExtra("total",getTotal());
                i.putIntegerArrayListExtra("id_plat", listidplat);
                i.putIntegerArrayListExtra("id_resto", listidresto);
                i.putStringArrayListExtra("liste_plat", listplat3);
                startActivity(i);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:

                return true;
            case R.id.menu_help:
                
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




