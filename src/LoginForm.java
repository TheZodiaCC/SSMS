import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import ssms.DBConnection;
import ssms.DBStatement;


public class LoginForm extends JFrame {
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton loginButton;
    private JPanel rootPanel;
    private JLabel login_att;

    public LoginForm()
    {
        add(rootPanel);

        setTitle("SSMS - Login");
        setSize(400, 400);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DBConnection.connect("jdbc:mysql://localhost:3306/shop", textField1.getText(), new String(passwordField1.getPassword()));

                if(DBConnection.getConnected())
                {
                    try {
                        DBStatement.loginLog(textField1.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    dispose();
                    Content content = null;
                    try {
                        content = new Content();
                        content.setVisible(true);
                        content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    login_att.setText("Can't log in");
                }
            }
        });
    }
}
