package dia.scient.health.care.labs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPatient extends JFrame {

    JTextField tfName,tfLabNum,tfAge,tfPatientID,tfPhoneNo,tfPayment;
    JComboBox cbTest,cbGender;
    JTextArea taDetails,taAddress;


    AddPatient(){


        setFram();


    }

    public static void main(String[] args) {

        new AddPatient();
    }

    private void setFram(){

        setLable();
        setBtn();

        setSize(1000,500);
        setLayout(null);
        setTitle("Add Patient");
        setLocation(100,150);

        setVisible(true);

    }

    private void setLable(){
        JLabel addNewPatient,PaymentLable,LabNumLable,NameLable,AgeLable,GenderLable,PatientIDLable,TestLable,DateTimeLable,PhoneNoLable,AddressLable,Remarks,sysDateTimeLable;

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        addNewPatient=new JLabel("ADD NEW PATIENT");
        addNewPatient.setBounds(100,0,300,50);
        addNewPatient.setFont(new Font("Times New Roman",Font.BOLD,25));
        add(addNewPatient);

        LabNumLable=new JLabel("Lab Number");
        LabNumLable.setBounds(10,36,130,30);
        add(LabNumLable);
        LabNumLable.setBorder(border);

        tfLabNum=new JTextField();
        tfLabNum.setBounds(142,36,352,30);
        add(tfLabNum);

        //Total width of Lab_Number = 495

        PatientIDLable=new JLabel("Patient ID");
        PatientIDLable.setBounds(10,68,130,30);
        add(PatientIDLable);
        PatientIDLable.setBorder(border);

        tfPatientID=new JTextField("01");
        tfPatientID.setBounds(142,68,100,30);
        tfPatientID.setEditable(false);
        add(tfPatientID);


        DateTimeLable=new JLabel("Date & Time");
        DateTimeLable.setBounds(244,68,100,30);
        add(DateTimeLable);
        DateTimeLable.setBorder(border);


        String currentDate=new SimpleDateFormat("dd-MM-yyyy HH:mm").format(new Date());
        sysDateTimeLable=new JLabel(currentDate);
        sysDateTimeLable.setBounds(346,68,149,30);
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

        // Total WIDTH of the Patient Address is = 495


        Remarks=new JLabel("Remarks");
        Remarks.setBounds(10,290,130,60);
        add(Remarks);
        Remarks.setBorder(border);

        taDetails=new JTextArea(5,20);
        taDetails.setLineWrap(true); // Wrap text
        taDetails.setWrapStyleWord(true);
        taDetails.setBorder(BorderFactory.createLineBorder(Color.gray)); // Add border
        taDetails.setBounds(142,290,352,60);
        add(taDetails);

        // Total WIDTH of the Patient Remarks is = 495


        PaymentLable=new JLabel("Payment");
        PaymentLable.setBounds(10,352,130,30);
        add(PaymentLable);
        PaymentLable.setBorder(border);

        tfPayment=new JTextField();
        tfPayment.setBounds(142,352,352,30);
        add(tfPayment);






    }
    private void setBtn(){
        JButton saveBtn, printBtn, closeBtn;
        saveBtn=new JButton("SAVE");
        saveBtn.setBounds(245,400,75,30);
        add(saveBtn);

        printBtn=new JButton("PRINT");
        printBtn.setBounds(325,400,75,30);
        add(printBtn);

        closeBtn=new JButton("CLOSE");
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
}
