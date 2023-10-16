package com.patika.Model;

import com.patika.Helper.DBConnector;
import com.patika.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Patika {
    private int id;
    private String name;

    public Patika(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArrayList<Patika> getList(){
        ArrayList<Patika> patikaList = new ArrayList<>();
        Patika obj;
        try{
            Statement st = DBConnector.getConnectionInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM patika");
            while (rs.next()){
                obj = new Patika(rs.getInt("id"),rs.getString("name"));
                patikaList.add(obj);
            }
            st.close();
            rs.close();
        }catch (Exception e){

        }
        return patikaList;
    }

    public static boolean add(String patikaName){
        String query = "INSERT INTO patika (name) VALUES (?)";
        try(PreparedStatement ps = DBConnector.getPreparedStatement(query)){
            ps.setString(1, patikaName);
            int result = ps.executeUpdate();
            ps.close();
            return result != -1;
        }catch (Exception e){
            Helper.showMsg("Patika eklenirken bir hata oluştu!!!");
        }
        return false;
    }

    public static boolean update(int id, String name){

        String query = "UPDATE patika SET name=? WHERE id=?";
        try(PreparedStatement ps = DBConnector.getPreparedStatement(query)) {
            ps.setString(1,name);
            ps.setInt(2,id);
            int result = ps.executeUpdate();
            ps.close();
            return result != -1;
        }catch (Exception e){
            Helper.showMsg("Patika ismi güncellenirken bir hata oluştu");
        }
        return false;
    }
    public static boolean delete(int id){
        String query = "DELETE FROM patika WHERE id=?";
        try(PreparedStatement ps = DBConnector.getPreparedStatement(query)) {
            ps.setInt(1,id);
            int result = ps.executeUpdate();
            ps.close();
            for(Course course : Course.getListByPatika(id)){
                Course.delete(course.getId());
            }
            return result != -1;
        }catch (Exception e){
            Helper.showMsg("DeletePatika: Veritabanı Hatası");
        }
        return false;
    }
    public static Patika getFetch(int id){
        Patika obj = null;
        try(PreparedStatement ps = DBConnector.getPreparedStatement("SELECT * FROM patika WHERE id=?")){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                obj = new Patika(rs.getInt("id"),rs.getString("name"));
            }
            ps.close();
            rs.close();
        }catch (Exception e){

        }
        return obj;
    }
}
