package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StudentRegisterGUI extends JFrame{

    private JPanel wrapper;
    private JTextField txt_name;
    private JTextField txt_username;
    private JTextField txt_password;
    private JButton btn_register;

    public StudentRegisterGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LoginGUI loginGUI = new LoginGUI();
            }
        });

        btn_register.addActionListener(e -> {
            if(Helper.isFieldEmpty(txt_name) || Helper.isFieldEmpty(txt_username) || Helper.isFieldEmpty(txt_password)){
                Helper.showMsg("fill");
            }else{
                if(User.add(txt_name.getText(),txt_username.getText(),txt_password.getText(),"student")){
                    Helper.showMsg("success");
                    dispose();

                }
            }
        });

    }

}
