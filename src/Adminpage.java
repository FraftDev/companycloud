import DataAccess.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import DataAccess.Globals;
import Models.*;

public class Adminpage extends JFrame{
    private JPanel AdminpagePanel;
    private JPanel LogsPanel;
    private JScrollPane LogsContent;
    private JPanel SettingsStatsPanel;
    private JPanel StatsPanel;
    private JPanel SettingsPanel;
    private JProgressBar TotalBar;
    private JProgressBar IndividualBar;
    private JProgressBar DepartmentBar;
    private JLabel TotalMainLabel;
    private JLabel IndividualMainLabel;
    private JLabel DepartmentMainLabel;
    private JLabel TotalStatusLabel;
    private JLabel IndividualStatusLabel;
    private JLabel DepartmentStatusLabel;
    private JButton registrierungenButton;
    private JButton abteilung1AnpassenButton;
    private JButton abteilung2AnpassenButton;
    private JList MitarbeiterList1;
    private JList MitarbeiterList2;
    private JButton zuAbteilung2WechselnButton;
    private JButton zuAbteilung1WechselnButton;
    private DefaultListModel mitarbeiterListModel1 = new DefaultListModel();
    private DefaultListModel mitarbeiterListModel2 = new DefaultListModel();


    public  Adminpage(){
        setContentPane(AdminpagePanel);
        setTitle("CompanyCloud | Admin Zugang");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());

        SetMitarbeiterListe(1);
        SetMitarbeiterListe(2);

        registrierungenButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == registrierungenButton) {
                    RegistrierungenAdminpage registrierungPanel = new RegistrierungenAdminpage();
                }
            }
        });
        abteilung1AnpassenButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == abteilung1AnpassenButton) {
                    Abteilung1Adminpage abeilungs1Panel =new Abteilung1Adminpage();
                }
            }
        });
        abteilung2AnpassenButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == abteilung2AnpassenButton) {
                    Abteilung2Adminpage abeilungs2Panel = new Abteilung2Adminpage();
                }
            }
        });
        zuAbteilung2WechselnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(MitarbeiterList1.isSelectionEmpty())
                    return;

                Account accountToUpdate = Account.GetAccounts().stream()
                        .filter(x -> x.email.equals(MitarbeiterList1.getSelectedValue()))
                        .findFirst()
                        .orElse(null);

                accountToUpdate.department = Globals.currentCompany.abteilung2;
                Account.UpdateAccount(accountToUpdate);

                if(Globals.currentUser.email.equals(accountToUpdate.email))
                    Globals.currentUser = accountToUpdate;

                SetMitarbeiterListe(1);
                SetMitarbeiterListe(2);
            }
        });
        zuAbteilung1WechselnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(MitarbeiterList2.isSelectionEmpty())
                    return;

                Account accountToUpdate = Account.GetAccounts().stream()
                        .filter(x -> x.email.equals(MitarbeiterList2.getSelectedValue()))
                        .findFirst()
                        .orElse(null);

                accountToUpdate.department = Globals.currentCompany.abteilung1;
                Account.UpdateAccount(accountToUpdate);

                if(Globals.currentUser.email.equals(accountToUpdate.email))
                    Globals.currentUser = accountToUpdate;

                SetMitarbeiterListe(1);
                SetMitarbeiterListe(2);
            }
        });
    }

    public void SetMitarbeiterListe(int id){
        if(id == 1){
            mitarbeiterListModel1 = new DefaultListModel();

            List<Account> accounts = Account.GetAccounts().stream().filter(x -> x.department.equals(Globals.currentCompany.abteilung1) &&
                    x.company.equals(Globals.currentCompany.name) &&
                    x.verified == 1)
                    .collect(Collectors.toList());

            for(Account account : accounts){
                mitarbeiterListModel1.addElement(account.email);
            }
            MitarbeiterList1.setModel(mitarbeiterListModel1);
        }
        else{
            mitarbeiterListModel2 = new DefaultListModel();

            List<Account> accounts = Account.GetAccounts().stream().filter(x -> x.department.equals(Globals.currentCompany.abteilung2) &&
                    x.company.equals(Globals.currentCompany.name) &&
                    x.verified == 1)
                    .collect(Collectors.toList());

            for(Account account : accounts){
                mitarbeiterListModel2.addElement(account.email);
            }
            MitarbeiterList2.setModel(mitarbeiterListModel2);
        }
    }
}
