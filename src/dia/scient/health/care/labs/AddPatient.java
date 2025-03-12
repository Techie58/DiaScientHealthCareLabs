package dia.scient.health.care.labs;

import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPatient extends JFrame {

    JTextField tfName,tfLabNum,tfAge,tfPatientID,tfPhoneNo,tfPayment;
    JLabel addNewPatient,PaymentLable,LabNumLable,NameLable,AgeLable,GenderLable,PatientIDLable,TestLable,DateTimeLable,PhoneNoLable,AddressLable,Remarks,sysDateTimeLable;

    JComboBox cbTest,cbGender;
    JTextArea taRemarks,taAddress;
    String currentDate;
    JScrollPane jScrollPane;

    private int patientID;


    AddPatient(){ }

    public static void main(String[] args) {

        new AddPatient();
        AddPatient addPatient=new AddPatient();

        addPatient.setBtn("SAVE",null);
        addPatient.setLable("Add Patient");
        addPatient.setFram("Add Patient");

    }

    public void setFram(String framTitle)
    {




        setSize(1000,500);
        setLayout(null);
        setTitle(framTitle);
        setLocation(100,150);

        setVisible(true);

    }

    public void setLable(String headingLable){

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        addNewPatient=new JLabel(headingLable);
        addNewPatient.setBounds(100,0,400,40);
        addNewPatient.setFont(new Font("Times New Roman",Font.BOLD,35));
        add(addNewPatient);

        LabNumLable=new JLabel("Lab Number");
        LabNumLable.setBounds(10,36,130,30);
        add(LabNumLable);
        LabNumLable.setBorder(border);

        tfLabNum=new JTextField();
        tfLabNum.setBounds(142,36,352,30);
        add(tfLabNum);
        setIntRestriction(tfLabNum);



        //Total width of Lab_Number = 495


        PatientIDLable=new JLabel("Patient ID");
        PatientIDLable.setBounds(10,68,130,30);
        add(PatientIDLable);
        PatientIDLable.setBorder(border);

        try {
            dbConnection dbConnection = new dbConnection();

            // Check if the table is empty
            ResultSet resultSet = dbConnection.statement.executeQuery("SELECT COUNT(*) AS count FROM patient_details");

            int currentID = 1; // Default ID

            if (resultSet.next() && resultSet.getInt("count") > 0) {
                // Get the last used ID
                ResultSet rsMax = dbConnection.statement.executeQuery("SELECT MAX(patient_id) AS max_id FROM patient_details");
                if (rsMax.next()) {
                    currentID = rsMax.getInt("max_id") + 1;
                }
            } else {
                // Reset AUTO_INCREMENT when the table is empty
                dbConnection.statement.executeUpdate("ALTER TABLE patient_details AUTO_INCREMENT = 1");
            }

            tfPatientID = new JTextField(String.valueOf(currentID));
            tfPatientID.setBounds(142, 68, 100, 30);
            add(tfPatientID);
            tfPatientID.setEditable(false);

        } catch (Exception e) {
            e.printStackTrace();
            tfPatientID = new JTextField("1");
            tfPatientID.setBounds(142, 68, 100, 30);
            add(tfPatientID);
        }



        DateTimeLable=new JLabel("Date & Time");
        DateTimeLable.setBounds(244,68,100,30);
        add(DateTimeLable);
        DateTimeLable.setBorder(border);


        currentDate=new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        sysDateTimeLable=new JLabel(currentDate);
        sysDateTimeLable.setBounds(346,68,147,30);
        sysDateTimeLable.setFont(new Font("Times New Roman",Font.BOLD,18));
        add(sysDateTimeLable);
        sysDateTimeLable.setBorder(border);


        // Total with of the Patient ID & Date is = 495


        NameLable=new JLabel("Patient Name");
        NameLable.setBounds(10,100,130,30);
        add(NameLable);
        NameLable.setBorder(border);

        tfName=new JTextField();
        tfName.setBounds(142,100,352,30);
        add(tfName);


        // Total WIDTH of the Patient Name is = 495

        GenderLable=new JLabel("Gender");
        GenderLable.setBounds(10,132,130,30);
        add(GenderLable);
        GenderLable.setBorder(border);

        String gender[]={"Male","Female"};
        cbGender=new JComboBox(gender);
        cbGender.setBounds(142,132,100,30);
        add(cbGender);


        AgeLable=new JLabel("Age");
        AgeLable.setBounds(244,132,98,30);
        add(AgeLable);
        AgeLable.setBorder(border);

        tfAge=new JTextField();
        tfAge.setBounds(344,132,150,30);
        setIntRestriction(tfAge);

        add(tfAge);

        // Total WIDTH of the Patient Age & Gender is = 495

        TestLable=new JLabel("Test");
        TestLable.setBounds(10,164,130,30);
        add(TestLable);
        TestLable.setBorder(border);

        String tests[]={"Complete blood count (CBC)","Blood lipid profile","Antinuclear antibody","Blood chemistry study","BNP testing"," Complement"," Creatinine"," C-reactive protein (CRP)"};
        cbTest=new JComboBox(tests);
        cbTest.setBounds(142,164,352,30);
        add(cbTest);


        // Total WIDTH of the Tests is = 495


        PhoneNoLable=new JLabel("Phone No");
        PhoneNoLable.setBounds(10,196,130,30);
        add(PhoneNoLable);
        PhoneNoLable.setBorder(border);

        tfPhoneNo=new JTextField();
        tfPhoneNo.setBounds(142,196,352,30);
        add(tfPhoneNo);
        setIntRestriction(tfPhoneNo);

        // Total WIDTH of the Patient Phone No is = 495


        AddressLable=new JLabel("Address");
        AddressLable.setBounds(10,228,130,60);
        add(AddressLable);
        AddressLable.setBorder(border);

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


        Remarks=new JLabel("Remarks");
        Remarks.setBounds(10,290,130,60);
        add(Remarks);
        Remarks.setBorder(border);

        taRemarks=new JTextArea(5,20);
        taRemarks.setLineWrap(true); // Wrap text
        taRemarks.setWrapStyleWord(true);
        taRemarks.setBorder(BorderFactory.createLineBorder(Color.gray)); // Add border
        taRemarks.setBounds(142,290,352,60);
        add(taRemarks);

        // Total WIDTH of the Patient Remarks is = 495


        PaymentLable=new JLabel("Payment");
        PaymentLable.setBounds(10,352,130,30);
        add(PaymentLable);
        PaymentLable.setBorder(border);



        tfPayment=new JTextField();
        tfPayment.setBounds(142,352,352,30);
        setIntRestriction(tfPayment);
        add(tfPayment);






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

                dbConnection db=new dbConnection();
                String query="SELECT * FROM patient_details where patient_id = '"+patientID+"'";
                ResultSet resultset = db.statement.executeQuery(query);
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

                }}catch (Exception e){
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


//                    Establishing Connection with Database

                        dbConnection db=new dbConnection();
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

                        db.statement.executeUpdate(updateBtnQuery);


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


    private void setIntRestriction(JTextField tf) {
            tf.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();

                    // Allow only digits, backspace, and delete
                    if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
                        e.consume(); // Ignore the key event
                    }

                    // Limit input length to 11 characters
                    if (tf.getText().length() >= 11) {
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
            dbConnection db = new dbConnection();
            String saveBtnQuery = "INSERT INTO patient_details(lab_number, date_time, patient_name, gender, age, test, phone_no, address, remarks, payment) " +
                    "VALUES('" + labNum + "', '" + dataTime + "', '" + patientName + "', '" + gender + "', '" + age + "', '" + test + "', '" + phoneNo + "', '" + address + "', '" + remarks + "', '" + payment + "')";

            db.statement.executeUpdate(saveBtnQuery);
            JOptionPane.showMessageDialog(null, "Patient Details Saved");
            setVisible(false);
            new Main();

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Error saving patient details.", "Error", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
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
                dbConnection db = new dbConnection();
                String saveBtnQuery = "INSERT INTO patient_details(lab_number, date_time, patient_name, gender, age, test, phone_no, address, remarks, payment) " +
                        "VALUES('" + labNum + "', '" + dataTime + "', '" + patientName + "', '" + gender + "', '" + age + "', '" + test + "', '" + phoneNo + "', '" + address + "', '" + remarks + "', '" + payment + "')";

                db.statement.executeUpdate(saveBtnQuery);
                new Print(tfPatientID.getText().toString());
                setVisible(false);


            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Error saving patient details.", "Error", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
    }


