package com.patika.Model;

import com.patika.Helper.DBConnector;
import com.patika.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Course {
    private int id;
    private int educator_id;
    private int patika_id;
    private String name;
    private String language;

    private Patika patika;
    private User educator;

    public Course(int id, int educator_id, int patika_id, String name, String language) {
        this.id = id;
        this.educator_id = educator_id;
        this.patika_id = patika_id;
        this.name = name;
        this.language = language;
        this.educator = User.getFetchByID(educator_id);
        this.patika = Patika.getFetch(patika_id);
    }

    public static boolean add(int educator_id, int patika_id, String name, String language){
        String query = "INSERT INTO course (educator_id,patika_id,name,language) VALUES(?,?,?,?)";
        try(PreparedStatement ps = DBConnector.getPreparedStatement(query)) {
            ps.setInt(1,educator_id);
            ps.setInt(2,patika_id);
            ps.setString(3,name);
            ps.setString(4,language);
            int result = ps.executeUpdate();
            return result != -1;

        }catch (Exception e){
            Helper.showMsg("Veritabanı Hatası!!!");
        }
        return false;
    }

    public static boolean delete(int id){
        String query = "DELETE FROM course WHERE id=?";
        try {
            PreparedStatement ps = DBConnector.getPreparedStatement(query);
            ps.setInt(1,id);
            int result = ps.executeUpdate();
            ps.close();
            return result != -1;
        } catch (SQLException e) {
            Helper.showMsg("error");
        }
        return false;
    }
    public static ArrayList<Course> getList() {
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            Statement st = DBConnector.getConnectionInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course");
            while (rs.next()){
                courseList.add(new Course(rs.getInt("id"),rs.getInt("educator_id"),
                                          rs.getInt("patika_id"),rs.getString("name"),
                                          rs.getString("language")));
            }
        } catch (SQLException e) {
            Helper.showMsg("Veritabanı Hatası!!");
        }
        return courseList;
    }

    public static ArrayList<Course> getListByUser(int educator_id) {
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            Statement st = DBConnector.getConnectionInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE educator_id="+educator_id);
            while (rs.next()){
                courseList.add(new Course(rs.getInt("id"),rs.getInt("educator_id"),
                        rs.getInt("patika_id"),rs.getString("name"),
                        rs.getString("language")));
            }
        } catch (SQLException e) {
            Helper.showMsg("Veritabanı Hatası!!");
        }
        return courseList;
    }
    public static Course getFetch(int id){
        Course obj = null;
        try(PreparedStatement ps = DBConnector.getPreparedStatement("SELECT * FROM course WHERE id=?")){
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                obj = new Course(rs.getInt("id"),rs.getInt("educator_id"),
                                 rs.getInt("patika_id"),rs.getString("name"),rs.getString("language"));
            }
            ps.close();
            rs.close();
        }catch (Exception e){
            Helper.showMsg("Veritabanı Hatası!!!");
        }
        return obj;
    }
    public static ArrayList<Course> getListByPatika(int patika_id) {
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            Statement st = DBConnector.getConnectionInstance().createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM course WHERE patika_id="+patika_id);
            while (rs.next()){
                courseList.add(new Course(rs.getInt("id"),rs.getInt("educator_id"),
                        rs.getInt("patika_id"),rs.getString("name"),
                        rs.getString("language")));
            }
        } catch (SQLException e) {
            Helper.showMsg("Veritabanı Hatası!!");
        }
        return courseList;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEducator_id() {
        return educator_id;
    }

    public void setEducator_id(int educator_id) {
        this.educator_id = educator_id;
    }

    public int getPatika_id() {
        return patika_id;
    }

    public void setPatika_id(int patika_id) {
        this.patika_id = patika_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Patika getPatika() {
        return patika;
    }

    public void setPatika(Patika patika) {
        this.patika = patika;
    }

    public User getEducator() {
        return educator;
    }

    public void setEducator(User educator) {
        this.educator = educator;
    }
}
