import ssms.Customer;
import ssms.DBStatement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class editCustomer extends  JFrame{
    private JTextField nameField;
    private JPanel panel1;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField statusField;
    private JButton editButton;
    private JButton closeButton;
    private JLabel ErrorLab;
    private int customer_id;
    private String name;
    private String phone;
    private String email;
    private String status;

    public editCustomer(int id, String n, String p, String e, String s) {

        customer_id = id;
        name = n;
        phone = p;
        email = e;
        status = s;

        add(panel1);

        setTitle("SSMS - Edit Customer" + customer_id);

        setSize(400, 400);

        nameField.setText(name);
        phoneField.setText(phone);
        emailField.setText(email);
        statusField.setText(status);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(nameField.getText().equals("") || phoneField.getText().equals("") || emailField.getText().equals("") || statusField.getText().equals(""))
                {
                    ErrorLab.setText("Cant be empty");
                }
                else
                {
                    try {
                        DBStatement.updateCustomer(id, nameField.getText(), phoneField.getText(), emailField.getText(), statusField.getText());
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    dispose();
                }
            }
        });

    }
}
