package com.example.tp4_grupo7_pa2v1.conexion;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.tp4_grupo7_pa2v1.adapter.ArticuloAdapter;
import com.example.tp4_grupo7_pa2v1.entidad.Articulo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class DataMainActivity extends AsyncTask<String, Void, String> {
    private ListView lvArticulos;
    private Context context;
    private ArrayList<Articulo> articulos = new ArrayList<Articulo>();

    public DataMainActivity(ListView lv, Context ct)
    {
        lvArticulos = lv;
        context = ct;
    }

    @Override
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            ResultSet rs = DataDB.ExecuteResultSet("SELECT * FROM articulo");

            Articulo articulo;
            while(rs.next()) {
                articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setStock(rs.getInt("stock"));
                articulo.setIdCategoria(rs.getInt("idCategoria"));
                articulos.add(articulo);
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
    protected void onPostExecute(String response) {
        ArticuloAdapter adapter = new ArticuloAdapter(context, articulos);
        lvArticulos.setAdapter(adapter);
    }
}