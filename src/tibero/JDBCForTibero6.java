package tibero;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCForTibero6 {
    
    private static final String DB_DRIVER = "com.tmax.tibero.jdbc.TbDriver";
    private static final String DB_CONNECTION = "jdbc:tibero:thin:@10.81.30.75:8629:tibero";
    private static final String DB_USER = "tiberotst";
    private static final String DB_PASSWORD = "tiberotst";

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
        String selectTableSQL = "SELECT id,name from test_tab";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // execute select SQL stetement
            ResultSet rs = statement.executeQuery(selectTableSQL);
            while (rs.next()) {
                String userid = rs.getString("id");
                String username = rs.getString("name");
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
