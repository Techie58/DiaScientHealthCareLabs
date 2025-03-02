package dia.scient.health.care.labs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    Main(){

        setFram();
        setBtn();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void setFram(){

        setLayout(null);
        setSize(1200,600);
        setLocation(150,50);
        setTitle("Dia Scient Labs");

        setVisible(true);


    }

    private void setBtn(){
        JButton addPatient,viewPatient,removePatient;


        addPatient= new JButton("ADD PATIENT");
        addPatient.setBounds(525,100,150,50);
        addPatient.setBackground(Color.BLACK);
        addPatient.setForeground(Color.white);
        //        addPatient.setFont();
        add(addPatient);

        addPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        viewPatient = new JButton("View Patient");
        viewPatient.setBounds(525,200,150,50);
        viewPatient.setBackground(Color.BLACK);
        viewPatient.setForeground(Color.white);
        add(viewPatient);

        viewPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        removePatient = new JButton("REMOVE PATIENT");
        removePatient.setBounds(525,300,150,50);
        removePatient.setBackground(Color.BLACK);
        removePatient.setForeground(Color.white);
        add(removePatient);

        removePatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });




    }


}
