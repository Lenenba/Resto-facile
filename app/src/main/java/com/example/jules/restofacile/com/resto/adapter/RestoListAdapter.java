package com.example.jules.restofacile.com.resto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jules.restofacile.R;
import com.example.jules.restofacile.com.resto.entite.EntiteResto;

import java.util.ArrayList;

/**
 * Created by JULES on 30/07/2016.
 */
public class RestoListAdapter extends BaseAdapter {

    private ArrayList<EntiteResto> listResto;
    private LayoutInflater layoutInflater;


    public RestoListAdapter(Context aContext, ArrayList<EntiteResto> listResto) {
        this.listResto = listResto;
        layoutInflater = LayoutInflater.from(aContext);
    }
    @Override
    public int getCount() {
        return listResto.size();
    }

    @Override
    public Object getItem(int position) {
        return listResto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.resto_layout, null);
            holder = new ViewHolder();
            holder.nom = (TextView) convertView.findViewById(R.id.libelle);
            holder.tel = (TextView) convertView.findViewById(R.id.nom);
            holder.img = (ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tel.setText("TEL:"+listResto.get(position).getTelephone());
        holder.nom.setText(listResto.get(position).getNom());
        holder.img.setImageResource(listResto.get(position).getImg());
        return convertView;
    }

    static class ViewHolder {
        TextView nom;
        TextView tel;
        ImageView img;
    }
}
