import ssms.DBConnection;
import ssms.DBStatement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Content extends JFrame {
    private JPanel panel1;
    private JButton logout;
    private JLabel loggedAs;
    private JButton addCustomerButton;
    private JButton removeCustomerButton;
    private JButton editButton;
    private JButton refreashButton;
    private JTable customersTable;
    private JScrollPane scrollPan;
    private JTextField nameTextField;
    private JButton searchButton;


    public Content() throws SQLException {

        add(panel1);

        setTitle("SSMS - Content");

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        loggedAs.setText("Logged as " + DBConnection.getUSER());

        customersTable.setDefaultEditor(Object.class, null);

        scrollPan.setViewportView(customersTable);

        DefaultTableModel model = (DefaultTableModel) customersTable.getModel();

        model.addColumn("Customer ID");
        model.addColumn("Customer Name");
        model.addColumn("Customer Phone");
        model.addColumn("Customer Email");
        model.addColumn("Customer Status");

        DBStatement.putIntable(model, "SELECT customer_id, name, phone, email, status FROM customers");

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    DBConnection.closeConnection();
                    dispose();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        refreashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    DBStatement.refreashTable(model, "SELECT customer_id, name, phone, email, status FROM customers");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    DBStatement.searchByName(nameTextField.getText(), model);
                    nameTextField.setText("");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        removeCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(customersTable.getValueAt(customersTable.getSelectedRow(), 0).toString());
                try {
                    DBStatement.removeCustomer(id);
                    DBStatement.refreashTable(model, "SELECT customer_id, name, phone, email, status FROM customers");
                } catch (SQLException e) {
                    //Happens when user doesnt have permission to Delete
                    e.printStackTrace();
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int id = Integer.parseInt(customersTable.getValueAt(customersTable.getSelectedRow(), 0).toString());
                String name = customersTable.getValueAt(customersTable.getSelectedRow(), 1).toString();
                String phone = customersTable.getValueAt(customersTable.getSelectedRow(), 2).toString();
                String email = customersTable.getValueAt(customersTable.getSelectedRow(), 3).toString();
                String status = customersTable.getValueAt(customersTable.getSelectedRow(), 4).toString();

                editCustomer editCustomer = new editCustomer(id, name, phone, email, status);
                editCustomer.setVisible(true);
                editCustomer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });

        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                addUserForm addUser = new addUserForm();
                addUser.setVisible(true);
                addUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        });
    }
}
