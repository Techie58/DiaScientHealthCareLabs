package dia.scient.health.care.labs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Print extends JFrame implements Printable {
    JScrollPane scrollPane;
    JPanel jPanel;

    public Print() {
        setPrintBtn();
        setFram();
    }

    public static void main(String[] args) {
        new Print();
    }

    private void setFram() {


        getJPanel();

        // JFrame settings
        setLayout(null);
        setSize(1000, 600);
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
        jPanel.setPreferredSize(new Dimension(1000, 1000)); // Set preferred size to make JScrollPane work
        jPanel.setBackground(Color.WHITE);


        settingHeader();
        setUserDetailLabels();
        setReportDetail();


        // Create a JScrollPane and add the JPanel to it
        scrollPane = new JScrollPane(jPanel);
        scrollPane.setBounds(10, 45, 960, 500);  // Position & size of the scroll pane
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Add JScrollPane to the JFrame
        add(scrollPane);

        return jPanel;
    }

    private JLabel setLabel(String labelText,int x, int y, int width, int height){
        JLabel jLabel = new JLabel(labelText);
        jLabel.setBounds(x, y, width,height);
        jLabel.setFont(new Font("Arial", Font.BOLD, 14));
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

        setImg("assets/bg_images/splash_bg.png",2,2,750,200);

        JLabel foterAdressLable=setLabel(adress,760,5, 250,200);
        foterAdressLable.setHorizontalAlignment(SwingConstants.LEFT); // Align text to left
        foterAdressLable.setVerticalAlignment(SwingConstants.TOP);
        jPanel.add(foterAdressLable);

        setLabel("<html><hr width='1500px' size='2' color='black'></html>",10,205,1500,5);
        setLabel("<html><hr width='1500px' size='1' color='black'></html>",10,235,1500,5);
        setLabel("<html><hr width='1500px' size='5' color='black'></html>",10,370,1500,5);


    }
    private void setUserDetailLabels(){
        setLabel("Name: ",      15,240,150,30);
        setLabel("Patient ID: ", 350,240,150,30);
        setLabel("Lab Refferal: ",750,240,200,30);

        setLabel("Age: ",      15,280,150,30);
        setLabel("Gender: ", 350,280,150,30);
        setLabel("Date: ",750,280,200,30);

        setLabel("Referred By: ",      15,320,150,30);
        setLabel("Registration Date: ", 350,320,150,30);
        setLabel("Lab No: ",750,320,150,30);
    }
    private void setReportDetail(){

        setLabel("H. PYLORI ANTIGEN (STOOL) ICT",10,390,600,40).setFont(new Font("Arial",Font.BOLD,18));
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
