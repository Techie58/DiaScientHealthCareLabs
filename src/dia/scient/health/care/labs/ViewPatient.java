package dia.scient.health.care.labs;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class ViewPatient extends JFrame implements ActionListener {

    JLabel searchLable;
    Choice searchChoice;
    JTable jTable;
    JButton searchBtn,printBtn,updateBtn,backBtn;

    ViewPatient(){


        setFram();

    }




    public static void main(String[] args) {
        new ViewPatient();
    }



    private void setFram(){

        setSearchLayout();

        setSize(900,600);
        setLayout(null);
        setLocation(300,100);
        setTitle("View Patient Details");


        setVisible(true);

    }
    private void setSearchLayout(){
        searchLable=new JLabel("Search by Patient ID ");
        searchLable.setBounds(20,10,130,30);
        searchLable.setFont(searchLable.getFont().deriveFont(Font.BOLD));
        add(searchLable);

        searchChoice=new Choice();
        searchChoice.setFont(searchLable.getFont().deriveFont(Font.BOLD));
        searchChoice.setBounds(160,16,100,30);
        add(searchChoice);
        setBtn();

        try {
            dbConnection dbConnection=new dbConnection();
            ResultSet resultSet=dbConnection.statement.executeQuery("SELECT * FROM patient_details");

            while(resultSet.next()){
                searchChoice.add(resultSet.getString("patient_id"));
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }

        jTable=new JTable();
        jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane jScrollPane=new JScrollPane(jTable);
        jScrollPane.setBounds(2,100,880,500);
        add(jScrollPane);
        try {
            dbConnection dbConnection=new dbConnection();
            ResultSet resultSet=dbConnection.statement.executeQuery("SELECT * FROM patient_details");
            jTable.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch (Exception exception){
            exception.printStackTrace();
        }




    }
    private void setBtn(){
        searchBtn=new JButton("Search");
        searchBtn.setBounds(20,50,80,30);
        searchBtn.addActionListener(this);
        add(searchBtn);

        printBtn=new JButton("Print");
        printBtn.setBounds(130,50,80,30);
        printBtn.addActionListener(this);
        add(printBtn);

        updateBtn=new JButton("Update");
        updateBtn.setBounds(240,50,80,30);
        updateBtn.addActionListener(this);
        add(updateBtn);

        backBtn=new JButton("Back");
        backBtn.setBounds(350,50,80,30);
        backBtn.addActionListener(this);
        add(backBtn);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==searchBtn){
            try {
                dbConnection dbConnection=new dbConnection();
                ResultSet resultSet=dbConnection.statement.executeQuery("SELECT * FROM patient_details WHERE patient_id = '"+searchChoice.getSelectedItem()+"'");
                jTable.setModel(DbUtils.resultSetToTableModel(resultSet));

            }catch (Exception exception){
                exception.printStackTrace();
            }


        }else if (e.getSource()==printBtn){
            try {
                jTable.print();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else if (e.getSource()==updateBtn){
            setVisible(false);

            new UpdatePatient(searchChoice.getSelectedItem());

        }
        else {
            setVisible(false);
            new Main();}

    }
}
