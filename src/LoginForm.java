import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;

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
                //DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/flas", textField1.getText(), passwordField1.getText());
                //dbConnection.connect();
                DBConnection.connect("jdbc:mysql://localhost:3306/shop", textField1.getText(), passwordField1.getText());

                if(DBConnection.getConnected())
                {
                    Calendar calendar = Calendar.getInstance();
                    String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
                    String mins = String.valueOf(calendar.get(Calendar.MINUTE));
                    String date_day = String.valueOf(calendar.get(Calendar.DATE));
                    String date_month = String.valueOf((calendar.get(Calendar.MONTH))+1);
                    String date_year = String.valueOf(calendar.get(Calendar.YEAR));

                    try {
                        DBStatement.loginLog(textField1.getText(), hour + ":" + mins, date_day + "-" + date_month + "-" + date_year);
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
