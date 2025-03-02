package dia.scient.health.care.labs;

import javax.swing.*;

public class RemovePatient extends JFrame {


    RemovePatient(){

        setFram();
    }

    public static void main(String[] args) {
        new RemovePatient();
    }



    private void setFram(){
        setSize(600,300);
        setLayout(null);
        setTitle("Remove Patient ");
        setBounds(300,100,600,300);

        setVisible(true);

    }

}
