import DataAccess.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    public  Adminpage(){
        setContentPane(AdminpagePanel);
        setTitle("Adminpage");
        setSize(800, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setIconImage(new ImageIcon(Database.ICON_PATH).getImage());
        registrierungenButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == registrierungenButton) {
                    RegistrierungenAdminpage registrierungPanel =new RegistrierungenAdminpage();
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
                    Abteilung2Adminpage abeilungs2Panel =new Abteilung2Adminpage();
                }
            }
        });
    }

    public static void main(String[] args){
        Adminpage adminpagePanel =new Adminpage();
    }

}
