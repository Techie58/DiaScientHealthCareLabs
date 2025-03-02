package dia.scient.health.care.labs;

import javax.swing.*;

public class ViewPatient extends JFrame {

    ViewPatient(){

        setFram();
    }


    public static void main(String[] args) {
        new ViewPatient();
    }



    private void setFram(){
        setSize(600,300);
        setLayout(null);
        setTitle("View Patient Details");
        setBounds(300,100,600,300);

        setVisible(true);

    }

}
