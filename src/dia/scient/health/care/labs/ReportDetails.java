package dia.scient.health.care.labs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.ResultSet;

public class ReportDetails {
    ReportDetails(String patientID,JPanel jPanel){

        // Create JPanel
        JPanel headerPanel = createHeaderPanel();
        headerPanel.setBounds(70, 620, 1150, 70); // Adjust position and size
        jPanel.add(headerPanel);


        try {

            dbConnection databaseConnection = new dbConnection();
            ResultSet resultSet=databaseConnection.statement.executeQuery("SELECT * FROM patient_details WHERE patient_id = '"+patientID+"' ");
            while (resultSet.next()) {
                String reportTitle = resultSet.getString("test");
                JLabel rTitle = setLabel(jPanel,reportTitle,70,580,1500,40,true);
                rTitle.setHorizontalAlignment(SwingConstants.LEFT);


            }

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Database Error", JOptionPane.ERROR_MESSAGE);
        }



    }
    public static void main(String[] args) {
        new RemovePatient();
    }



    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        panel.setPreferredSize(new Dimension(1150, 70));

        Border border=BorderFactory.createLineBorder(Color.BLACK, 2);

        // Column Labels
        JLabel testLabel = setLabel(panel,"Test", 0, 0, 400, 70,true);
        testLabel.setHorizontalAlignment(SwingConstants.LEFT);
        testLabel.setBorder(border);
        JLabel resultsLabel = setLabel(panel,"Results", 400, 0, 375, 70,true);
        resultsLabel.setBorder(border);
        JLabel previousResultsLabel = setLabel(panel,"Previous Results", 775, 0, 375, 70,true);
        previousResultsLabel.setBorder(border);

        // Add labels to panel
        panel.add(testLabel);
        panel.add(resultsLabel);
        panel.add(previousResultsLabel);

        return panel;
    }
    private JLabel setLabel(JPanel jPanel,String labelText,int x, int y, int width, int height,Boolean boldFont){

        JLabel jLabel = new JLabel(labelText,SwingConstants.CENTER);
        jLabel.setBounds(x, y, width,height);

        if (boldFont){
            jLabel.setFont(new Font("Arial", Font.BOLD, 20));
        }else {
            jLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        }

        jPanel.add(jLabel);
        return jLabel;

    }


    private static JLabel createHeaderLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return label;
    }


}

