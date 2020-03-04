package ssms;
import java.sql.*;

public class DBConnection {
    private final String DB_URL;
    private final String USER;
    private final String PASS;
    private Connection con = null;
    public boolean connected = false;

    public DBConnection(String DB, String U, String P)
    {
        DB_URL = DB;
        USER = U;
        PASS = P;
    }

    public void connect() {

        try {
            con = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected");
            connected = true;

        } catch (SQLException se) {
            System.out.println("Cant connect");
        }
    }

    public void closeConnection() throws SQLException {
        if(connected) {
            con.close();
        }
    }
}
