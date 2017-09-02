package com.example.jules.restofacile.com.resto.entite;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by JULES on 30/07/2016.
 */
public class EntiteResto {

    String nom;
    Date date;
    int nbre ;
    int img;
    int telephone,id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    ArrayList <EntitePlat> listePlat;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbre() {
        return nbre;
    }

    public void setNbre(int nbre) {
        this.nbre = nbre;
    }

    public ArrayList<EntitePlat> getListePlat() {
        return listePlat;
    }

    public void setListePlat(ArrayList<EntitePlat> listePlat) {
        this.listePlat = listePlat;
    }
}
