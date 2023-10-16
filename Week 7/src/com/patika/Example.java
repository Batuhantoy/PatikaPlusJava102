package com.patika;

import javax.swing.*;
import java.awt.*;

public class Example extends JFrame{
    private JPanel wrapper;
    private JPanel wtop;
    private JPanel wbot;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;

    public Example(){
        for(UIManager.LookAndFeelInfo info :  UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        this.setContentPane(wrapper);//add(wrapper); ikiside dogru kullanım
        setSize(400,400);
        setTitle("Patika.dev");
        setVisible(true);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width)/2,
                    (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height)/2);

        btn_login.addActionListener(e->{
            if(fld_username.getText().isEmpty() || fld_password.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Tüm Alanları Doldurun","Hata",JOptionPane.INFORMATION_MESSAGE);
            }
        });


    }

}
