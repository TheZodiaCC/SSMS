package ssms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

public class DBStatement {

    private static Statement statement = null;

    private static void createStatement() throws SQLException {
        if(DBConnection.getConnected())
        {
            statement = DBConnection.getCon().createStatement();
        }
    }

    private static void closeStatement() throws SQLException {
        statement.close();
    }

    public static void searchByName(String search, DefaultTableModel model) throws SQLException {
        createStatement();

        String query = "SELECT customer_id, name, phone, email, status FROM customers WHERE name='"+search + "' OR phone='"+search + "' OR email='"+search + "' OR status='"+search + "'";

        model.setRowCount(0);
        putIntable(model, query);

        closeStatement();
    }

    public static void loginLog(String user) throws SQLException {

        Calendar calendar = Calendar.getInstance();

        String currHour =  String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(calendar.get(Calendar.MINUTE));
        String currDate = String.valueOf(calendar.get(Calendar.DATE)) + "-" + String.valueOf((calendar.get(Calendar.MONTH))+1) + "-" + String.valueOf(calendar.get(Calendar.YEAR));

        createStatement();
        statement.executeUpdate("INSERT INTO logins (`login_ID`, `user`, `date`, `hour`) VALUES (NULL, '" + user + "', '" + currDate + "', '" + currHour + "');");
        closeStatement();
    }

    private static ArrayList<Customer> getCustomer(String query) throws SQLException {

        createStatement();

        ArrayList<Customer> customer = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next())
        {
            String id = resultSet.getString("customer_id");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            String status = resultSet.getString("status");

            customer.add(new Customer(id, name, phone, email, status));
            //System.out.println("Added " + name);
        }
        resultSet.close();
        closeStatement();

        return customer;
    }

    public static void putIntable(DefaultTableModel model, String query) throws SQLException {

        for(Customer cust : getCustomer(query))
        {
            model.addRow(new Object[]{cust.getId(), cust.getName(), cust.getPhone(), cust.getEmail(), cust.getStatus()});
        }
    }

    public static void refreashTable(DefaultTableModel model, String query) throws SQLException {
        model.setRowCount(0);
        putIntable(model, query);
    }
}
