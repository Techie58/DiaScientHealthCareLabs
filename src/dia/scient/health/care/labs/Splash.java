package dia.scient.health.care.labs;


import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {

    Splash (){


        setBgPic();
        setFram();
        try {
            Thread.sleep(4000);
            setVisible(false);
            new Login();

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static void main(String [] args){

        new Splash();

    }





    public void setFram(){

        setSize(1120,600);
        setLayout(null);
        setVisible(true);
        setLocation(150,50);
        setTitle("Dia Scient Health Care");


    }

    public void setBgPic(){
        ImageIcon iIcon= new ImageIcon(ClassLoader.getSystemResource("assets/bg_images/splash_bg.png"));
        Image imgResize=iIcon.getImage().getScaledInstance(1120,600,Image.SCALE_DEFAULT);
        ImageIcon iIcon1=new ImageIcon(imgResize);
        JLabel label=new JLabel(iIcon1);
        label.setBounds(0,0,1120,600);
        add(label);

    }

}

