import DataAccess.Database;
import DataAccess.Globals;
import Models.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This JFrame displays and handles the Adminpage that show the open registrations
 * Here an Admin can deny or accept open registrations
 */
public class RegistrierungenAdminpage extends JFrame{
    private JPanel RegistrierungMainPanel;
    private JButton zulassenButton;
    private JButton ablehnenButton;
    private JList UserList;
    private JScrollBar scrollBar1;
    private DefaultListModel defaultListModel = new DefaultListModel();

    public  RegistrierungenAdminpage(){
        setContentPane(RegistrierungMainPanel);
        setTitle("CompanyCloud | Registrierungen Zulassen");
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        List<Account> accounts = Account.GetAccounts().stream().filter(x -> x.company.equals(Globals.currentUser.company) && x.verified == 0).collect(Collectors.toList());

        for(Account account : accounts){
            defaultListModel.addElement(account.email);
        }

        UserList.setModel(defaultListModel);
        // This Button accepts a registration and verifies the User in the Database
        zulassenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(UserList.isSelectionEmpty())
                    return;

                Account accountToUpdate = Account.GetAccounts().stream()
                        .filter(x -> x.email.equals(UserList.getSelectedValue().toString()))
                        .findFirst()
                        .orElse(null);

                if(accountToUpdate == null)
                    return;

                accountToUpdate.verified = 1;
                Account.UpdateAccount(accountToUpdate);

                defaultListModel.removeElementAt(UserList.getSelectedIndex());

                JOptionPane.showMessageDialog(RegistrierungMainPanel, "Der Nutzer wurde erfolgreich freigegeben.", "Nutzer freigegeben", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // This Button rejecs a registration and deletes the User in the Database
        ablehnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(UserList.isSelectionEmpty())
                    return;

                Account accountToDelete = Account.GetAccounts().stream()
                        .filter(x -> x.email.equals(UserList.getSelectedValue().toString()))
                        .findFirst()
                        .orElse(null);

                if(accountToDelete == null)
                    return;

                Account.DeleteAccount(accountToDelete);

                defaultListModel.removeElementAt(UserList.getSelectedIndex());

                JOptionPane.showMessageDialog(RegistrierungMainPanel, "Der Nutzer wurde erfolgreich gel??scht.", "Nutzer gel??scht", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void main(String[] args){
        RegistrierungenAdminpage registrierungPanel =new RegistrierungenAdminpage();
    }}
