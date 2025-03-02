package dia.scient.health.care.labs;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class dbConnection {

    Connection connection;
    Statement statement;


    public dbConnection(){
        String url="jdbc:mysql://localhost:3306/diascientlabs";
        String userName="root";
        String password="root";

        try {


            connection= DriverManager.getConnection(url,userName,password);
            statement=connection.createStatement();


        }catch (Exception e){
            e.printStackTrace();
        }

    };

}
