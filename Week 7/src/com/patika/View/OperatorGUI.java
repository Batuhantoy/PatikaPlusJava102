package com.patika.View;

import com.patika.Helper.*;
import com.patika.Model.Course;
import com.patika.Model.Operator;
import com.patika.Model.Patika;
import com.patika.Model.User;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;


// Ders güncelleme ve silme işlemlerini yapabilirsin(güncellemeyi bir dersi seçtikten sonra textboklar doldurulup
// Ekle butonuna bastıktan sonra da güncelleme saglanabilir
public class OperatorGUI extends JFrame {

    private JPanel wrapper;
    private JTabbedPane tab_operator;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JPanel pnl_user_list;
    private JScrollPane scrl_user_list;
    private JTable tbl_user_list;
    private JPanel pnl_user_form;
    private JTextField input_name;
    private JTextField input_username;
    private JTextField input_password;
    private JComboBox input_cmb_usertype;
    private JButton btn_adduser;
    private JTextField input_deleteuserbyID;
    private JButton btn_delete_user;
    private JTextField input_searchbar_name;
    private JTextField input_searchbar_username;
    private JComboBox input_cmb_search_type;
    private JButton btn_search;
    private JPanel pnl_patika_list;
    private JScrollPane scrl_patika_list;
    private JTable tbl_patika_list;
    private JTextField input_patika_name;
    private JButton btn_patika_add;
    private JPanel pnl_course_list;
    private JScrollPane scrl_course_list;
    private JTable tbl_course_list;
    private JPanel pnl_course_add;
    private JTextField input_course_name;
    private JTextField input_course_lang;
    private JComboBox cmb_course_patika;
    private JComboBox cmb_course_educator;
    private JButton btn_course_add;
    private final DefaultTableModel mdl_user_list;
    private final Object[] row_user_list;
    private final Operator operator;
    private final DefaultTableModel mdl_patika_list;
    private final Object[] row_patika_list;
    private JPopupMenu patikaMenu;
    private DefaultTableModel mdl_course_list;//bunu tanımlayıp kaldım 11:10
    private Object[] row_course_list;

    public OperatorGUI(Operator operator){
        this.operator=operator;
        add(wrapper);
        setSize(1000,500);
        setLocation(Helper.screenCenterPoint("x",getSize()),
                    Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);


        lbl_welcome.setText("Hoşgeldiniz : "+operator.getName());

        //UserList - START
        mdl_user_list = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==0){// 0 ıncı column un düzeltilemez editlenemez olmasını sagladık
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        Object[] col_user_list = {"ID","Ad Soyad","Kullanıcı adı","Şifre","Üyelik Tipi"};
        mdl_user_list.setColumnIdentifiers(col_user_list);
        row_user_list = new Object[col_user_list.length];
        loadUserModel();

        tbl_user_list.setModel(mdl_user_list);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);

        tbl_user_list.getSelectionModel().addListSelectionListener(e ->{
            try{
                String selected_id = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString();
                input_deleteuserbyID.setText(selected_id);
            }catch (Exception ex){

            }
        });

