package com.example.tp4_grupo7_pa2v1.dao;

import android.os.AsyncTask;

import com.example.tp4_grupo7_pa2v1.conexion.DataDB;
import com.example.tp4_grupo7_pa2v1.entidad.Articulo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.MessageFormat;

public class ModifyArticulo extends AsyncTask<String, Void, String> {

    private Articulo articulo;
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        String query = "UPDATE articulo SET " + "nombre = '" + articulo.getNombre() +
                "', stock = " + articulo.getStock() + ", idCategoria = " + articulo.getIdCategoria() + " WHERE id = " + articulo.getId();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            st.executeUpdate(query);
            response = "Exito al registrar";
        }
        catch(Exception e) {
            e.printStackTrace();
            response = "Error al registrar";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        //agregar algo
    }
}