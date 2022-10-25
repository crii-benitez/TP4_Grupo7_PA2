package com.example.tp4_grupo7_pa2v1.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataDB {
    //Información de la BD
    public static String host="sql10.freesqldatabase.com";
    public static String port="3306";
    public static String nameBD="sql10527351";
    public static String user="sql10527351";
    public static String pass="mQSfKcqFuM";

    //Información para la conexion
    public static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/"+nameBD;
    public static String driver = "com.mysql.jdbc.Driver";

    public static ResultSet ExecuteResultSet(String consulta) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
        Statement st = con.createStatement();
        return st.executeQuery(consulta);
    }
}