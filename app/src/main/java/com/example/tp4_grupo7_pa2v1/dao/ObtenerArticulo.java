package com.example.tp4_grupo7_pa2v1.dao;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo7_pa2v1.conexion.DataDB;
import com.example.tp4_grupo7_pa2v1.entidad.Articulo;
import com.example.tp4_grupo7_pa2v1.entidad.Categoria;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ObtenerArticulo extends AsyncTask<String, Void, String> {
    private String id;
    private EditText etNombre, etStock;
    private Spinner spCategoria;
    private Context context;
    Articulo articulo;

    public ObtenerArticulo(String id, EditText etNombre, EditText etStock, Spinner spCategoria, Context context) {
        this.id = id;
        this.etNombre = etNombre;
        this.etStock = etStock;
        this.spCategoria = spCategoria;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        try {
            ResultSet rs = DataDB.ExecuteResultSet("SELECT * FROM articulo WHERE ID = " + id);

            articulo = new Articulo();

            if (rs.next())
            {
                articulo.setId(Integer.valueOf(id));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setStock(rs.getInt("stock"));
                articulo.setIdCategoria(rs.getInt("idCategoria"));
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
        if (articulo != null && articulo.getId() != 0)
        {
            etNombre.setText(articulo.getNombre());
            etStock.setText(String.valueOf(articulo.getStock()));
            spCategoria.setSelection(articulo.getIdCategoria() - 1);
        }
        else
        {
            etNombre.setText("");
            etStock.setText("");
            Toast.makeText(context, "El artículo con id " + id + " no existe.", Toast.LENGTH_SHORT).show();
        }
    }

}
