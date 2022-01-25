import DataAccess.Database;
import DataAccess.Globals;
import Models.Account;
import Models.Company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Abteilung1Adminpage extends JFrame{
    private JPanel Abteilung1MainPanel;
    private JTextField AbteilungsName;
    private JButton änderungenSpeichernButton;


    public  Abteilung1Adminpage(){
        setContentPane(Abteilung1MainPanel);
        setTitle("Abteilung 1");
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        änderungenSpeichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(AbteilungsName.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(Abteilung1MainPanel, "Bitte geben Sie einen neuen Namen für die Abteilung ein.");
                    return;
                }

                if(AbteilungsName.getText().contains("."))
                {
                    JOptionPane.showMessageDialog(Abteilung1MainPanel, "Der Abteilungsname darf keinen Punkt enthalten.");
                    return;
                }

                String oldDepartmentName = Globals.currentCompany.abteilung1;

                Globals.currentCompany.abteilung1 = AbteilungsName.getText();

                if(Globals.currentUser.department.equals(oldDepartmentName))
                    Globals.currentUser.department = Globals.currentCompany.abteilung1;

                Company.UpdateCompany(Globals.currentCompany);

                List<Account> accountsToUpdate = Account.GetAccounts();
                accountsToUpdate.forEach((account) -> {
                    if(account.company.equals(Globals.currentCompany.name) && account.department.equals(oldDepartmentName))
                        account.department = Globals.currentCompany.abteilung1;
                });

                Database.UpdateAccounts(accountsToUpdate);

                File oldDir = new File(Database.SERVER_PATH + "\\" + Globals.currentCompany.name + "\\" + oldDepartmentName + "\\");
                oldDir.renameTo(new File(Database.SERVER_PATH + "\\" + Globals.currentCompany.name + "\\" + Globals.currentCompany.abteilung1 + "\\"));

                JOptionPane.showMessageDialog(Abteilung1MainPanel, "Abteilung erfolgreich umbenannt.");
            }
        });
    }
}
