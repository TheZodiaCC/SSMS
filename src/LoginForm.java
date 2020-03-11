import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import ssms.DBConnection;


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
                //DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/flas", textField1.getText(), passwordField1.getText());
                //dbConnection.connect();
                DBConnection.connect("jdbc:mysql://localhost:3306/shop", textField1.getText(), passwordField1.getText());

                if(DBConnection.getConnected())
                {
                    dispose();
                    Content content = null;
                    try {
                        content = new Content();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    content.setVisible(true);
                    content.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else {
                    login_att.setText("Can't log in");
                }
            }
        });
    }
}
