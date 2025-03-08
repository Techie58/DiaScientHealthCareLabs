package dia.scient.health.care.labs;

import javax.swing.*;

public class UpdatePatient extends JFrame {

    AddPatient aPatient=new AddPatient();

    UpdatePatient(String  searchChoice) {

        aPatient.setLable("UPDATE PATIENT");
        aPatient.setBtn("UPDATE",Integer.parseInt(searchChoice));

        aPatient.setFram("Update Patient");
    }

    public static void main(String[] args) {
        new UpdatePatient("");
    }
}
