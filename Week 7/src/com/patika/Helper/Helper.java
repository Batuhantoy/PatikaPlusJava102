package com.patika.Helper;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setLayout(){
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
            if("Nimbus".equals(info.getName())){
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    throw new RuntimeException(e.getMessage());
                }
                break;
            }
        }
    }

    public static void showMsg(String  str){
        optionPageTR();
        String msg;
        String title;
        switch (str){
            case "fill":
                msg="Lütfen Eksiksiz doldurdunuz!";
                title="Hata";
                break;
            case "success":
                msg="Ekleme işlemi başarılı";
                title="Sonuç";
                break;
            case "username_exists":
                msg="Bu kullanıcı adı kullanılmış!";
                title="Hata";
                break;
            case "error":
                msg="Bir hata oluştu!";
                title="Hata";
                break;
            default:
                msg=str;
                title="Mesaj";

        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void optionPageTR(){
        UIManager.put("OptionPane.okButtonText","Tamam");
        UIManager.put("OptionPane.yesButtonText","Evet");
        UIManager.put("OptionPane.noButtonText","Hayır");
    }
    public static int screenCenterPoint(String axis, Dimension size){
        int point;
        switch (axis){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width-size.width)/2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height-size.height)/2;
                break;
            default:
                point=0;
        }
        return point;
    }
    public static boolean confirm(String str){
        String msg;
        switch (str){
            case "sure":
                msg = "Bu işlemi gerçekleştirmek istediginizden emin misiniz?";
                break;
            default:
                msg=str;
                break;
        }
        return JOptionPane.showConfirmDialog(null,msg,"İşlemi yapmak istediginize emin misiniz?",JOptionPane.YES_NO_OPTION)==0;
    }

    public static boolean isFieldEmpty(JTextField field){
        return field.getText().isEmpty();
    }
}
