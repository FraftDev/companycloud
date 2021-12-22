import DataAccess.Database;
import Models.Account;

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
                String passwordInput = new String(passwordBox.getPassword());

                Account loggedAccount = Account.Login(emailInput, passwordInput);

                if(loggedAccount == null)
                    JOptionPane.showMessageDialog(loginPanel, "Ihre Email/Passwort Kombination ist nicht gültig.", "Fehler beim Login", JOptionPane.ERROR_MESSAGE);
                else
                    JOptionPane.showMessageDialog(loginPanel, "Login erfolgreich, sie sind eingeloggt.");
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new LoginFrame("Company Cloud | Login");
        frame.setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
