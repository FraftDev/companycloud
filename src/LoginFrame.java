import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JPanel loginPanel;
    private JTextField emailBox;
    private JPasswordField passwordBox;
    private JButton loginButton;
    private JButton registerButton;

    public LoginFrame(String frameName){
        super(frameName);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailInput = emailBox.getText();
                String passwordInput = passwordBox.getPassword().toString();



                JOptionPane.showMessageDialog(loginPanel, "Login erfolgreich, sie sind eingeloggt.");
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new LoginFrame("CompanyCloud Login");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
