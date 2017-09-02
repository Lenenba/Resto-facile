package com.example.jules.restofacile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jules.restofacile.com.resto.controller.ReservationController;
import com.example.jules.restofacile.com.resto.entite.EntiteReservation;

import java.sql.Date;

public class Reserver2Activity extends AppCompatActivity {

    String nom_clt,nom_resto,prenom;
    int id_clt,id_resto,tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserver2);

        Intent intent = getIntent();
        id_resto = intent.getIntExtra("id_resto", 0);
        tel = intent.getIntExtra("tel", 0);
        nom_resto = intent.getStringExtra("nom_resto");
        nom_clt = intent.getStringExtra("nom_clt");
        prenom=intent.getStringExtra("prenom");
        id_clt = intent.getIntExtra("id_clt", 0);

        final  EditText nbre_perso = (EditText)findViewById(R.id.peroe);
        final  EditText date = (EditText)findViewById((R.id.date));
        final  TextView nom = (TextView)findViewById(R.id.libelle);
        final  TextView telephone = (TextView)findViewById(R.id.nom);

        nom.setText(nom_resto.toString());
        telephone.setText(Integer.toString(tel));

        final EntiteReservation entiteReservation = new EntiteReservation();

        Button valider = (Button)findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entiteReservation.setId_client(id_clt);
                entiteReservation.setId_resto(id_resto);
                entiteReservation.setPersoe(Integer.parseInt(nbre_perso.getText().toString()));
                entiteReservation.setDate(Date.valueOf(date.getText().toString()));
                ReservationController reservationController = new ReservationController();
                if(reservationController.Register(entiteReservation)){
                    Intent intent1 = new Intent(Reserver2Activity.this, ReserverActivity.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(Reserver2Activity.this, "Erreur lors de lenregistrement", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
