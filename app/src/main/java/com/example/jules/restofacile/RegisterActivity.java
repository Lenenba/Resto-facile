package com.example.jules.restofacile;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jules.restofacile.com.resto.controller.ClientController;
import com.example.jules.restofacile.com.resto.entite.EntiteClient;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        final EditText nom = (EditText)findViewById(R.id.libelle);
        final EditText prenom = (EditText)findViewById(R.id.prenom);
        final EditText email = (EditText)findViewById(R.id.email);
        final EditText adresse = (EditText)findViewById(R.id.adresse);
        final EditText mdp = (EditText)findViewById(R.id.mdp);
        final EditText telephone = (EditText)findViewById(R.id.telephone);

        final EntiteClient client = new EntiteClient();
        final ClientController clientController = new ClientController();

        Button register = (Button)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.setNom(nom.getText().toString());
                client.setPrenom(prenom.getText().toString());
                client.setEmail(email.getText().toString());
                client.setAdresse(adresse.getText().toString());
                client.setMdp(mdp.getText().toString());
                client.setTelephone(Integer.parseInt(telephone.getText().toString()));

                if(clientController.Register(client)){
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this,"Enregistrement Echoue" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
