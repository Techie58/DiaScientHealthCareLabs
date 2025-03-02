package dia.scient.health.care.labs;

import javax.swing.*;

public class AddPatient extends JFrame {

    AddPatient(){
//        Main main=new Main();

        setFram();

    }

    public static void main(String[] args) {

        new AddPatient();
    }

    private void setFram(){
        setSize(600,300);
        setLayout(null);
        setTitle("Add Patient");
        setBounds(300,100,600,300);

        setVisible(true);

    }

}