       tbl_user_list.getModel().addTableModelListener(e -> {
           if(e.getType() == TableModelEvent.UPDATE){
               int user_id = Integer.parseInt(tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0).toString());
               String name = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 1).toString();
               String username = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 2).toString();
               String password = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 3).toString();
               String type = tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 4).toString();

               if(User.update(user_id,name,username,password,type)){
                   Helper.showMsg("Güncelleme işlemi başarılı");
               }
               loadUserModel();
               loadEducatorComboBox();
               loadCourseModel();
           }
        });
        //UserList - END

        //PatikaList - START
        patikaMenu = new JPopupMenu();
        JMenuItem updateMenu = new JMenuItem("Güncelle");
        JMenuItem deleteMenu = new JMenuItem("Sil");
        patikaMenu.add(updateMenu);
        patikaMenu.add(deleteMenu);

        updateMenu.addActionListener(e -> {
            int selectedRowId = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
            UpdatePatikaGUI updatePatikaGUI = new UpdatePatikaGUI(Patika.getFetch(selectedRowId));
            updatePatikaGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPatikaModel();
                    loadPatikaComboBox();
                    loadCourseModel();
                }
            });
        });

        deleteMenu.addActionListener(e -> {
            int selectedRowId = Integer.parseInt(tbl_patika_list.getValueAt(tbl_patika_list.getSelectedRow(),0).toString());
            if(Helper.confirm("sure") && Patika.delete(selectedRowId)){
                loadPatikaModel();
                loadPatikaComboBox();
                loadCourseModel();
                Helper.showMsg("Patika silme işlemi başarılı");
            }
        });

       mdl_patika_list = new DefaultTableModel();
       Object[] col_patika_list = {"ID","Patika Adı"};
       mdl_patika_list.setColumnIdentifiers(col_patika_list);
       row_patika_list = new Object[col_patika_list.length];
       tbl_patika_list.setModel(mdl_patika_list);
       tbl_patika_list.setComponentPopupMenu(patikaMenu);
       tbl_patika_list.getTableHeader().setReorderingAllowed(false);
       tbl_patika_list.getColumnModel().getColumn(0).setMaxWidth(70);
       tbl_patika_list.addMouseListener(new MouseAdapter() {
           @Override
           public void mousePressed(MouseEvent e) {
               Point point = e.getPoint();
               int selectedRow = tbl_patika_list.rowAtPoint(point);
               tbl_patika_list.setRowSelectionInterval(selectedRow, selectedRow);
           }
       });
       loadPatikaModel();
       //PatikaList - END

        // CourseList - Start
        mdl_course_list = new DefaultTableModel();
        Object[] col_course_list = {"ID","Ders Adı","Programlama Dili","Patika","Eğitmen"};
        mdl_course_list.setColumnIdentifiers(col_course_list);
        row_course_list = new Object[col_course_list.length];

        tbl_course_list.setModel(mdl_course_list);
        tbl_course_list.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_course_list.getTableHeader().setReorderingAllowed(false);
        loadCourseModel();

        loadPatikaComboBox();
        loadEducatorComboBox();
        // CourseList - End

        btn_adduser.addActionListener(e -> {
            if(Helper.isFieldEmpty(input_name) || Helper.isFieldEmpty(input_username) || Helper.isFieldEmpty(input_password)){
                Helper.showMsg("fill");
            }else{
                if(User.add(input_name.getText(),input_username.getText(),input_password.getText(),
                        input_cmb_usertype.getSelectedItem().toString())){
                    loadUserModel();
                    input_name.setText(null);
                    input_username.setText(null);
                    input_password.setText(null);
                    if(input_cmb_usertype.getSelectedItem()=="educator"){
                        loadEducatorComboBox();
                    }
                    Helper.showMsg("success");

                }
            }//Son eklenen verinin id sini "SELECT last_insert_rowid()" ile çekip o verinin bilgilerini row olarak da ekleyebiliriz
             //ancak gereksiz veritabanı işlemi olur gibi???
        });

        btn_delete_user.addActionListener(e -> {
            if(Helper.isFieldEmpty(input_deleteuserbyID)){
                Helper.showMsg("fill");
            }else{
                if(Helper.confirm("sure") && User.deleteUser(Integer.parseInt(input_deleteuserbyID.getText()))){
                    loadUserModel();
                    loadEducatorComboBox();
                    loadCourseModel();
                    input_deleteuserbyID.setText(null);
                    Helper.showMsg("Silme işlemi başarılı");
                    //((DefaultTableModel)tbl_user_list.getModel()).removeRow(tbl_user_list.getSelectedRow());
                }
            }
        });
        btn_search.addActionListener(e -> {
            String name = input_searchbar_name.getText();
            String username = input_searchbar_username.getText();
            String type = input_cmb_search_type.getSelectedItem().toString();

            ArrayList<User> searchingUser = User.searchUserList(User.searchQuery(name,username,type));
            loadUserModel(searchingUser);
        });
        btn_logout.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });
        btn_patika_add.addActionListener(e -> {
            if(Helper.isFieldEmpty(input_patika_name)){
                Helper.showMsg("fill");
            }else {
                if(Patika.add(input_patika_name.getText())){
                    loadPatikaModel();
                    loadPatikaComboBox();
                    input_patika_name.setText(null);
                    Helper.showMsg("success");
                }
            }

        });

        btn_course_add.addActionListener(e -> {
            Item patikaItem = (Item) cmb_course_patika.getSelectedItem();
            Item educatorItem = (Item) cmb_course_educator.getSelectedItem();
            if(Helper.isFieldEmpty(input_course_name) || Helper.isFieldEmpty(input_course_lang)){
                Helper.showMsg("fill");
            }else{
                assert patikaItem != null;
                assert educatorItem != null;
                if(Course.add(educatorItem.getKey(),patikaItem.getKey(),input_course_name.getText(),input_course_lang.getText())){
                    input_course_lang.setText(null);
                    input_course_name.setText(null);
                    Helper.showMsg("success");
                    loadCourseModel();
                }
            }
        });
    }

    private void loadCourseModel() {
        DefaultTableModel clearModel = (DefaultTableModel) tbl_course_list.getModel();
        clearModel.setRowCount(0);
        for (Course obj : Course.getList()){
            row_course_list[0] = obj.getId();
            row_course_list[1] = obj.getName();
            row_course_list[2] = obj.getLanguage();
            row_course_list[3] = obj.getPatika().getName();
            row_course_list[4] = obj.getEducator().getName();
            mdl_course_list.addRow(row_course_list);
        }

    }

    private void loadPatikaModel() {
        DefaultTableModel clear = (DefaultTableModel) tbl_patika_list.getModel();
        clear.setRowCount(0);
        for(Patika patika : Patika.getList()){
            int i =0;
            row_patika_list[i++] = patika.getId();
            row_patika_list[i++] = patika.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public void loadUserModel(){// Sağlıklı bir yöntem degil gibi gereksiz tekrardan işlemler yapıyor
        //Tablonun liste modelini getirip, temiz(boş) bir modele atıyoruz sonra setRowCount() ile tüm row ları siliyoruz
        //Sonuç olarak tablo her seferinde yenilenmiş oluyor
        DefaultTableModel df = (DefaultTableModel) tbl_user_list.getModel();
        df.setRowCount(0);
        for(User user : User.getList()){
            int i=0;
            row_user_list[i++] = user.getId();
            row_user_list[i++] = user.getName();
            row_user_list[i++] = user.getUsername();
            row_user_list[i++] = user.getPassword();
            row_user_list[i++] = user.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }
    public void loadUserModel(ArrayList<User> list){
        DefaultTableModel df = (DefaultTableModel) tbl_user_list.getModel();
        df.setRowCount(0);
        for(User user : list){
            int i=0;
            row_user_list[i++] = user.getId();
            row_user_list[i++] = user.getName();
            row_user_list[i++] = user.getUsername();
            row_user_list[i++] = user.getPassword();
            row_user_list[i++] = user.getType();
            mdl_user_list.addRow(row_user_list);
        }
    }

    public void loadPatikaComboBox(){
        cmb_course_patika.removeAllItems();
        for(Patika obj : Patika.getList()){
            cmb_course_patika.addItem(new Item(obj.getId(),obj.getName()));
        }
    }

    public void loadEducatorComboBox(){
        cmb_course_educator.removeAllItems();
        for (User obj : User.getEducatorList()){
            cmb_course_educator.addItem(new Item(obj.getId(),obj.getName()));
        }
    }
    public static void main(String[] args) {
        Helper.setLayout();
        Operator o = new Operator();
        o.setId(1);
        o.setName("Batuhan Toy");
        o.setUsername("bth");
        o.setPassword("456847");
        o.setType("operator");
        OperatorGUI op = new OperatorGUI(o);
    }
}
