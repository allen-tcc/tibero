package tibero;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCStatementSelectOracle {

    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String DB_CONNECTION = "jdbc:oracle:thin:@192.168.97.4:1521:orcl";
    private static final String DB_USER = "system";
    private static final String DB_PASSWORD = "oracle";

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
                System.out.println("userid : " + userid);
                System.out.println("username : " + username);
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
