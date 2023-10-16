package com.patika.Model;

import com.patika.Helper.DBConnector;
import com.patika.Helper.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Content {
    private int id;
    private Course course;
    private String title;
    private String explanation;
    private String link;
    private String quizQuestions;

    public Content(int id, int course_id, String title, String explanation, String link, String quizQuestions) {
        this.id = id;
        this.course = Course.getFetch(course_id);
        this.title = title;
        this.explanation = explanation;
        this.link = link;
        this.quizQuestions = quizQuestions;
    }

    public static boolean add(int courseID,String title,String explanation,String link, String quizQuestions){
        try(PreparedStatement ps = DBConnector.getPreparedStatement("INSERT INTO content (course_id,title,explanation,link,quiz_questions) VALUES(?,?,?,?,?)")) {
            ps.setInt(1,courseID);
            ps.setString(2,title);
            ps.setString(3,explanation);
            ps.setString(4,link);
            ps.setString(5,quizQuestions);
            int result = ps.executeUpdate();
            ps.close();
            return result !=-1;

        }catch (Exception e){
            Helper.showMsg("Veritabanı Hatası!!!");
        }
        return false;
    }
    public static boolean update(int contentID,String title,String explanation,String link, String quizQuestions){
        try(PreparedStatement ps = DBConnector.getPreparedStatement("UPDATE content SET title=?,explanation=?,link=?,quiz_questions=? WHERE id=?")) {
            ps.setString(1,title);
            ps.setString(2,explanation);
            ps.setString(3,link);
            ps.setString(4,quizQuestions);
            ps.setInt(5,contentID);
            int result = ps.executeUpdate();
            ps.close();
            return result !=-1;

        }catch (Exception e){
            Helper.showMsg("Veritabanı Hatası!!!");
        }
        return false;
    }
    public static boolean delete(int ID){
        try(PreparedStatement ps = DBConnector.getPreparedStatement("DELETE FROM content WHERE id=?")) {
            ps.setInt(1,ID);
            int result = ps.executeUpdate();
            ps.close();
            return result != -1;
        }catch (Exception e){
            Helper.showMsg("Veri Tabanı Hatası!!!");
        }
        return false;
    }

    public static ArrayList<Content> getList(){
        ArrayList<Content> list = new ArrayList<>();
        try(Statement st = DBConnector.getConnectionInstance().createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM content");
            while (rs.next()){
                list.add(new Content(rs.getInt("id"),rs.getInt("course_id"),
                                     rs.getString("title"),rs.getString("explanation"),
                                     rs.getString("link"),rs.getString("quiz_questions")));
            }
        }catch (Exception e){
            Helper.showMsg("Veritabanı Hatası!!!");
        }
        return list;
    }
    public static ArrayList<Content> getList(int courseID){
        ArrayList<Content> list = new ArrayList<>();
        try(Statement st = DBConnector.getConnectionInstance().createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM content WHERE course_id="+courseID);
            while (rs.next()){
                list.add(new Content(rs.getInt("id"),rs.getInt("course_id"),
                        rs.getString("title"),rs.getString("explanation"),
                        rs.getString("link"),rs.getString("quiz_questions")));
            }
        }catch (Exception e){
            Helper.showMsg("Veritabanı Hatası!!!");
        }
        return list;
    }
    public static ArrayList<Content> searchContent(int courseID,String title){
        ArrayList<Content> list = new ArrayList<>();
        String query = "SELECT * FROM content WHERE course_id LIKE '%{{course_id}}%' AND title LIKE '%{{title}}%'";
        query = query.replace("{{course_id}}",String.valueOf(courseID));
        query = query.replace("{{title}}",title);

        Content content;
        try(Statement st = DBConnector.getConnectionInstance().createStatement()) {
            ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                list.add(new Content(rs.getInt("id"),rs.getInt("course_id"),
                                     rs.getString("title"),rs.getString("explanation"),
                                     rs.getString("link"),rs.getString("quiz_questions")));
            }
            st.close();
            rs.close();
        }catch (Exception e){
            Helper.showMsg("Veritabanı Hatası!!");
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(String quizQuestions) {
        this.quizQuestions = quizQuestions;
    }


}
