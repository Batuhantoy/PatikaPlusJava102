package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Patika;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentGUI extends JFrame{
    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JTable tbl_patika_list;
    private DefaultTableModel mdl_patika_list;
    private Object[] row_patika_list;

    public StudentGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        // Patika List - START
        Object[] col_patika_list = {"ID","Patika"};
        mdl_patika_list = new DefaultTableModel();
        mdl_patika_list.setColumnIdentifiers(col_patika_list);
        row_patika_list = new Object[col_patika_list.length];
        tbl_patika_list.setModel(mdl_patika_list);
        loadPatikaList();


        tbl_patika_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //JTable table = (JTable) e.getSource();
                Point point = e.getPoint();
                int row = tbl_patika_list.rowAtPoint(point);
                if(e.getClickCount()==2 && tbl_patika_list.getSelectedRow() != -1){
                    // bir row a çift tıklanınca bir şey yapacağız ama ne yapıcaz üşendiğin için burayı bıraktın--------
                }
            }
        });


        // Patika List - END

    }
    private void loadPatikaList(){
        DefaultTableModel db = (DefaultTableModel)tbl_patika_list.getModel();
        db.setRowCount(0);
        for(Patika p : Patika.getList()){
            row_patika_list[0] = p.getId();
            row_patika_list[1] = p.getName();
            mdl_patika_list.addRow(row_patika_list);
        }
    }

    public static void main(String[] args) {
        StudentGUI s = new StudentGUI();
    }
}