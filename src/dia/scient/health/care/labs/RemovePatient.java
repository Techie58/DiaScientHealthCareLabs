package dia.scient.health.care.labs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class RemovePatient extends JFrame implements ActionListener {

    private Choice patientIDChoice;
    private JLabel labNoLabel, patientNameLabel, genderLabel, testLabel;
    private dbConnection db;  // Use a single database connection instance
    private JButton deleteBtn, backBtn;

    RemovePatient() {
        db = new dbConnection();  // Initialize DB connection
        setFrame();
        getData();
    }

    public static void main(String[] args) {
        new RemovePatient();
    }

    private void setFrame() {
        setSize(1000, 400);
        setLayout(null);
        setTitle("Remove Patient");
        setLocation(150, 100);

        setLable("Patient ID", 50, 50);

        patientIDChoice = new Choice();
        patientIDChoice.setBounds(210, 50, 150, 30);
        add(patientIDChoice);

        setLable("Lab No", 50, 100);
        labNoLabel = setLable("", 210, 100);

        setLable("Patient Name", 50, 140);
        patientNameLabel = setLable("", 210, 140);

        setLable("Gender", 50, 180);
        genderLabel = setLable("", 210, 180);

        setLable("Test", 50, 220);
        testLabel = setLable("", 210, 220);

        deleteBtn = setButton("Delete",100,300);
        backBtn   = setButton("Back",220,300);

        setVisible(true);
    }

    private void getData() {
        try {
            ResultSet rs = db.statement.executeQuery("SELECT patient_id FROM patient_details");
            while (rs.next()) {
                patientIDChoice.add(rs.getString("patient_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add listener to update patient details when an ID is selected
        patientIDChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                updatePatientDetails(patientIDChoice.getSelectedItem());
            }
        });

        // Load details for the first patient by default
        if (patientIDChoice.getItemCount() > 0) {
            updatePatientDetails(patientIDChoice.getItem(0));
        }
    }

    private void updatePatientDetails(String patientId) {
        try {
            ResultSet rs = db.statement.executeQuery("SELECT * FROM patient_details WHERE patient_id = " + patientId);
            if (rs.next()) {
                labNoLabel.setText(rs.getString("lab_number"));
                patientNameLabel.setText(rs.getString("patient_name"));
                genderLabel.setText(rs.getString("gender"));
                testLabel.setText(rs.getString("test"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JLabel setLable(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        add(label);
        return label;
    }
    private JButton setButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 100, 30);
        button.addActionListener(this);
        add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == deleteBtn) {
            try {
                String query="DELETE FROM patient_details WHERE patient_id ='"+patientIDChoice.getSelectedItem()+"'";
                db.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Patient Details Deleted!");
                setVisible(false);
                new RemovePatient();


            }catch (Exception exception){
                exception.printStackTrace();
                JOptionPane.showMessageDialog(null,"There is No Any Patient","No Record",JOptionPane.ERROR_MESSAGE);
            }
        }else {
            setVisible(false);
            new Main();
        }
    }
}

