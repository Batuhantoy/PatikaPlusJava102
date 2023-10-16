package com.patika.Model;

import com.patika.Helper.DBConnector;
import com.patika.Helper.Helper;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String type;

    public User() {
    }

    public User(int id, String name, String username, String password, String type) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.type = type;
    }

    public static ArrayList<User> getList(){
        ArrayList<User> userList = new ArrayList<>();
        User object;
        try {
            Statement statement = DBConnector.getConnectionInstance().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM user");
            while(rs.next()){
                object = new User();
                object.setId(rs.getInt("id"));
                object.setName(rs.getString("name"));
                object.setUsername(rs.getString("username"));
                object.setPassword(rs.getString("password"));
                object.setType(rs.getString("type"));
                userList.add(object);

            }
            statement.close();
            rs.close();
        } catch (SQLException e) {

        }
        return userList;
    }
    public static ArrayList<User> getEducatorList(){
        ArrayList<User> userList = new ArrayList<>();
        User object;
        try {
            Statement statement = DBConnector.getConnectionInstance().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM user WHERE type='educator'");
            while(rs.next()){
                object = new User();
                object.setId(rs.getInt("id"));
                object.setName(rs.getString("name"));
                object.setUsername(rs.getString("username"));
                object.setPassword(rs.getString("password"));
                object.setType(rs.getString("type"));
                userList.add(object);

            }
            statement.close();
            rs.close();
        } catch (SQLException e) {

        }
        return userList;
    }

    public static boolean add(String name,String username,String password,String type){
        if(getFetchByUsername(username)!=null){
            Helper.showMsg("username_exists");
            return false;
        }
        String query = "INSERT INTO user (name,username,password,type) VALUES (?,?,?,?)";
        try(PreparedStatement ps = DBConnector.getPreparedStatement(query)) {
            ps.setString(1,name);
            ps.setString(2,username);
            ps.setString(3,password);
            ps.setString(4,type);
            int result = ps.executeUpdate();
            ps.close();
            return result != -1;
        } catch (SQLException e) {
            Helper.showMsg("Veritabanı Hatası Oluştu!!!");
        }
        return false;
    }

    public static boolean update(int id, String name,String username,String password,String type){
        User selectedUser = getFetchByUsername(username);
        if(selectedUser!=null && selectedUser.getId()!=id){
            Helper.showMsg("username_exists");
            return false;
        }

        String query = "UPDATE user SET name=?,username=?,password=?,type=? WHERE id=?";
        PreparedStatement ps = DBConnector.getPreparedStatement(query);
        try {
            ps.setString(1,name);
            ps.setString(2,username);
            ps.setString(3,password);
            ps.setString(4,type);
            ps.setInt(5,id);
            int result = ps.executeUpdate();
            ps.close();
            return result != -1;
        } catch (SQLException e) {
            Helper.showMsg("Güncelleme yapılırken bir hata oldu!!!");
        }
        return false;
    }

    public static boolean deleteUser(int id){
        String query = "DELETE FROM user WHERE id=?";
        try {
            PreparedStatement ps = DBConnector.getPreparedStatement(query);
            ps.setInt(1,id);
            int result = ps.executeUpdate();
            ps.close();
            for(Course course : Course.getListByUser(id)){
                Course.delete(course.getId());
            }
            return result != -1;
        } catch (SQLException e) {
            Helper.showMsg("error");
        }
        return false;
    }
    public static ArrayList<User> searchUserList(String query){
        ArrayList<User> userList = new ArrayList<>();
        User object;
        try {
            Statement statement = DBConnector.getConnectionInstance().createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                object = new User();
                object.setId(rs.getInt("id"));
                object.setName(rs.getString("name"));
                object.setUsername(rs.getString("username"));
                object.setPassword(rs.getString("password"));
                object.setType(rs.getString("type"));
                userList.add(object);

            }
            statement.close();
            rs.close();
        } catch (SQLException e) {

        }
        return userList;
    }
    public static String searchQuery(String name, String username, String type){
        String query = "SELECT * FROM user WHERE username LIKE '%{{username}}%' AND name LIKE '%{{name}}%'";
        query = query.replace("{{username}}",username);
        query = query.replace("{{name}}",name);
        if(!type.isEmpty()){
            query += " AND type='{{type}}'";
            query = query.replace("{{type}}",type);
        }
        return query;

    }
    public static User login(String username, String password){
        User obj=null;
        String query = "SELECT * FROM user WHERE username=? AND password=?";
        try {
            PreparedStatement ps = DBConnector.getPreparedStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                switch (rs.getString("type")){
                    case "operator":
                        obj = new Operator();
                        break;
                    case "educator":
                        obj = new Educator();
                        break;
                    case "student":
                        obj = new Student();
                        break;
                    default:
                        obj = new User();
                        break;
                }
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUsername(rs.getString("username"));
                obj.setPassword(rs.getString("password"));
                obj.setType(rs.getString("type"));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("getFetchByUserName() hatası");
        }
        return obj;
    }
    public static User getFetchByUsername(String username){
        User obj=null;
        String query = "SELECT * FROM user WHERE username=?";
        try {
            PreparedStatement ps = DBConnector.getPreparedStatement(query);
            ps.setString(1,username);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                obj = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("type"));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("getFetchByUserName() hatası");
        }
        return obj;
    }
    public static User getFetchByID(int id){
        User obj=null;
        String query = "SELECT * FROM user WHERE id=?";
        try {
            PreparedStatement ps = DBConnector.getPreparedStatement(query);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                obj = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("type"));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Hata oluştu");
        }
        return obj;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
