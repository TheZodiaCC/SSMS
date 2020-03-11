package ssms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    private static ArrayList<Customer> getCustomer() throws SQLException {

        createStatement();

        ArrayList<Customer> customer = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT customer_id, name, phone, email, status FROM customers");

        while(resultSet.next())
        {
            String id = resultSet.getString("customer_id");
            String name = resultSet.getString("name");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            String status = resultSet.getString("status");

            customer.add(new Customer(id, name, phone, email, status));
            System.out.println("Added " + name);
        }
        resultSet.close();
        closeStatement();

        return customer;
    }

    public static void putIntable(DefaultTableModel model) throws SQLException {

        for(Customer cust : getCustomer())
        {
            model.addRow(new Object[]{cust.getId(), cust.getName(), cust.getPhone(), cust.getEmail(), cust.getStatus()});
        }
    }

    public static void refreashTable(DefaultTableModel model) throws SQLException {
        model.setRowCount(0);
        putIntable(model);
    }
}
