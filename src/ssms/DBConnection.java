package ssms;
import java.sql.*;

public class DBConnection {
    private static String DB_URL;
    private static String USER;
    private static String PASS;
    private static Connection con = null;
    public static boolean connected = false;

    //public DBConnection(String DB, String U, String P)
    //{
    //    DB_URL = DB;
    //    USER = U;
    //    PASS = P;
    //}

    public static String getUSER()
    {
        return USER;
    }

    public static void connect(String db_url, String user, String pass) {

        try {
            con = DriverManager.getConnection(db_url, user, pass);
            System.out.println("Connected");
            USER = user;
            connected = true;

        } catch (SQLException se) {
            System.out.println("Cant connect");
        }
    }

    public static void closeConnection() throws SQLException {
        if(connected) {
            con.close();
            System.out.println("Disconnected");
            connected = false;
        }
    }
}
