package com.example.tp4_grupo7_pa2v1.dao;

import android.os.AsyncTask;
import android.widget.Toast;

//import com.example.tp4_grupo7_pa2v1.adapter.ArticuloAdapter;
import com.example.tp4_grupo7_pa2v1.conexion.DataDB;
import com.example.tp4_grupo7_pa2v1.entidad.Articulo;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;

public class AltaArticulo extends AsyncTask<String, Void, String> {

    private Articulo articulo;
    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        String query = MessageFormat.format("INSERT INTO articulo(id,nombre,stock,idCategoria) VALUES({0}, ''{1}'', {2}, {3})",
                articulo.getId(), articulo.getNombre(),articulo.getStock(), articulo.getIdCategoria());

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