package com.sportify.utilitydb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DBConnector {

    private static Connection con = null;

    public static Connection getConnector() throws SQLException {
        if (con != null && !DBConnector.con.isClosed() && DBConnector.con.isValid(0))
            return DBConnector.con;
        else {
            try {
                Objects.requireNonNull(con).close();
            } catch (SQLException | NullPointerException e){
                //connection was already closed
            }
        }

        try (InputStream input = new FileInputStream("src/main/resources/com/sportify/database/DB.properties")) {

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
