package com.example.jules.restofacile.com.resto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jules.restofacile.R;
import com.example.jules.restofacile.com.resto.entite.EntitePlat;

import java.util.ArrayList;

/**
 * Created by JULES on 30/07/2016.
 */
public class PlatListAdapter extends BaseAdapter {

    private ArrayList<EntitePlat> listPlat;
    private LayoutInflater layoutInflater;


    public PlatListAdapter(Context aContext, ArrayList<EntitePlat> listPlat) {
        this.listPlat = listPlat;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listPlat.size();
    }

    @Override
    public Object getItem(int position) {
        return listPlat.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_plat, null);
            holder = new ViewHolder();
            holder.libelle = (TextView) convertView.findViewById(R.id.libelle);
            holder.nom = (TextView) convertView.findViewById(R.id.nom);
            holder.cat = (TextView)convertView.findViewById(R.id.cat);
            holder.prix = (TextView)convertView.findViewById(R.id.prix);
            holder.img = (ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.libelle.setText(listPlat.get(position).getLibelle());
        holder.nom.setText(listPlat.get(position).getNom());
        holder.prix.setText(listPlat.get(position).getPrix()+" CFA");
        holder.cat.setText(listPlat.get(position).getCat());
        holder.img.setImageResource(listPlat.get(position).getImg());
        return convertView;
    }

    static class ViewHolder {
        TextView nom,prix,cat;
        TextView libelle;
        ImageView img;
    }
}
