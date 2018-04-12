package tibero;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStatementSelectTibero6 {
    
    private static final String DB_DRIVER = "com.tmax.tibero.jdbc.TbDriver";
    private static final String DB_CONNECTION = "jdbc:tibero:thin:@192.168.97.4:8629:tb6";
    private static final String DB_USER = "sys";
    private static final String DB_PASSWORD = "tibero";

    public static void main(String[] argv) {
        try {
            selectRecordsFromDbUserTable();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void selectRecordsFromDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "SELECT EMPLOYEE_ID userid, FIRST_NAME username from HR.EMPLOYEES where employee_id=206";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String userid = rs.getString("userid");
                String username = rs.getString("username");
                System.out.println("userid : " + userid +" , username : " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}
