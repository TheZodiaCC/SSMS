import ssms.DBConnection;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/flas", "root", "");
        dbConnection.connect();

        dbConnection.closeConnection();
    }
}
