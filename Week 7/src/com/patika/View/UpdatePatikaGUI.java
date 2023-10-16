package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Patika;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePatikaGUI extends JFrame{
    private JPanel wrapper;
    private JTextField input_update_patika_name;
    private JButton btn_update_patika;
    private Patika patika;

    public UpdatePatikaGUI(Patika patika) {
        this.patika = patika;
        add(wrapper);
        setSize(400, 200);
        setLocation(Helper.screenCenterPoint("x", getSize()), Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setVisible(true);
        input_update_patika_name.setText(patika.getName());


        btn_update_patika.addActionListener(e -> {
            if(Helper.isFieldEmpty(input_update_patika_name)){
                Helper.showMsg("fill");
            }else{
                if(Patika.update(patika.getId(),input_update_patika_name.getText())){
                    Helper.showMsg("Güncelleme işlemi başarılı");
                }
                dispose();
            }
        });
    }
}
