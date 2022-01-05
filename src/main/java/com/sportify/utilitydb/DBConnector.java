package com.sportify.utilitydb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private static Connection con = null;

    public static Connection getConnector() throws SQLException {
        if (con != null && !DBConnector.con.isClosed())
            return DBConnector.con;

        try (InputStream input = new FileInputStream("src/main/resources/com.sportify.login/DBOnline.properties")) {

            Properties prop = new Properties();

            // carica il file properties
            prop.load(input);
            //ritorna la connessione al DB specificato nel file DB.properties
            con = DriverManager.getConnection(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.psw"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return con;
    }

    private DBConnector(){} //La classe ha metodi statici, non deve essere istanziata

}
