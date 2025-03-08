package dia.scient.health.care.labs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JTextField userNameTF;
    JPasswordField passwordTF;
    JButton loginBtn,backBtn;



    Login(){

        setFram();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==loginBtn){

            try {

                String loginUserName = userNameTF.getText();
                String loginPassword = passwordTF.getText();
                String query="SELECT * FROM login WHERE username = '"+loginUserName+"' and password = '"+loginPassword+"'";

                dbConnection dbConnection=new dbConnection();
                ResultSet resultSet= dbConnection.statement.executeQuery(query);
                if (resultSet.next()){

                    setVisible(false);
                    new Main();

                }else{
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }

            }catch (Exception exception){
                exception.printStackTrace();
            }

        }else if(e.getSource()==backBtn) {
            System.exit(80);
        }
    }

    public static void main(String[] args) {
        new Login();
    }


    public void setFram(){


        setLable();
        setBgImg();
        setSize(600,250);
        setLayout(null);
        setVisible(true);
//        getContentPane().setBackground(Color.BLACK);
        setLocation(350,420);


        setTitle("Login");



    }
    public void setLable(){

        JLabel userNameLable=new JLabel("Username");
        userNameLable.setBounds(40,20,100,30);
//        userNameLable.setForeground(Color.white);
        add(userNameLable);

        //Text Fied of userName
        userNameTF=new JTextField();
        userNameTF.setBounds(150,20,200 ,20);
//        userNameTF.setForeground(Color.white);
        add(userNameTF);


        JLabel passwordLable=new JLabel("Password");
        passwordLable.setBounds(40,50,100,30);
//        passwordLable.setForeground(Color.white);

        add(passwordLable);

        //Text Fied for Password
        passwordTF=new JPasswordField();
        passwordTF.setBounds(150,50,200,20);
        add(passwordTF);

        //setting buttions
        setBTN();
    }
    private void setBTN(){
        loginBtn=new JButton("Login");
        loginBtn.setBounds(180,80,75,25);
        loginBtn.setBackground(Color.gray);
        loginBtn.setForeground(Color.white);
        loginBtn.addActionListener(this);
        add(loginBtn);

        backBtn=new JButton("BACK");
        backBtn.setBounds(275,80,75,25);
        backBtn.setBackground(Color.gray);
        backBtn.setForeground(Color.white);
        backBtn.addActionListener(this);
        add(backBtn);
    }

    private void setBgImg(){
        ImageIcon msLogo= new ImageIcon(ClassLoader.getSystemResource("assets/logos/microscop.png"));
        Image reSizeMsImg= msLogo.getImage().getScaledInstance(200,220,Image.SCALE_DEFAULT);
        ImageIcon msLogoReGet=new ImageIcon(reSizeMsImg);
        JLabel msLogoLable= new JLabel(msLogoReGet);
        msLogoLable.setBounds(370,2,200,220);
        add(msLogoLable);

        ImageIcon bgLogo= new ImageIcon(ClassLoader.getSystemResource("assets/logos/logo.png"));
        Image reSizeBgImg= bgLogo.getImage().getScaledInstance(400,80,Image.SCALE_DEFAULT);
        ImageIcon bgLogoReGet=new ImageIcon(reSizeBgImg);
        JLabel bgLogoLable= new JLabel(bgLogoReGet);
        bgLogoLable.setBounds(2,120,400,80);
        add(bgLogoLable);

    }
}
