package dia.scient.health.care.labs;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;

public class Print extends JFrame implements Printable {
    private JScrollPane scrollPane;
    private JPanel jPanel;
    private JLabel pNameLabel,pIdLabel,pAgeLabel,pGenderLabel,pDateLabel,pLabNo,pRegDateLabel,pTestLabel;
    private dbConnection databaseConnection;
    private int YvalueOfLabel,labelHeight,secondRawYValue,thirdRawYValue;

    public Print(String patientID) {
        labelHeight=30;
        YvalueOfLabel=440;

        setPrintBtn();
        setFram();
        getDatabase(patientID);
    }

    public static void main(String[] args) {
        new Print("");
    }

    private void setFram() {


        getJPanel();

        // JFrame settings
        setLayout(null);
        setSize(1100, 600);

        setLocation(150, 50);
        setTitle("Print");
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel getJPanel() {
        // Create a JPanel with a proper layout
        jPanel = new JPanel();
        jPanel.setLayout(null);  // Using null layout for absolute positioning
        jPanel.setPreferredSize(new Dimension(1240, 1754)); // Set preferred size to make JScrollPane work
        jPanel.setBorder(new EmptyBorder(20, 50, 20, 30)); // Top, Left, Bottom, Right padding

        jPanel.setBackground(Color.WHITE);


        settingHeader();
        setUserDetailLabels();

        setReportDetail();


        // Create a JScrollPane and add the JPanel to it
        scrollPane = new JScrollPane(jPanel);
        scrollPane.setBounds(10, 45, 1060, 500);  // Position & size of the scroll pane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add JScrollPane to the JFrame
        add(scrollPane);

        return jPanel;
    }

    private JLabel setLabel(String labelText,int x, int y, int width, int height){
        JLabel jLabel = new JLabel(labelText);
        jLabel.setBounds(x, y, width,height);
        jLabel.setFont(new Font("Arial", Font.BOLD, 18));


        jPanel.add(jLabel);
        return jLabel;

    }

    private JLabel setUserDataLabel(String labelText,int x, int y, int width, int height){
        JLabel jLabel = new JLabel(labelText);
        jLabel.setBounds(x, y, width,height);
        jLabel.setFont(new Font("Arial", Font.PLAIN, 18));


        jPanel.add(jLabel);
        return jLabel;

    }
    private void setImg(String imgPath,int x, int y, int width, int height){

        ImageIcon bgLogo= new ImageIcon(ClassLoader.getSystemResource(imgPath));
        Image reSizeBgImg= bgLogo.getImage().getScaledInstance(width,height,Image.SCALE_SMOOTH);
        ImageIcon bgLogoReGet=new ImageIcon(reSizeBgImg);
        JLabel bgLogoLable= new JLabel(bgLogoReGet);
        bgLogoLable.setBounds(x,y,width,height);
        jPanel.add(bgLogoLable);
    }

    private void settingHeader(){
        String adress="<html>"
                + "<div style='line-height: 1.5;'>"
                + "<b>DIA Scient Medical Complex,</b><br>"
                + "35-G, Gulistan Colony No.1,<br>"
                + "Near Aziz Fatima Hospital, Faisalabad.<br>"
                + "<b>Ph:</b> +92-41-8862324-25<br>"
                + "<b>Help Line:</b> +92-345-7664420, 30<br>"
                + "<b>E-mail:</b> ceo@diascient.com<br>"
                + "<b>www.diascient.com</b>"
                + "<b>UAN: +92-304-333-3579</b>"
                + "</html>";

        setImg("assets/bg_images/headerImg.png",2,202,1240,200);


//        JLabel headerAddressLabel=setLabel(adress,950,205, 310,200);
//        headerAddressLabel.setHorizontalAlignment(SwingConstants.LEFT); // Align text to left
//        headerAddressLabel.setVerticalAlignment(SwingConstants.TOP);
//        headerAddressLabel.setFont(new Font("Arial", Font.BOLD, 18));
//        jPanel.add(headerAddressLabel);
        int lineHeight=5;
        int lineYValue=405;
        int lineWidth=1500;

        setLabel("<html><hr width='1800px' size='2' color='black'></html>",10,lineYValue,1600,lineHeight);
        setLabel("<html><hr width='1800px' size='1' color='black'></html>",10,lineYValue+15,1500,lineHeight);
        setLabel("<html><hr width='1800px' size='5' color='black'></html>",10,lineYValue+130,1500,lineHeight);


    }
    private void setUserDetailLabels(){
        int width=100;
        setLabel("Name: ",      15,YvalueOfLabel,width,labelHeight);
        setLabel("Patient ID: ", 350,YvalueOfLabel,width,labelHeight);
        setLabel("Lab Refferal: ",750,YvalueOfLabel,width,labelHeight);

        secondRawYValue=YvalueOfLabel+labelHeight;
        setLabel("Age: ",      15,secondRawYValue,width,labelHeight);
        setLabel("Gender: ", 350,secondRawYValue,width,labelHeight);
        setLabel("Date: ",750,secondRawYValue,width,labelHeight);

        thirdRawYValue=secondRawYValue+labelHeight;
        setLabel("Referred By: ",      15,thirdRawYValue,width,labelHeight);
        setLabel("Registration Date: ", 350,thirdRawYValue,130,labelHeight);
        setLabel("Lab No: ",750,thirdRawYValue,width,labelHeight);
    }


    private void getDatabase(String patientID){
        pNameLabel = setUserDataLabel("Name ",      60  ,YvalueOfLabel,150,labelHeight);
        pIdLabel = setUserDataLabel("Patient ID ", 420,YvalueOfLabel,150,labelHeight);
        setUserDataLabel("DSL Marzipura",840,YvalueOfLabel,200,labelHeight);

        pAgeLabel = setUserDataLabel("Age",      60,secondRawYValue,150,labelHeight);
        pGenderLabel = setUserDataLabel("Gender ", 420,secondRawYValue,150,labelHeight);
        pDateLabel = setUserDataLabel("Date",810,secondRawYValue,200,labelHeight);

        setUserDataLabel("DSL Marzipura ",      110,thirdRawYValue,150,labelHeight);
        pRegDateLabel = setUserDataLabel("Registration Date ", 470,thirdRawYValue,150,labelHeight);
        pLabNo = setUserDataLabel("Lab No ",810,thirdRawYValue,150,labelHeight);


        try {

            databaseConnection = new dbConnection();
            ResultSet resultSet=databaseConnection.statement.executeQuery("SELECT * FROM patient_details WHERE patient_id = '"+patientID+"' ");
            while (resultSet.next()) {
                pNameLabel.setText(resultSet.getString("patient_name"));
                pIdLabel.setText(resultSet.getString("patient_id"));
                pAgeLabel.setText(resultSet.getString("age"));
                pGenderLabel.setText(resultSet.getString("gender"));
                pDateLabel.setText(resultSet.getString("date_time"));
                pRegDateLabel.setText(resultSet.getString("date_time"));
                pLabNo.setText(resultSet.getString("lab_number"));
                pTestLabel.setText(resultSet.getString("test"));


            }

        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,e.getMessage().toString(),"Database Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void setReportDetail(){

        pTestLabel = setLabel("H. PYLORI ANTIGEN (STOOL) ICT",10,370,600,40);
        pTestLabel.setFont(new Font("Arial",Font.BOLD,18));

    }

    private void setPrintBtn() {
        JButton printBtn = new JButton("PRINT");
        printBtn.setBounds(50, 10, 75, 30);
        add(printBtn);

        printBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printPanel();
                System.out.println("Print button clicked!");
            }
        });
        JButton backBtn=new JButton("Back");
        backBtn.setBounds(130, 10, 75, 30);
        add(backBtn);
        backBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new ViewPatient();
            }
        });
    }

    private void printPanel() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);
        boolean doPrint = printerJob.printDialog();
        if (doPrint) {
            try {
                printerJob.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }



        }
    }


    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Convert Graphics to Graphics2D for better scaling
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate((int) pf.getImageableX(), (int) pf.getImageableY());

        // Scale the JPanel to fit the print area
        double scaleX = pf.getImageableWidth() / jPanel.getWidth();
        double scaleY = pf.getImageableHeight() / jPanel.getHeight();
        double scale = Math.min(scaleX, scaleY);
        g2d.scale(scale, scale);

        // Print the JPanel
        jPanel.printAll(g2d);
        return PAGE_EXISTS;
    }
}
