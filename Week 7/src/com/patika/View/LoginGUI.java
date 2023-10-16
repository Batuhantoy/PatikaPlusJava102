package com.patika.View;

import com.patika.Helper.Config;
import com.patika.Helper.Helper;
import com.patika.Model.Educator;
import com.patika.Model.Operator;
import com.patika.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbottom;
    private JTextField login_username;
    private JPasswordField login_password;
    private JButton btn_login;
    private JButton btn_register;

    public LoginGUI(){
        add(wrapper);
        setSize(400,400);
        setLocation(Helper.screenCenterPoint("x",getSize()),Helper.screenCenterPoint("y",getSize()));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(Config.PROJECT_TITLE);
        setResizable(false);
        setVisible(true);


        btn_login.addActionListener(e -> {
            if(Helper.isFieldEmpty(login_username) || Helper.isFieldEmpty(login_password)){
                Helper.showMsg("fill");
            }else {
                User user = User.login(login_username.getText(),login_password.getText());
                if(user==null){
                    Helper.showMsg("Kullanıcı adınız veya şifreniz hatalı!!!");
                }else {
                    switch (user.getType()){
                        case "operator":
                            OperatorGUI operator = new OperatorGUI((Operator) user);
                            break;
                        case "educator":
                            EducatorGUI educator = new EducatorGUI((Educator) user);
                            break;
                        case "student":
                            StudentGUI student = new StudentGUI();
                            break;
                    }
                    dispose();
                }
            }
        });
        btn_register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                StudentRegisterGUI studentRegisterGUI = new StudentRegisterGUI();
            }
        });
    }

    public static void main(String[] args) {
        LoginGUI dfg = new LoginGUI();
    }

}
