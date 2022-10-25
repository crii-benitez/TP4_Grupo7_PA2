package com.example.tp4_grupo7_pa2v1.dao;

import android.os.AsyncTask;

import com.example.tp4_grupo7_pa2v1.conexion.DataDB;
import com.example.tp4_grupo7_pa2v1.entidad.Articulo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuscarArticulo extends AsyncTask<String, Void, Articulo> {

    private int id;

    private Articulo articulo;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    protected Articulo doInBackground(String... strings) {
        articulo = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE id = " + id);

            while(rs.next()) {
                articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setStock(rs.getInt("stock"));
                articulo.setIdCategoria(rs.getInt("idCategoria"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return articulo;
    }
}