import DataAccess.Database;
import DataAccess.Globals;
import Models.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Registrierung extends JFrame{
    private JTextField Company;
    private JTextField Firstname;
    private JTextField Email;
    private JTextField Password;
    private JPanel MainRegistrierungPanel;
    private JButton registrierenButton;
    private JLabel Nachname;
    private JTextField Lastname;

    public Registrierung(){
        setContentPane(MainRegistrierungPanel);
        setTitle("Registrierung");
        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
        registrierenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailInput = Email.getText();
                String passwordInput = Password.getText();
                String companyInput = Company.getText();
                String firstnameInput = Firstname.getText();
                String lastnameInput = Lastname.getText();

                Account registeredAccount = Account.Register(firstnameInput, lastnameInput, emailInput, passwordInput, companyInput);

                if(registeredAccount == null)
                    JOptionPane.showMessageDialog(MainRegistrierungPanel, "Bitte kontaktieren Sie ihren Administrator.", "Fehler beim Registrieren.", JOptionPane.ERROR_MESSAGE);
                else
                {
                    Globals.currentUser = registeredAccount;
                    JOptionPane.showMessageDialog(MainRegistrierungPanel, "Registrierung erfolgreich, so sind nun eingeloggt.");
                    dispose();
                    Mainpage mainpage = new Mainpage();
                }
            }
        });
    }
}
