import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addUserForm extends JFrame {
    private JPanel panel1;
    private JTextField textField2;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JButton closeButton;
    private JButton addCustomerButton;

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
    }
}
