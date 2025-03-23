package dia.scient.health.care.labs;
import java.io.File;
import java.sql.*;

public class SQLiteDbConnection {
    Connection connection;
    Statement statement;
    String userName, password;

    public SQLiteDbConnection() {
        // Set database location in user home directory
        String dbPath = System.getProperty("user.home") + "/MyAppData/login.db";
        String dbDir = System.getProperty("user.home") + "/MyAppData/";

        String url = "jdbc:sqlite:" + dbPath;
        userName = "root";
        password = "root";

        try {


            // Print database path for debugging
            System.out.println("Database path: " + dbPath);

            // Ensure directory exists
            File directory = new File(dbDir);

            if (!directory.exists()) {
                boolean dirCreated = directory.mkdirs();
                System.out.println("Directory Created: " + dirCreated);
            } else {
                System.out.println("Directory already exists.");
            }

            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            System.out.println("Database connection successful!");


            // Call table creation methods
            createPatientDetailsTable();
            createLoginTable();

            System.out.println("Tables created successfully!");


            System.out.println("Database initialized at: " + dbPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createLoginTable() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS login (" +
                "username TEXT UNIQUE NOT NULL, " +
                "password TEXT NOT NULL" +
                ");";
        statement.execute(createTableQuery);

        // Check if "root" user exists
        String checkUserQuery = "SELECT COUNT(*) FROM login WHERE username = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkUserQuery)) {
            checkStmt.setString(1, "root");
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                // Insert default user
                String insertQuery = "INSERT INTO login VALUES (?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setString(1, "root");
                    preparedStatement.setString(2, "root");
                    preparedStatement.executeUpdate();
                }
            }
        }
    }

    private void createPatientDetailsTable() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS patient_details (" +
                "lab_number INT NOT NULL DEFAULT 0, " +
                "patient_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date_time TEXT DEFAULT NULL, " +
                "patient_name TEXT NOT NULL DEFAULT '', " +
                "gender TEXT NOT NULL DEFAULT '', " +
                "age INT DEFAULT NULL, " +
                "test TEXT DEFAULT NULL, " +
                "phone_no TEXT DEFAULT NULL, " +
                "address TEXT DEFAULT NULL, " +
                "remarks TEXT DEFAULT NULL, " +
                "payment INT DEFAULT NULL" +
                ");";

        statement.execute(createTableQuery);
    }

}
