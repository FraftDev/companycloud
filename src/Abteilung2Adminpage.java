import DataAccess.Database;
import DataAccess.Globals;
import Models.Account;
import Models.Company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class Abteilung2Adminpage extends JFrame{
    private JPanel Abteilung2Mainpanel;
    private JTextField AbteilungsName;
    private JButton änderungen2SpeichernButton;


    public  Abteilung2Adminpage(){
        setContentPane(Abteilung2Mainpanel);
        setTitle("Download");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
        änderungen2SpeichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(AbteilungsName.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(Abteilung2Mainpanel, "Bitte geben Sie einen neuen Namen für die Abteilung ein.");
                    return;
                }

                if(AbteilungsName.getText().contains("."))
                {
                    JOptionPane.showMessageDialog(Abteilung2Mainpanel, "Der Abteilungsname darf keinen Punkt enthalten.");
                    return;
                }

                String oldDepartmentName = Globals.currentCompany.abteilung2;

                Globals.currentCompany.abteilung2 = AbteilungsName.getText();

                if(Globals.currentUser.department.equals(oldDepartmentName))
                    Globals.currentUser.department = Globals.currentCompany.abteilung2;

                Company.UpdateCompany(Globals.currentCompany);

                List<Account> accountsToUpdate = Account.GetAccounts();
                accountsToUpdate.forEach((account) -> {
                    if(account.company.equals(Globals.currentCompany.name) && account.department.equals(oldDepartmentName))
                        account.department = Globals.currentCompany.abteilung2;
                });

                Database.UpdateAccounts(accountsToUpdate);

                File oldDir = new File(Database.SERVER_PATH + "\\" + Globals.currentCompany.name + "\\" + oldDepartmentName + "\\");
                oldDir.renameTo(new File(Database.SERVER_PATH + "\\" + Globals.currentCompany.name + "\\" + Globals.currentCompany.abteilung1 + "\\"));

                JOptionPane.showMessageDialog(Abteilung2Mainpanel, "Abteilung erfolgreich umbenannt.");
            }
        });
    }
}
