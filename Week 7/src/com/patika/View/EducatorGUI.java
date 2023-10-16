package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Helper.Item;
import com.patika.Model.Content;
import com.patika.Model.Course;
import com.patika.Model.Educator;
import com.patika.Model.Patika;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EducatorGUI extends JFrame{
    private JPanel wrapper;
    private JTable tbl_educator_course_list;
    private DefaultTableModel mdl_educator_course_list;
    private DefaultTableModel mdl_educator_content_list;
    private Object[] row_educator_course_list;
    private Object[] row_educator_content_list;

    private JLabel lbl_educator_name;
    private JComboBox cmb_patika_combobox;
    private JTextField txt_course_name;
    private JTextField txt_language;
    private JButton btn_add_course;
    private JTabbedPane tab_educator;
    private JComboBox cmb_course_list;
    private JTable tbl_content_list;
    private JScrollPane scrl_content_list;
    private JTextField txt_title;
    private JTextField txt_explanation;
    private JTextField txt_link;
    private JTextField txt_quiz_questions;
    private JButton btn_addContent;
    private JTextField txt_search_title;
    private JButton btn_searchContent;
    private Educator educator;

    public EducatorGUI(Educator educator){
        this.educator = educator;
        add(wrapper);
        setSize(800,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        lbl_educator_name.setText("Hoşgeldiniz : "+educator.getName());

        // Course List - START
        Object[] col_educator_course_list = {"ID","Patika","Ders","Dil"};
        mdl_educator_course_list = new DefaultTableModel();
        mdl_educator_course_list.setColumnIdentifiers(col_educator_course_list);
        row_educator_course_list = new Object[col_educator_course_list.length];
        tbl_educator_course_list.setModel(mdl_educator_course_list);
        loadCourseList();
        loadPatikaComboBox();
        // Course List - END


        // Content List - START
        mdl_educator_content_list = new DefaultTableModel();
        mdl_educator_content_list.setColumnIdentifiers(new Object[]{"ID","Kurs İsmi","Başlık","Açıklama","Link","Quiz Soruları"});
        row_educator_content_list = new Object[6];
        tbl_content_list.setModel(mdl_educator_content_list);
        loadCoursePatikaComboBox();
        loadContentList(((Item)cmb_course_list.getItemAt(0)).getKey());
        JPopupMenu contentPopupMenu = new JPopupMenu();
        JMenuItem updateContent = new JMenuItem("Güncelle");
        JMenuItem deleteContent = new JMenuItem("Sil");
        contentPopupMenu.add(updateContent);
        contentPopupMenu.add(deleteContent);
        tbl_content_list.setComponentPopupMenu(contentPopupMenu);
        updateContent.addActionListener(e -> {
            //int selectedContentID = (int) tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0);
            //System.out.println(selectedContentID);
            tbl_content_list.getCellEditor().stopCellEditing();
            if (Content.update(
                    (int) tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0),
                    (String) tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),2),
                    (String) tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),3),
                    (String) tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),4),
                    (String) tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),5))){
                loadContentList(((Item)cmb_course_list.getSelectedItem()).getKey());
                Helper.showMsg("Güncelleme işlemi başarılı");

            }
        });
        deleteContent.addActionListener(e -> {
            if(Content.delete((int) tbl_content_list.getValueAt(tbl_content_list.getSelectedRow(),0))){
                loadContentList(((Item) cmb_course_list.getSelectedItem()).getKey());
                Helper.showMsg("Silme işlemi başarılı");
            }
        }); //SİLME VE GÜNCELLEME BİTTİ -----------------------------------------------------------------------------
            //ancak sol tık yapmadan otomatik select yapma olayını geliştir

        // Content List - END

        btn_add_course.addActionListener(e -> {
            if(Helper.isFieldEmpty(txt_course_name) || Helper.isFieldEmpty(txt_language)){
                Helper.showMsg("fill");
            }else{
                Item selectedPatika = (Item)cmb_patika_combobox.getSelectedItem();
                if(Course.add(educator.getId(),selectedPatika.getKey(),txt_course_name.getText(),txt_language.getText())){
                    loadCourseList();
                    Helper.showMsg("success");

                }
            }
        });

        cmb_course_list.addActionListener(e -> {
            Item selectedCourse = (Item) cmb_course_list.getSelectedItem();
            assert selectedCourse != null;
            loadContentList(selectedCourse.getKey());
        });
        btn_addContent.addActionListener(e -> {
            if(Helper.isFieldEmpty(txt_title) || Helper.isFieldEmpty(txt_explanation) || Helper.isFieldEmpty(txt_link) || Helper.isFieldEmpty(txt_quiz_questions)){
                Helper.showMsg("fill");
            }else{
                int courseID = ((Item)cmb_course_list.getSelectedItem()).getKey();
                if(Content.add(courseID,txt_title.getText(),txt_explanation.getText(),
                               txt_link.getText(),txt_quiz_questions.getText())){
                    loadContentList(courseID);
                    Helper.showMsg("success");
                }
            }
        });

        btn_searchContent.addActionListener(e -> {
            loadContentList(Content.searchContent(((Item)cmb_course_list.getSelectedItem()).getKey(),txt_search_title.getText()));
        });
    }


    private void loadCourseList() {
        DefaultTableModel db = (DefaultTableModel) tbl_educator_course_list.getModel();
        db.setRowCount(0);
        for(Course course : Course.getListByUser(educator.getId())){
            row_educator_course_list[0] = course.getEducator_id();
            row_educator_course_list[1] = course.getPatika().getName();
            row_educator_course_list[2] = course.getName();
            row_educator_course_list[3] = course.getLanguage();
            mdl_educator_course_list.addRow(row_educator_course_list);
        }
    }
    // Patikaları comboya ekliyoduk
    private void loadPatikaComboBox(){ //içerik bomboboxının içini doldurduk, seçilene göre dersler listelenecek
        cmb_patika_combobox.removeAllItems();
        for(Patika patika : Patika.getList()){
            cmb_patika_combobox.addItem(new Item(patika.getId(),patika.getName()));
        }
    }

    /*private void loadContentList(){
        DefaultTableModel db = (DefaultTableModel) tbl_content_list.getModel();
        db.setRowCount(0);
        for(Content content : Content.getList()){
            row_educator_content_list[0] = content.getId();
            row_educator_content_list[1] = content.getCourse().getName();
            row_educator_content_list[2] = content.getTitle();
            row_educator_content_list[3] = content.getExplanation();
            row_educator_content_list[4] = content.getLink();
            row_educator_content_list[5] = content.getQuizQuestions();
            mdl_educator_content_list.addRow(row_educator_content_list);
        }
    }*/
    private void loadContentList(int id){
        DefaultTableModel db = (DefaultTableModel) tbl_content_list.getModel();
        db.setRowCount(0);
        for(Content content : Content.getList(id)){
            row_educator_content_list[0] = content.getId();
            row_educator_content_list[1] = content.getCourse().getName();
            row_educator_content_list[2] = content.getTitle();
            row_educator_content_list[3] = content.getExplanation();
            row_educator_content_list[4] = content.getLink();
            row_educator_content_list[5] = content.getQuizQuestions();
            mdl_educator_content_list.addRow(row_educator_content_list);
        }
    }
    private void loadContentList(ArrayList<Content> list){
        DefaultTableModel db = (DefaultTableModel) tbl_content_list.getModel();
        db.setRowCount(0);
        for(Content content : list){
            row_educator_content_list[0] = content.getId();
            row_educator_content_list[1] = content.getCourse().getName();
            row_educator_content_list[2] = content.getTitle();
            row_educator_content_list[3] = content.getExplanation();
            row_educator_content_list[4] = content.getLink();
            row_educator_content_list[5] = content.getQuizQuestions();
            mdl_educator_content_list.addRow(row_educator_content_list);
        }
    }
    private void loadCoursePatikaComboBox(){
        cmb_course_list.removeAllItems();
        for(Course course : Course.getList()){
            cmb_course_list.addItem(new Item(course.getId(),course.getName()));
        }
    }

    public static void main(String[] args) {
        EducatorGUI u = new EducatorGUI(new Educator(1,"Batuhan Toy","batuhantoy","1234","educator"));
    }

}
