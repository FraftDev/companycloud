import DataAccess.Database;
import DataAccess.Globals;
import Models.Account;
import Models.Company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This JFrame displays and handles the Loginframe
 */
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

        // This button check if the login is succesful
        // It opens the mainpage if yes, and displays an error message if not.
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailInput = emailBox.getText();
                String passwordInput = new String(passwordBox.getPassword());

                Account loggedAccount = Account.Login(emailInput, passwordInput);

                if(loggedAccount == null)
                    JOptionPane.showMessageDialog(loginPanel, "Ihre Email/Passwort Kombination ist nicht gültig.", "Fehler beim Login", JOptionPane.ERROR_MESSAGE);
                else if(loggedAccount.verified == 0)
                    JOptionPane.showMessageDialog(loginPanel, "Ihr Account wurde noch nicht vom Administrator freigegeben.", "Fehler beim Login", JOptionPane.ERROR_MESSAGE);
                else
                {
                    Globals.currentUser = loggedAccount;
                    Globals.currentCompany = Company.GetCompanyByName(loggedAccount.company);
                    JOptionPane.showMessageDialog(loginPanel, "Login erfolgreich, sie sind eingeloggt.");
                    dispose();
                    Mainpage mainpage =new Mainpage();
                }


            }
        });
        // This button opens the Registration Page
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrierung MainRegistrierungPanel = new Registrierung();
            }
        });
    }
}
