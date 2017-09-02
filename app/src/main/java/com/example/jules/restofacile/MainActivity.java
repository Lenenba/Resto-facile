package com.example.jules.restofacile;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jules.restofacile.com.resto.controller.ClientController;
import com.example.jules.restofacile.com.resto.entite.EntiteClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setLogo(R.drawable.logo2);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final ClientController clientController = new ClientController();

        final EditText nom = (EditText) findViewById(R.id.libelle);
        final EditText mdp = (EditText)findViewById(R.id.libelle);

        TextView register_Link = (TextView)findViewById(R.id.register);
        register_Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register_View = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(register_View);
            }
        });

        Button login = (Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EntiteClient client = new EntiteClient();
               String n=nom.getText().toString().trim();
               String m= mdp.getText().toString().trim();
                if(n.isEmpty() || m.isEmpty()){
                    Toast.makeText(MainActivity.this, "Entrer des valeurs correct" ,Toast.LENGTH_LONG).show();
                }else{
                        if (clientController.findClient(n, m)){

                        Intent intent = new Intent(MainActivity.this, RestoActivity.class);
                            intent.putExtra("nom",clientController.getClient().getNom());
                            intent.putExtra("prenom",clientController.getClient().getPrenom());
                            intent.putExtra("id", clientController.getClient().getId());
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "nom ou mot de passe Incorrect", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }
}
