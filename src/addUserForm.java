import ssms.DBConnection;
import ssms.DBStatement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class addUserForm extends JFrame {
    private JPanel panel1;
    private JTextField phoneField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField statusField;
    private JButton closeButton;
    private JButton addCustomerButton;
    private JLabel ErrorLab;

    public addUserForm() {
        add(panel1);

        setTitle("SSMS - Add Customer");

        setSize(400, 400);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        addCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println(DBConnection.getConnected());

                if(nameField.getText().equals("") || phoneField.getText().equals("") || emailField.getText().equals("") || statusField.getText().equals(""))
                {
                    ErrorLab.setText("Cant be empty");
                }
                else
                {
                    try {
                        DBStatement.addCustomer(nameField.getText(), phoneField.getText(), emailField.getText(), statusField.getText());
                        dispose();
                    }
                    catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }
        });
    }
}
