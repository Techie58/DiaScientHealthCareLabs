package dia.scient.health.care.labs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main extends JFrame {

    Main(){

        setImg();
        setBtn();
        setFram();
    }

    public static void main(String[] args) {
        new Main();
    }

    public void setFram(){

        Image titleBarImg=Toolkit.getDefaultToolkit().getImage("assets/logos/logo.png");
        setIconImage(titleBarImg);

        setLayout(null);
        setSize(1000,500);
        setLocation(150,50);
        setTitle("Dia Scient Labs");
        getContentPane().setBackground(Color.WHITE);



        setVisible(true);


    }

    private void setBtn(){
        JButton addPatient,viewPatient,removePatient,closeMain;


        addPatient= new JButton("ADD PATIENT");
        addPatient.setBounds(30,20,150,50);
        addPatient.setBackground(Color.BLACK);
        addPatient.setForeground(Color.white);
        //        addPatient.setFont();
        add(addPatient);

        addPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new AddPatient();
                AddPatient addPatient=new AddPatient();
                addPatient.setBtn("SAVE",null);
                addPatient.setLable("Add Patient Details");
                addPatient.setFram("Add Patient");
                setVisible(false);
            }
        });

        viewPatient = new JButton("View Patient");
        viewPatient.setBounds(30,80,150,50);
        viewPatient.setBackground(Color.BLACK);
        viewPatient.setForeground(Color.white);
        add(viewPatient);

        viewPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewPatient();
                setVisible(false);
            }
        });

        removePatient = new JButton("REMOVE PATIENT");
        removePatient.setBounds(30,140,150,50);
        removePatient.setBackground(Color.BLACK);
        removePatient.setForeground(Color.white);
        add(removePatient);

        removePatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RemovePatient();
                setVisible(false);

            }
        });



        closeMain = new JButton("CLOSE");
        closeMain.setBounds(30,200,150,50);
        closeMain.setBackground(Color.BLACK);
        closeMain.setForeground(Color.white);
        closeMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(20);
            }
        });
        add(closeMain);

    }

    private void setImg(){
        ImageIcon microScopImg=new ImageIcon(ClassLoader.getSystemResource("assets/logos/microscop.png"));
        Image resizeMicroScopImg=microScopImg.getImage().getScaledInstance(400,450,Image.SCALE_SMOOTH);
        ImageIcon getReziseMicroImg=new ImageIcon(resizeMicroScopImg);
        JLabel microImgLable=new JLabel(getReziseMicroImg);
        microImgLable.setBounds(600,0,400,450);
        add(microImgLable);

        ImageIcon bgLogo= new ImageIcon(ClassLoader.getSystemResource("assets/logos/logo.png"));
        Image reSizeBgImg= bgLogo.getImage().getScaledInstance(450,202,Image.SCALE_DEFAULT);
        ImageIcon bgLogoReGet=new ImageIcon(reSizeBgImg);
        JLabel bgLogoLable= new JLabel(bgLogoReGet);
        bgLogoLable.setBounds(200,20,450,202);
        add(bgLogoLable);

        ImageIcon brandLogo= new ImageIcon(ClassLoader.getSystemResource("assets/logos/brandLogo.gif"));
        Image reSizeBrandLogo= brandLogo.getImage().getScaledInstance(500,270,Image.SCALE_DEFAULT);
        ImageIcon brandLogoReGet=new ImageIcon(reSizeBrandLogo);
        JLabel branLogoLable= new JLabel(brandLogoReGet);
        branLogoLable.setBounds(30,250,500,270);
        add(branLogoLable);
    }

}
