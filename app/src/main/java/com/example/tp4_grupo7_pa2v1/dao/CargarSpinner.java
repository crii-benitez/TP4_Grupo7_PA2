package com.example.tp4_grupo7_pa2v1.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.example.tp4_grupo7_pa2v1.entidad.Categoria;
import com.example.tp4_grupo7_pa2v1.conexion.DataDB;

public class CargarSpinner extends AsyncTask<String, Void, String> {
    private ArrayList<String> listaCategoria = new ArrayList<String>();
    private Spinner spinnerCat;
    private Context context;

    public CargarSpinner(Spinner spinner, Context context) {
        this.spinnerCat = spinner;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        listaCategoria.clear();
        String response = "";
        try {
            ResultSet rs = DataDB.ExecuteResultSet("SELECT * FROM categoria");

            Categoria categoria;
            while(rs.next()) {
                categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setDescripcion(rs.getString("descripcion"));
                listaCategoria.add(categoria.getDescripcion());
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            response = "Conexion no exitosa";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, listaCategoria);
        spinnerCat.setAdapter(adapter);
    }
}
