import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/registration_db";
        String user = "root";
        String password = ""; // my MySQL password
        return DriverManager.getConnection(url, user, password);
    }
}