package com.patika.Model;

import com.patika.Helper.DBConnector;
import com.patika.Helper.Helper;

import java.sql.PreparedStatement;

public class Educator extends User{

    public Educator() {
    }

    public Educator(int id, String name, String username, String password, String type) {
        super(id, name, username, password, type);
    }

    public static boolean addCourse(int educatorID,int patikaID,String name,String language){
        try(PreparedStatement ps = DBConnector.getPreparedStatement("INSERT INTO course (educator_id,patika_id,name,language) VALUES(?,?,?,?)")){
            ps.setInt(1,educatorID);
            ps.setInt(2,patikaID);
            ps.setString(3,name);
            ps.setString(4,language);
            int result = ps.executeUpdate();
            ps.close();
            return result != -1;
        }catch (Exception e){
            Helper.showMsg("Veritabanı Hatası");
        }
        return false;
    }
}
