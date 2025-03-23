package dia.scient.health.care.labs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPatient extends JFrame {

    JTextField tfName,tfLabNum,tfAge,tfPatientID,tfPhoneNo,tfPayment;
    JLabel addNewPatientLabel,PaymentLable,LabNumLable,NameLable,AgeLable,PhoneNoLable;

    JComboBox cbTest,cbGender;
    JTextArea taRemarks,taAddress;
    String currentDate;
    JScrollPane jScrollPane;
    SQLiteDbConnection sqLiteDbConnection;


    public static void main(String[] args) {

        AddPatient addPatient=new AddPatient();

        addPatient.setBtn("SAVE",null);
        addPatient.setLabels("Add Patient");
        addPatient.setFram("Add Patient");

    }

    public void setFram(String framTitle) {
        setSize(1000,500);
        setLayout(null);
        setTitle(framTitle);
        setLocation(100,150);

        setVisible(true);

    }

    public void setLabels(String headingLable){


        addNewPatientLabel = setPatientDetailLabel(headingLable,100,0,400,40);
        addNewPatientLabel.setFont(new Font("Times New Roman",Font.BOLD,35));
        addNewPatientLabel.setBorder(BorderFactory.createEmptyBorder());



        LabNumLable= setPatientDetailLabel("MR #",10,36,130,30);
        tfLabNum= setPatientDetailTextField(142,36,352,30,true,5);
        //Total width of Lab_Number = 495
        setPatientDetailLabel("Patient ID",10,68,130,30);

        try {
             sqLiteDbConnection = new SQLiteDbConnection();

            ResultSet resultSet = sqLiteDbConnection.statement.executeQuery("SELECT COUNT(*) AS count FROM patient_details");
            int currentID = 1; // Default ID

            if (resultSet.next() && resultSet.getInt("count") > 0) {
                // Get the last used ID
                ResultSet rsMax = sqLiteDbConnection.statement.executeQuery("SELECT MAX(patient_id) AS max_id FROM patient_details");
                if (rsMax.next()) {
                    currentID = rsMax.getInt("max_id") + 1;
                }
            } else {
                // Reset patient_id in SQLite when table is empty
                sqLiteDbConnection.statement.executeUpdate("DELETE FROM sqlite_sequence WHERE name='patient_details'");
            }

            tfPatientID=setPatientDetailTextField(142, 68, 100, 30,false,null);
            tfPatientID.setText(String.valueOf(currentID));
            tfPatientID.setEditable(false);

            resultSet.close();
            sqLiteDbConnection.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            tfPatientID = new JTextField("1");
            tfPatientID.setBounds(142, 68, 100, 30);
            add(tfPatientID);
        }



        setPatientDetailLabel("Date",244,68,100,30);

        currentDate=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        setPatientDetailLabel(currentDate,346,68,147,30);

        // Total with of the Patient ID & Date is = 495


        NameLable = setPatientDetailLabel("Patient Name",10,100,130,30);
        tfName = setPatientDetailTextField(142,100,352,30,false,null);

          // Total WIDTH of the Patient Name is = 495

        setPatientDetailLabel("Gender",10,132,130,30);
        String gender[]={"Male","Female"};
        cbGender=new JComboBox(gender);
        cbGender.setBounds(142,132,100,30);
        add(cbGender);


        AgeLable = setPatientDetailLabel("Age",244,132,98,30);
        tfAge = setPatientDetailTextField(344,132,150,30,true,3);

        // Total WIDTH of the Patient Age & Gender is = 495

        setPatientDetailLabel("Test",10,164,130,30);

        String tests[]={"COMPLETE BLOOD COUNT (CBC)","H. PYLORI ANTIGEN (STOOL) ICT","BLOOD LIPID PROFILE", "ANTINUCLEAR ANTIBODY", "BLOOD CHEMISTRY STUDY", "BNP TESTING", "COMPLEMENT", "CREATININE", "C-REACTIVE PROTEIN (CRP)"};
        cbTest=new JComboBox(tests);
        cbTest.setBounds(142,164,352,30);
        add(cbTest);


        // Total WIDTH of the Tests is = 495


        PhoneNoLable = setPatientDetailLabel("Phone No",10,196,130,30);
        tfPhoneNo = setPatientDetailTextField(142,196,352,30,true,11);
        // Total WIDTH of the Patient Phone No is = 495


        setPatientDetailLabel("Address",10,228,130,60);

        taAddress=new JTextArea(5,20);
        taAddress.setLineWrap(true); // Wrap text
        taAddress.setWrapStyleWord(true);
        taAddress.setBorder(BorderFactory.createLineBorder(Color.gray)); // Add border
        taAddress.setBounds(142,228,352,60);
        add(taAddress);

        jScrollPane=new JScrollPane(taAddress);
        jScrollPane.setBounds(142,228,352,60);
        add(jScrollPane);

        // Total WIDTH of the Patient Address is = 495


        setPatientDetailLabel("Remarks",10,290,130,60);

        taRemarks=new JTextArea(5,20);
        taRemarks.setLineWrap(true); // Wrap text
        taRemarks.setWrapStyleWord(true);
        taRemarks.setBorder(BorderFactory.createLineBorder(Color.gray)); // Add border
        taRemarks.setBounds(142,290,352,60);
        add(taRemarks);

        // Total WIDTH of the Patient Remarks is = 495


        PaymentLable = setPatientDetailLabel("Payment",10,352,130,30);

        tfPayment = setPatientDetailTextField(142,352,352,30,true,6);

    }

    public void setBtn(String btnName,Integer patientID ){




        if (patientID==null){

            JButton saveBtn, printBtn, closeBtn;

            saveBtn=new JButton(btnName);
            saveBtn.setBounds(235,400,85,30);
            add(saveBtn);
            saveBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    insertDataIntoDB();

                }
            });



            printBtn=new JButton("PRINT");
            printBtn.setBounds(325,400,75,30);
            add(printBtn);
            printBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    printData();

                }
            });

            closeBtn=new JButton("BACK");
            closeBtn.setBounds(405,400,75,30);
            add(closeBtn);

            closeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Main();
                    setVisible(false);

                }
            });

        }

        else {

//            Setting Existing Data into the Fields
            try {

                sqLiteDbConnection = new SQLiteDbConnection();
                String query = "SELECT * FROM patient_details WHERE patient_id = ?";

                PreparedStatement stmt = sqLiteDbConnection.connection.prepareStatement(query);
                stmt.setInt(1, patientID);

                ResultSet resultset = stmt.executeQuery();
                while (resultset.next()){

                    tfLabNum.setText(Integer.toString(resultset.getInt("lab_number")));
                    tfPatientID.setText(Integer.toString(resultset.getInt("patient_id")));
                    tfName.setText(resultset.getString("patient_name"));
                    tfAge.setText(Integer.toString(resultset.getInt("age")));
                    tfPhoneNo.setText(resultset.getString("phone_no"));
                    tfPayment.setText(Integer.toString(resultset.getInt("payment")));
                    taAddress.setText(resultset.getString("address"));
                    taRemarks.setText(resultset.getString("remarks"));
                    cbGender.setSelectedItem(resultset.getString("gender"));
                    cbTest.setSelectedItem(resultset.getString("test"));

                }
                // Close resources
                resultset.close();
                stmt.close();
                sqLiteDbConnection.connection.close(); // Close connection
            }catch (Exception e){
                e.printStackTrace();
            }



            JButton updateBtn, closeBtn;
            updateBtn=new JButton(btnName);
            updateBtn.setBounds(200,400,140,30);
            add(updateBtn);

            updateBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        // Close old connection before opening a new one
                        if (sqLiteDbConnection != null && !sqLiteDbConnection.connection.isClosed()) {
                            sqLiteDbConnection.connection.close();
                        }
//                    Establishing Connection with Database

                        sqLiteDbConnection=new SQLiteDbConnection();
                        String dataTime,patientName,gender,test,address,remarks,phoneNo;
                        int labNum,patientID,age,payment;

                        labNum= Integer.parseInt(tfLabNum.getText());
                        patientID= Integer.parseInt(tfPatientID.getText());
                        age= Integer.parseInt(tfAge.getText());
                        phoneNo= tfPhoneNo.getText();
                        payment= Integer.parseInt(tfPayment.getText());

                        dataTime=currentDate;
                        patientName=tfName.getText();
                        gender=cbGender.getSelectedItem().toString();
                        test=cbTest.getSelectedItem().toString();
                        address=taAddress.getText();
                        remarks=taRemarks.getText();
                        if (remarks.isEmpty()){
                            remarks="";
                        }


                        String updateBtnQuery = "UPDATE patient_details SET " +
                                "lab_number = '" + labNum + "', " +
                                "date_time = '" + dataTime + "', " +
                                "patient_name = '" + patientName + "', " +
                                "gender = '" + gender + "', " +
                                "age = '" + age + "', " +
                                "test = '" + test + "', " +
                                "phone_no = '" + phoneNo + "', " +
                                "address = '" + address + "', " +
                                "remarks = '" + remarks + "', " +
                                "payment = '" + payment + "' " +
                                "WHERE patient_id = '" + patientID + "'";

                        sqLiteDbConnection.statement.executeUpdate(updateBtnQuery);



                        JOptionPane.showMessageDialog(null,"Patient Details is Updated");
                        setVisible(false);
                        new ViewPatient();

                    }catch (Exception e1) {
                        JOptionPane.showMessageDialog(null,"Please Fill All the Details","Missing Details",JOptionPane.ERROR_MESSAGE);
                        e1.printStackTrace();
                    }
                }
            });


            closeBtn=new JButton("BACK");
            closeBtn.setBounds(350,400,140,30);
            add(closeBtn);

            closeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Main();
                    setVisible(false);

                }
            });

        }


        }
    private void printData(){

        try {
            // Validate Required Fields
            boolean isValid = true;
            isValid &= setNotEmptyIntValidation(tfLabNum, LabNumLable);
            isValid &= setNotEmptyStringValidation(tfName, NameLable);
            isValid &= setNotEmptyIntValidation(tfAge, AgeLable);
            isValid &= setNotEmptyIntValidation(tfPayment, PaymentLable);
            isValid &= setNotEmptyStringValidation(tfPhoneNo, PhoneNoLable);

            if (!isValid) {
                JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Missing Details", JOptionPane.ERROR_MESSAGE);
                return; // Stop execution if validation fails
            }

            // Extracting Data
            String dataTime = currentDate;
            String patientName = tfName.getText();
            String gender = cbGender.getSelectedItem().toString();
            String test = cbTest.getSelectedItem().toString();
            String address = taAddress.getText();
            String remarks = taRemarks.getText();
            if (remarks.isEmpty()) remarks = "";

            int labNum = Integer.parseInt(tfLabNum.getText());
            int age = Integer.parseInt(tfAge.getText());
            String phoneNo = tfPhoneNo.getText();
            int payment = Integer.parseInt(tfPayment.getText());

            // Database Insertion
            sqLiteDbConnection = new SQLiteDbConnection();
            String saveBtnQuery = "INSERT INTO patient_details(lab_number, date_time, patient_name, gender, age, test, phone_no, address, remarks, payment) " +
                    "VALUES('" + labNum + "', '" + dataTime + "', '" + patientName + "', '" + gender + "', '" + age + "', '" + test + "', '" + phoneNo + "', '" + address + "', '" + remarks + "', '" + payment + "')";

            sqLiteDbConnection.statement.executeUpdate(saveBtnQuery);
            new Print(tfPatientID.getText().toString());
            setVisible(false);


        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Error saving patient details.", "Error", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }

    private void setIntRestriction(JTextField tf, int maxLength) {
        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                    e.consume();
                }
                if (tf.getText().length() >= maxLength) {
                    e.consume();
                }
            }
        });
    }
    private boolean setNotEmptyIntValidation(JTextField textField, JLabel jLabel) {
        if (textField.getText().trim().isEmpty()) {
            jLabel.setForeground(Color.RED);
            return false; // Validation failed
        } else {
            jLabel.setForeground(Color.BLACK);
            return true; // Validation passed
        }
    }
    private boolean setNotEmptyStringValidation(JTextField textField, JLabel jLabel) {

        if (textField.getText().trim().isEmpty()) {
            jLabel.setForeground(Color.RED);
            return false; // Validation failed
        } else {
            jLabel.setForeground(Color.BLACK);
            return true; // Validation passed
        }
    }
    private void insertDataIntoDB(){

        try {
            // Validate Required Fields
            boolean isValid = true;
            isValid &= setNotEmptyIntValidation(tfLabNum, LabNumLable);
            isValid &= setNotEmptyStringValidation(tfName, NameLable);
            isValid &= setNotEmptyIntValidation(tfAge, AgeLable);
            isValid &= setNotEmptyIntValidation(tfPayment, PaymentLable);
            isValid &= setNotEmptyStringValidation(tfPhoneNo, PhoneNoLable);

            if (!isValid) {
                JOptionPane.showMessageDialog(null, "Please fill all the required fields!", "Missing Details", JOptionPane.ERROR_MESSAGE);
                return; // Stop execution if validation fails
            }

            // Extracting Data
            String dataTime = currentDate;
            String patientName = tfName.getText();
            String gender = cbGender.getSelectedItem().toString();
            String test = cbTest.getSelectedItem().toString();
            String address = taAddress.getText();
            String remarks = taRemarks.getText();
            if (remarks.isEmpty()) remarks = "";

            int labNum = Integer.parseInt(tfLabNum.getText());
            int age = Integer.parseInt(tfAge.getText());
            String phoneNo = tfPhoneNo.getText();
            int payment = Integer.parseInt(tfPayment.getText());

            // Database Insertion
            sqLiteDbConnection= new SQLiteDbConnection();
            String query = "INSERT INTO patient_details (lab_number, date_time, patient_name, gender, age, test, phone_no, address, remarks, payment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = sqLiteDbConnection.connection.prepareStatement(query)) {
                stmt.setInt(1, labNum);
                stmt.setString(2, dataTime);
                stmt.setString(3, patientName);
                stmt.setString(4, gender);
                stmt.setInt(5, age);
                stmt.setString(6, test);
                stmt.setString(7, phoneNo);
                stmt.setString(8, address);
                stmt.setString(9, remarks);
                stmt.setInt(10, payment);
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Patient Details Saved");
            setVisible(false);
            new Main();

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Error saving patient details.", "Error", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
        }
    }
    private JLabel setPatientDetailLabel(String title,int x,int y,int width,int height){
        Border border = BorderFactory.createLineBorder(Color.BLACK);

        JLabel lable=new JLabel(title);
        lable.setBounds(x,y,width,height);
        lable.setBorder(border);
        add(lable);
        return lable;
  }
    private JTextField setPatientDetailTextField(int x, int y, int width, int height, boolean intRestriction, Integer RestrictionLimit) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        add(textField);

        if (intRestriction && RestrictionLimit != null) {
            setIntRestriction(textField, RestrictionLimit);
        }
        return textField;
    }

    }


