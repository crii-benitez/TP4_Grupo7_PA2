package com.example.tp4_grupo7_pa2v1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.tp4_grupo7_pa2v1.R;
import com.example.tp4_grupo7_pa2v1.entidad.Articulo;

import java.util.List;


public class ArticuloAdapter extends ArrayAdapter<Articulo> {

    public ArticuloAdapter(Context context, List<Articulo> objetos) {
        super(context, R.layout.list_template, objetos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_template, null);

        TextView tvnombre = (TextView) item.findViewById(R.id.nombre);
        TextView tvstock = (TextView) item.findViewById(R.id.stock);

        tvnombre.setText(getItem(position).getNombre());
        tvstock.setText(getItem(position).getStock()+"");

        return item;
    }
}