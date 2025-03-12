package dia.scient.health.care.labs;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class ReportDetails {
    ReportDetails(String patientID,JPanel jPanel){

        JLabel reportTitleLabel = new JLabel("Report Details");
        reportTitleLabel.setBounds(70,750,1500,40);
        reportTitleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        jPanel.add(reportTitleLabel);

        try {

            dbConnection databaseConnection = new dbConnection();
            ResultSet resultSet=databaseConnection.statement.executeQuery("SELECT * FROM patient_details WHERE patient_id = '"+patientID+"' ");
            while (resultSet.next()) {
                reportTitleLabel.setText(resultSet.getString("test"));


            }

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Database Error", JOptionPane.ERROR_MESSAGE);
        }



    }
    public static void main(String[] args) {
        new RemovePatient();
    }

}
