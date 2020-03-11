package ssms;

import javax.swing.*;
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

    public static JList<String> getID() throws SQLException {

        DefaultListModel<String> listID = new DefaultListModel<>();

        for(Customer cust: getCustomer())
        {
            listID.addElement(cust.getId());
            //System.out.println(cust.getId());
        }

        JList<String> listOfID = new JList<>(listID);
        listOfID.setBounds(0,0, 150,75);

        return listOfID;
    }

    public static JList<String> getName() throws SQLException {

        DefaultListModel<String> listName = new DefaultListModel<>();

        for(Customer cust: getCustomer())
        {
            listName.addElement(cust.getName());
            //System.out.println(cust.getName());
        }

        JList<String> listOfName = new JList<>(listName);
        listOfName.setBounds(0,0, 150,75);

        return listOfName;
    }

    public static JList<String> getPhone() throws SQLException {

        DefaultListModel<String> listPhone = new DefaultListModel<>();

        for(Customer cust: getCustomer())
        {
            listPhone.addElement(cust.getPhone());
            //System.out.println(cust.getPhone());
        }

        JList<String> listOfPhone = new JList<>(listPhone);
        listOfPhone.setBounds(0,0, 150,75);

        return listOfPhone;
    }

    public static JList<String> getEmail() throws SQLException {

        DefaultListModel<String> listEmail = new DefaultListModel<>();

        for(Customer cust: getCustomer())
        {
            listEmail.addElement(cust.getEmail());
            //System.out.println(cust.getEmail());
        }

        JList<String> listOfEmail = new JList<>(listEmail);
        listOfEmail.setBounds(0,0, 150,75);

        return listOfEmail;
    }

    public static JList<String> getStatus() throws SQLException {

        DefaultListModel<String> listStatus = new DefaultListModel<>();

        for(Customer cust: getCustomer())
        {
            listStatus.addElement(cust.getStatus());
            //System.out.println(cust.getStatus());
        }

        JList<String> listOfStatus = new JList<>(listStatus);
        listOfStatus.setBounds(0,0, 150,75);

        return listOfStatus;
    }

}
