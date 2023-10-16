package com.patika.Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnector {
    private Connection connect=null;
    public Connection connectDB(){
        try {
            this.connect = DriverManager.getConnection(Config.DB_URL,Config.DB_USERNAME, Config.DB_PASSWORD);
        } catch (SQLException e) {
            Helper.showMsg("Veritabanı Baglantısını kontrol ediniz!!");
        }
        return this.connect;
    }
    public static Connection getConnectionInstance(){
        DBConnector db = new DBConnector();
        return db.connectDB();
    }

    public static PreparedStatement getPreparedStatement(String query){

        PreparedStatement ps = null;
        try {
            ps = DBConnector.getConnectionInstance().prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ps;
    }

}
