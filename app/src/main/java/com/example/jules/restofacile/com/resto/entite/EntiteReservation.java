package com.example.jules.restofacile.com.resto.entite;


import java.util.Date;

/**
 * Created by JULES on 30/07/2016.
 */
public class EntiteReservation {

    int id,persoe,id_resto,id_client;
    Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersoe() {
        return persoe;
    }

    public void setPersoe(int persoe) {
        this.persoe = persoe;
    }

    public int getId_resto() {
        return id_resto;
    }

    public void setId_resto(int id_resto) {
        this.id_resto = id_resto;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}


